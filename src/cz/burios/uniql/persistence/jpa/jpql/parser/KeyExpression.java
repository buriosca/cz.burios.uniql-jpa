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

/**
 * An identification variable qualified by the <code><b>KEY</b></code> operator is a path
 * expression. The <code><b>KEY</b></code> operator may only be applied to identification
 * variables that correspond to map-valued associations or map-valued element collections.
 * The type of the path expression is the type computed as the result of the operation; that
 * is, the abstract schema type of the field that is the value of the <code><b>KEY</b></code>
 * operator (the map key).
 * <p>
 * This is part of JPA 2.0.
 *
 * <div><b>BNF:</b> <code>KEY(identification_variable)</code><p></div>
 *
 * @version 2.5
 * @since 2.3
 * @author Pascal Filion
 */
public final class KeyExpression extends EncapsulatedIdentificationVariableExpression {

	/**
	 * Creates a new <code>KeyExpression</code>.
	 *
	 * @param parent The parent of this expression
	 */
	public KeyExpression(AbstractExpression parent) {
		super(parent, KEY);
	}

	/**
	 * {@inheritDoc}
	 */
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
}