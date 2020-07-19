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
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package cz.burios.uniql.persistence.jpa.jpql.tools.model.query;

import static cz.burios.uniql.persistence.jpa.jpql.parser.Expression.*;

import cz.burios.uniql.persistence.jpa.jpql.parser.ValueExpression;
import cz.burios.uniql.persistence.jpa.jpql.tools.spi.IType;
import cz.burios.uniql.persistence.jpa.jpql.tools.spi.ITypeDeclaration;

/**
 * This object represents an identification variable that maps the values of a {@link java.util.Map}.
 * <p>
 * This is part of JPA 2.0.
 *
 * <div><b>BNF:</b> <code>VALUE(identification_variable)</code><p></div>
 *
 * @see ValueExpression
 *
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
public class ValueExpressionStateObject extends EncapsulatedIdentificationVariableExpressionStateObject {

	/**
	 * Creates a new <code>ValueExpressionStateObject</code>.
	 *
	 * @param parent The parent of this state object, which cannot be <code>null</code>
	 * @exception NullPointerException The given parent cannot be <code>null</code>
	 */
	public ValueExpressionStateObject(StateObject parent) {
		super(parent);
	}

	/**
	 * Creates a new <code>ValueExpressionStateObject</code>.
	 *
	 * @param parent The parent of this state object, which cannot be <code>null</code>
	 * @param identificationVariable The identification variable
	 * @exception NullPointerException The given parent cannot be <code>null</code>
	 */
	public ValueExpressionStateObject(StateObject parent, String identificationVariable) {
		super(parent, identificationVariable);
	}

	/**
	 * {@inheritDoc}
	 */
	public void accept(StateObjectVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValueExpression getExpression() {
		return (ValueExpression) super.getExpression();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdentifier() {
		return VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IType resolveType() {

		ITypeDeclaration typeDeclaration = getTypeDeclaration();

		if (getTypeHelper().isMapType(typeDeclaration.getType())) {
			ITypeDeclaration[] typeParameters = typeDeclaration.getTypeParameters();

			if (typeParameters.length == 2) {
				return typeParameters[1].getType();
			}
		}

		return getTypeHelper().objectType();
	}

	/**
	 * Keeps a reference of the {@link ValueExpression parsed object} object, which should only be
	 * done when this object is instantiated during the conversion of a parsed JPQL query into
	 * {@link StateObject StateObjects}.
	 *
	 * @param expression The {@link ValueExpression parsed object} representing a <code><b>VALUE</b></code>
	 * expression
	 */
	public void setExpression(ValueExpression expression) {
		super.setExpression(expression);
	}
}