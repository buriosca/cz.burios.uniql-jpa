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
package cz.burios.uniql.persistence.jpa.jpql.tools.model;

import cz.burios.uniql.persistence.jpa.jpql.parser.DefaultEclipseLinkJPQLGrammar;

/**
 * An implementation of {@link IJPQLQueryBuilder} that provides support based on the latest release
 * of the Java Persistence functional specification. The current version of the functional
 * specification is <a href="http://jcp.org/en/jsr/detail?id=317">JSR-337 Java Persistence 2.0</a>.
 * Also adds support for the latest release of EclipseLink, which is version 2.4.
 *
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
public final class DefaultEclipseLinkJPQLQueryBuilder extends JPQLQueryBuilderWrapper {

	/**
	 * Creates a new <code>DefaultEclipseLinkJPQLQueryBuilder</code>.
	 */
	public DefaultEclipseLinkJPQLQueryBuilder() {
		super(new EclipseLinkJPQLQueryBuilder(DefaultEclipseLinkJPQLGrammar.instance()));
	}
}