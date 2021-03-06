/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     tware - initial implementation as part of JPA 2.0 RI
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.parsing;

import cz.burios.uniql.persistence.expressions.Expression;
import cz.burios.uniql.persistence.internal.expressions.MapEntryExpression;
import cz.burios.uniql.persistence.queries.ObjectLevelReadQuery;
import cz.burios.uniql.persistence.queries.ReportQuery;

/**
 * INTERNAL
 * <p><b>Purpose</b>: Represent an ENTRY in EJBQL
 * <p><b>Responsibilities</b>:<ul>
 * <li> Generate the correct expression for an ENTRY in EJBQL
 * </ul>
 *    @author tware
 *    @since EclipseLink 1.2
 */
public class MapEntryNode extends Node implements AliasableNode {
    
    public MapEntryNode(){
        super();
    }
    
    /**
     * INTERNAL
     * Apply this node to the passed query
     */
    public void applyToQuery(ObjectLevelReadQuery theQuery, GenerationContext generationContext) {
        if (theQuery instanceof ReportQuery) {
            ReportQuery reportQuery = (ReportQuery)theQuery;
            Expression expression = generateExpression(generationContext);
            reportQuery.addItem(left.resolveAttribute() + " MapEntry", expression);
        }
    }
    
    /**
     * INTERNAL
     * Generate the a new EclipseLink TableEntryExpression for this node.
     */
    public Expression generateExpression(GenerationContext context) {
        Expression owningExpression = getLeft().generateExpression(context);
        MapEntryExpression whereClause = new MapEntryExpression(owningExpression);
        whereClause.returnMapEntry();
        return whereClause;
    }
    
    public void validate(ParseTreeContext context) {
        TypeHelper typeHelper = context.getTypeHelper();
        left.validate(context);
        setType(typeHelper.getMapEntryType());
    }

    
    public boolean isAliasableNode(){
        return true;
    }
}

