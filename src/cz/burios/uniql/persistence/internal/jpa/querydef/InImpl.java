/*******************************************************************************
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Gordon Yorke - Initial development
 *
 ******************************************************************************/

package cz.burios.uniql.persistence.internal.jpa.querydef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.metamodel.Metamodel;

import cz.burios.uniql.persistence.internal.expressions.CollectionExpression;
import cz.burios.uniql.persistence.internal.expressions.CompoundExpression;
import cz.burios.uniql.persistence.internal.expressions.FunctionExpression;
import cz.burios.uniql.persistence.internal.expressions.LogicalExpression;
import cz.burios.uniql.persistence.internal.expressions.RelationExpression;
import cz.burios.uniql.persistence.internal.localization.ExceptionLocalization;

/**
 * <p>
 * <b>Purpose</b>: Contains the implementation of the In interface of the JPA
 * criteria API.
 * <p>
 * <b>Description</b>: This class represents an In predicate.
 * <p>
 * 
 * @see javax.persistence.criteria Join
 * 
 * @author gyorke
 * @since EclipseLink 1.2
 */

public class InImpl<T> extends CompoundExpressionImpl implements In<T> {
    
    protected cz.burios.uniql.persistence.expressions.Expression parentNode;
    
    public InImpl(Metamodel metamodel, ExpressionImpl leftExpression, Collection values, List expressions) {
        super(metamodel, ((InternalSelection)leftExpression).getCurrentNode().in(values), expressions, "in");
        this.leftExpression = leftExpression;
        
    }

    public InImpl(Metamodel metamodel, ExpressionImpl leftExpression, ExpressionImpl rightExp, List expressions) {
        super(metamodel, 
            (rightExp.isParameter()?
                leftExpression.getCurrentNode().in(rightExp.getCurrentNode()):
                leftExpression.getCurrentNode().equal(rightExp.getCurrentNode())), 
            expressions, "in");
        this.leftExpression = leftExpression;
        
    }

    protected ExpressionImpl leftExpression;

    /**
     * Returns the expression to be tested against the
     * list of values.
     * @return expression
     */
    public Expression<T> getExpression(){
        return this.leftExpression;
    }

    public void findRootAndParameters(CommonAbstractCriteriaImpl query){
        super.findRootAndParameters(query);
    }

    public boolean isPredicate(){
        return true;
    }

    /**
     *  Add to list of values to be tested against.
     *  @param value value
     *  @return in predicate
     */
    public In<T> value(T value){
        ((Collection)((CollectionExpression)((RelationExpression)this.currentNode).getSecondChild()).getValue()).add(value);
        return this;
    }

    /**
     *  Add to list of values to be tested against.
     *  @param value expression
     *  @return in predicate
     */
    public In<T> value(Expression<? extends T> value) {
        if (!(((InternalExpression) value).isLiteral() || ((InternalExpression) value).isParameter())) {
            RelationExpression baseIn = (RelationExpression) this.currentNode;
            this.currentNode = baseIn.getFirstChild().in(((SubQueryImpl) value).subQuery);
            if (this.parentNode != null) {
                if (this.parentNode.isCompoundExpression()) {
                    CompoundExpression logExp = (LogicalExpression) this.parentNode;
                    if (logExp.getFirstChild() == baseIn) {
                        logExp.create(this.currentNode, logExp.getSecondChild(), logExp.getOperator());
                    } else {
                        logExp.create(logExp.getFirstChild(), this.currentNode, logExp.getOperator());
                    }
                } else {
                    FunctionExpression funcExp = (FunctionExpression) this.parentNode;
                    funcExp.getChildren().set(funcExp.getChildren().indexOf(baseIn), this.currentNode);
                }
            }
        } else {
            if (this.currentNode.isRelationExpression()) {
                RelationExpression baseIn = (RelationExpression) this.currentNode;
                ((InternalSelection) value).getCurrentNode().setLocalBase(baseIn.getFirstChild());
                ((Collection) ((CollectionExpression) baseIn.getSecondChild()).getValue()).add(((InternalSelection) value).getCurrentNode());
            } else {
                throw new IllegalStateException(ExceptionLocalization.buildMessage("CANNOT_ADD_CONSTANTS_TO_SUBQUERY_IN"));
            }
        }
        return this;
    }
    
    /**
     * This method is used to store what will be the parent EclipseLink expression in the case the tree needs to be altered.
     * Currently used for In.
     */
    public void setParentNode(cz.burios.uniql.persistence.expressions.Expression parentNode){
        this.parentNode = parentNode;
    }

    public Predicate not(){
        parentNode = this.getCurrentNode().not();
        ArrayList list = new ArrayList();
        list.add(this);
        CompoundExpressionImpl expr = new CompoundExpressionImpl(this.metamodel, parentNode, list, "not");
        expr.setIsNegated(true);
        return expr;
    }
}
