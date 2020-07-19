/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.jpa.jpql;

import java.util.Collections;
import java.util.List;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.jpa.jpql.ExpressionTools;
import cz.burios.uniql.persistence.jpa.jpql.JPQLQueryDeclaration;
import cz.burios.uniql.persistence.jpa.jpql.parser.AbstractExpression;
import cz.burios.uniql.persistence.jpa.jpql.parser.Expression;
import cz.burios.uniql.persistence.jpa.jpql.parser.IdentificationVariable;
import cz.burios.uniql.persistence.jpa.jpql.parser.Join;
import cz.burios.uniql.persistence.mappings.DatabaseMapping;

/**
 * A <code>Declaration</code> is the corresponding representation of a single declaration defined in
 * the <code><b>FROM</b></code> clause of a query.
 *
 * @version 2.5
 * @since 2.4
 * @author Pascal Filion
 */
abstract class Declaration implements JPQLQueryDeclaration {

	/**
	 * TODO.
	 */
	Expression baseExpression;

	/**
	 * The declaration expression, which is either an {@link cz.burios.uniql.persistence.jpa.jpql.parser.
	 * IdentificationVariableDeclaration IdentificationVariableDeclaration} or a {@link
	 * cz.burios.uniql.persistence.jpa.jpql.parser.CollectionMemberDeclaration CollectionMemberDeclaration}
	 * when part of a <b>FROM</b> clause, otherwise it's either the {@link cz.burios.uniql.persistence.jpa.jpql.parser.DeleteClause DeleteClause} or the {@link cz.burios.uniql.persistence.jpa.jpql.parser.UpdateClause UpdateClause}.
	 */
	Expression declarationExpression;

	/**
	 * The cached {@link ClassDescriptor} that represents this {@link Declaration}'s "root" path.
	 */
	private ClassDescriptor descriptor;

	/**
	 * The {@link IdentificationVariable} used to declare a "root" object.
	 */
	IdentificationVariable identificationVariable;

	/**
	 * The cached {@link DatabaseMapping} if this {@link Declaration}'s "root" path points to a
	 * mapping, otherwise it will be <code>null</code>.
	 */
	private DatabaseMapping mapping;

	/**
	 * The {@link JPQLQueryContext} is used to query information about the application metadata and
	 * cached information.
	 */
	final JPQLQueryContext queryContext;

	/**
	 * The {@link cz.burios.uniql.persistence.expressions.Expression Expression} representing the
	 * information of this {@link Declaration}.
	 */
	private cz.burios.uniql.persistence.expressions.Expression queryExpression;

	/**
	 * The "root" object for objects which may not be reachable by navigation, it is either the
	 * abstract schema name (entity name), a derived path expression (which is only defined in a
	 * subquery) or <code>null</code> if this {@link Declaration} is a collection member declaration.
	 */
	String rootPath;

	/**
	 * Creates a new <code>Declaration</code>.
	 *
	 * @param queryContext The context used to query information about the application metadata and
	 * cached information
	 */
	Declaration(JPQLQueryContext queryContext) {
		super();
		this.queryContext = queryContext;
	}

	/**
	 * Creates the Expression {@link Expression} for this {@link Declaration}.
	 *
	 * @return A new {@link cz.burios.uniql.persistence.expressions.Expression Expression}
	 */
	abstract cz.burios.uniql.persistence.expressions.Expression buildQueryExpression();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expression getBaseExpression() {
		return baseExpression;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expression getDeclarationExpression() {
		return declarationExpression;
	}

	/**
	 * Returns the {@link ClassDescriptor} that represents this {@link Declaration}'s "root" path.
	 *
	 * @return The descriptor of the "root" path
	 */
	final ClassDescriptor getDescriptor() {
		if (descriptor == null) {
			descriptor = resolveDescriptor();
		}
		return descriptor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Join> getJoins() {
		return Collections.emptyList();
	}

	/**
	 * Returns the {@link DatabaseMapping} that this {@link Declaration} represents, which may be
	 * <code>null</code> in the case it does not represent one.
	 *
	 * @return Either the {@link DatabaseMapping} of the "root" path, or <code>null</code> if the
	 * "root" path is not a mapping
	 */
	final DatabaseMapping getMapping() {
		if (mapping == null) {
			mapping = resolveMapping();
		}
		return mapping;
	}

	/**
	 * Returns the Expression {@link Expression} for this {@link Declaration}.
	 *
	 * @return The {@link cz.burios.uniql.persistence.expressions.Expression Expression} representing the
	 * information of this {@link Declaration}
	 */
	final cz.burios.uniql.persistence.expressions.Expression getQueryExpression() {

		if (queryExpression == null) {

			// First create the Expression
			queryExpression = buildQueryExpression();

			// Cache the base expression with its identification variable as well
			queryContext.addQueryExpressionImp(getVariableName(), queryExpression);
		}

		return queryExpression;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getVariableName() {
		if (identificationVariable == null) {
			return ExpressionTools.EMPTY_STRING;
		}
		return identificationVariable.getVariableName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasJoins() {
		return false;
	}

	/**
	 * Resolves this {@link Declaration} and returns the associated {@link ClassDescriptor}. For a
	 * {@link RangeVariableDeclaration}, this will return the actual descriptor for the entity. For
	 * a mapping, this will return the reference descriptor.
	 *
	 * @return The descriptor associated with this declaration
	 */
	abstract ClassDescriptor resolveDescriptor();

	/**
	 * Resolves this {@link Declaration} and returns the associated {@link DatabaseMapping} if this
	 * represents a path expression. In the case of a {@link RangeVariableDeclaration}, this will
	 * return <code>null</code>.
	 *
	 * @return The mapping associated with this declaration
	 */
	abstract DatabaseMapping resolveMapping();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		if (declarationExpression != null) {
			return declarationExpression.toParsedText();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(rootPath);

		if (identificationVariable != null) {
			sb.append(AbstractExpression.SPACE);
			sb.append(identificationVariable.getText());
		}

		return sb.toString();
	}
}