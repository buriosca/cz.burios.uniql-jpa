/*******************************************************************************
 * Copyright (c) 2006, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package cz.burios.uniql.persistence.jpa.jpql.parser;

import cz.burios.uniql.persistence.jpa.jpql.WordParser;

/**
 * An <b>ALL</b> conditional expression is a predicate that is <code>true</code> if the comparison
 * operation is <code>true</code> for all values in the result of the subquery or the result of the
 * subquery is empty. An <b>ALL</b> conditional expression is <code>false</code> if the result of
 * the comparison is <code>false</code> for at least one row, and is unknown if neither
 * <code>true</code> nor <code>false</code>.
 * <p>
 * An <b>ANY</b> conditional expression is a predicate that is <code>true</code> if the comparison
 * operation is <code>true</code> for some value in the result of the subquery. An <b>ANY</b>
 * conditional expression is <code>false</code> if the result of the subquery is empty or if the
 * comparison operation is <code>false</code> for every value in the result of the subquery, and is
 * unknown if neither <code>true</code> nor <code>false</code>. The keyword <b>SOME</b> is
 * synonymous with <b>ANY</b>. The comparison operators used with <b>ALL</b> or <b>ANY</b>
 * conditional expressions are =, {@literal <, <=, >, >=, <>}. The result of the subquery must be
 * like that of the other argument to the comparison operator in type.
 *
 * <div><b>BNF:</b> <code>all_or_any_expression ::= {ALL|ANY|SOME}(subquery)</code><p></div>
 *
 * @version 2.5
 * @since 2.3
 * @author Pascal Filion
 */
public final class AllOrAnyExpression extends AbstractSingleEncapsulatedExpression {

	/**
	 * Creates a new <code>AllOrAnyExpression</code>.
	 *
	 * @param parent The parent of this expression
	 * @param identifier Either {@link Expression#ALL ALL}, {@link Expression#ANY ANY} or {@link
	 * Expression#SOME SOME}
	 */
	public AllOrAnyExpression(AbstractExpression parent, String identifier) {
		super(parent, identifier);
	}

	/**
	 * {@inheritDoc}
	 */
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEncapsulatedExpressionQueryBNFId() {
		return SubqueryBNF.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	public JPQLQueryBNF getQueryBNF() {
		return getQueryBNF(AllOrAnyExpressionBNF.ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractExpression parse(WordParser wordParser, String queryBNFId, boolean tolerant) {

		if (tolerant) {
			return super.parse(wordParser, queryBNFId, tolerant);
		}

		SimpleSelectStatement expression = new SimpleSelectStatement(this);
		expression.parse(wordParser, tolerant);
		return expression;
	}
}