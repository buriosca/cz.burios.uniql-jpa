/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle and/or its affiliates. All rights reserved.
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
package cz.burios.uniql.persistence.jpa.jpql.tools.resolver;

import cz.burios.uniql.persistence.jpa.jpql.tools.spi.IManagedType;
import cz.burios.uniql.persistence.jpa.jpql.tools.spi.IType;
import cz.burios.uniql.persistence.jpa.jpql.tools.spi.ITypeDeclaration;

/**
 * This {@link Resolver} is responsible to return the map key, which means that for identification
 * variables referring to an instance of an association or collection represented as a {@link
 * java.util.Map Map}, the identification variable is of the abstract schema type of the map key.
 *
 * @see ValueResolver
 *
 * @version 2.5
 * @since 2.3
 * @author Pascal Filion
 */
@SuppressWarnings("nls")
public class KeyResolver extends Resolver {

	/**
	 * Creates a new <code>KeyResolver</code>.
	 *
	 * @param parent The parent {@link Resolver}, which is never <code>null</code>
	 */
	public KeyResolver(Resolver parent) {
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IType buildType() {

		ITypeDeclaration typeDeclaration = getTypeDeclaration();

		if (getTypeHelper().isMapType(typeDeclaration.getType())) {
			ITypeDeclaration[] typeParameters = typeDeclaration.getTypeParameters();

			if (typeParameters.length > 0) {
				return typeParameters[0].getType();
			}
		}

		return getTypeHelper().objectType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ITypeDeclaration buildTypeDeclaration() {
		return getParentTypeDeclaration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IManagedType getManagedType() {
		return getProvider().getManagedType(getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "KEY(" + getParent() + ")";
	}
}