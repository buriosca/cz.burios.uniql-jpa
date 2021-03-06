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
 * One of the aggregate functions. The return type of this function is a <code>Long</code>.
 *
 * <div><b>BNF:</b> <code>expression ::= COUNT ([DISTINCT] identification_variable |
 *                                                                state_field_path_expression |
 *                                                                single_valued_object_path_expression)</code><p></div>
 *
 * @version 2.5
 * @since 2.3
 * @author Pascal Filion
 */
public final class CountFunction extends AggregateFunction {

	/**
	 * Creates a new <code>CountFunction</code>.
	 *
	 * @param parent The parent of this expression
	 */
	public CountFunction(AbstractExpression parent) {
		super(parent, COUNT);
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
	protected AbstractExpression buildEncapsulatedExpression(WordParser wordParser, String word) {

		if (KEY.equalsIgnoreCase(word)) {
			ExpressionFactory factory = getExpressionFactory(KeyExpressionFactory.ID);
			return factory.buildExpression(this, wordParser, word, getQueryBNF(), null, false);
		}

		if (VALUE.equalsIgnoreCase(word)) {
			ExpressionFactory factory = getExpressionFactory(ValueExpressionFactory.ID);
			return factory.buildExpression(this, wordParser, word, getQueryBNF(), null, false);
		}

		if (word.indexOf(DOT) == -1) {
			return new IdentificationVariable(this, word);
		}

		return super.buildEncapsulatedExpression(wordParser, word);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEncapsulatedExpressionQueryBNFId() {
		return InternalCountBNF.ID;
	}
}