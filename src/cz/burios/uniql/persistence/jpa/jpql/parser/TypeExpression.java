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
 * An entity type expression can be used to restrict query polymorphism. The <b>TYPE</b> operator
 * returns the exact type of the argument.
 * <p>
 * Part of JPA 2.0.
 *
 * <div><b>BNF:</b> <code>type_discriminator ::= TYPE(identification_variable |
 *                                                           single_valued_object_path_expression |
 *                                                           input_parameter)</code><p></div>
 *
 * @version 2.5
 * @since 2.3
 * @author Pascal Filion
 */
public final class TypeExpression extends AbstractSingleEncapsulatedExpression {

	/**
	 * Creates a new <code>TypeExpression</code>.
	 *
	 * @param parent The parent of this expression
	 */
	public TypeExpression(AbstractExpression parent) {
		super(parent, TYPE);
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
		return InternalEntityTypeExpressionBNF.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	public JPQLQueryBNF getQueryBNF() {
		return getQueryBNF(TypeExpressionBNF.ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractExpression parse(WordParser wordParser, String queryBNFId, boolean tolerant) {

		if (tolerant) {
			return super.parse(wordParser, queryBNFId, tolerant);
		}

		return buildExpressionFromFallingBack(
			wordParser,
			wordParser.word(),
			getQueryBNF(InternalEntityTypeExpressionBNF.ID),
			null,
			tolerant
		);
	}
}