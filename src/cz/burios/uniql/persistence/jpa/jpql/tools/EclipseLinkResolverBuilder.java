/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
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
package cz.burios.uniql.persistence.jpa.jpql.tools;

import cz.burios.uniql.persistence.jpa.jpql.parser.AsOfClause;
import cz.burios.uniql.persistence.jpa.jpql.parser.CastExpression;
import cz.burios.uniql.persistence.jpa.jpql.parser.ConnectByClause;
import cz.burios.uniql.persistence.jpa.jpql.parser.DatabaseType;
import cz.burios.uniql.persistence.jpa.jpql.parser.EclipseLinkExpressionVisitor;
import cz.burios.uniql.persistence.jpa.jpql.parser.ExtractExpression;
import cz.burios.uniql.persistence.jpa.jpql.parser.HierarchicalQueryClause;
import cz.burios.uniql.persistence.jpa.jpql.parser.OrderSiblingsByClause;
import cz.burios.uniql.persistence.jpa.jpql.parser.RegexpExpression;
import cz.burios.uniql.persistence.jpa.jpql.parser.StartWithClause;
import cz.burios.uniql.persistence.jpa.jpql.parser.TableExpression;
import cz.burios.uniql.persistence.jpa.jpql.parser.TableVariableDeclaration;
import cz.burios.uniql.persistence.jpa.jpql.parser.UnionClause;
import cz.burios.uniql.persistence.jpa.jpql.tools.resolver.ResolverBuilder;

/**
 * An implementation of a {@link ResolverBuilder} that adds support for EclipseLink extension.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.
 *
 * @version 2.5
 * @since 2.4
 * @author Pascal Filion
 */
public class EclipseLinkResolverBuilder extends ResolverBuilder
                                        implements EclipseLinkExpressionVisitor {

	/**
	 * Creates a new <code>EclipseLinkResolverBuilder</code>.
	 *
	 * @param queryContext The context used to query information about the JPQL query
	 */
	public EclipseLinkResolverBuilder(EclipseLinkJPQLQueryContext queryContext) {
		super(queryContext);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(AsOfClause expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(CastExpression expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(ConnectByClause expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(DatabaseType expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(ExtractExpression expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(HierarchicalQueryClause expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(OrderSiblingsByClause expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(RegexpExpression expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(StartWithClause expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(TableExpression expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(TableVariableDeclaration expression) {
		resolver = buildClassResolver(Object.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void visit(UnionClause expression) {
		resolver = buildClassResolver(Object.class);
	}
}