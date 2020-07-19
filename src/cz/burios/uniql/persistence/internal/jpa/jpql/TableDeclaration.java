/*******************************************************************************
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates. All rights reserved.
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
package cz.burios.uniql.persistence.internal.jpa.jpql;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.expressions.Expression;
import cz.burios.uniql.persistence.jpa.jpql.ExpressionTools;
import cz.burios.uniql.persistence.jpa.jpql.LiteralType;
import cz.burios.uniql.persistence.jpa.jpql.parser.TableVariableDeclaration;
import cz.burios.uniql.persistence.mappings.DatabaseMapping;

/**
 * This {@link Declaration} uses a database table as the "root" object.
 *
 * @version 2.5
 * @since 2.4
 * @author Pascal Filion
 */
final class TableDeclaration extends Declaration {

	/**
	 * Creates a new <code>TableDeclaration</code>.
	 *
	 * @param queryContext The context used to query information about the application metadata and
	 * cached information
	 */
	TableDeclaration(JPQLQueryContext queryContext) {
		super(queryContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	Expression buildQueryExpression() {
		TableVariableDeclaration declaration = (TableVariableDeclaration) getBaseExpression();
		String tableName = queryContext.literal(declaration.getTableExpression(), LiteralType.STRING_LITERAL);
		tableName = ExpressionTools.unquote(tableName);
		return queryContext.getBaseExpression().getTable(tableName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return Type.TABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	ClassDescriptor resolveDescriptor() {
		// A TableExpression does not resolve to a descriptor,
		// it maps directly to a database table
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	DatabaseMapping resolveMapping() {
		// A TableExpression does not resolve to a mapping,
		// it maps directly to a database table
		return null;
	}
}