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

import cz.burios.uniql.persistence.jpa.jpql.tools.model.query.StateObject;

/**
 * The default implementation of a {@link ICaseExpressionStateObjectBuilder}.
 *
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
public class DefaultCaseExpressionStateObjectBuilder extends AbstractCaseExpressionStateObjectBuilder {

	/**
	 * Creates a new <code>DefaultCaseExpressionStateObjectBuilder</code>.
	 */
	public DefaultCaseExpressionStateObjectBuilder(StateObject parent) {
		super(parent);
	}
}