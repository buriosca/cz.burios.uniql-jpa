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

import static cz.burios.uniql.persistence.jpa.jpql.parser.Expression.*;

import cz.burios.uniql.persistence.jpa.jpql.tools.model.query.KeywordExpressionStateObject;
import cz.burios.uniql.persistence.jpa.jpql.tools.model.query.StateObject;
import cz.burios.uniql.persistence.jpa.jpql.tools.model.query.UpdateItemStateObject;

/**
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
public abstract class AbstractNewValueStateObjectBuilder extends AbstractScalarExpressionStateObjectBuilder<INewValueStateObjectBuilder>
                                                         implements INewValueStateObjectBuilder {

	/**
	 * Creates a new <code>AbstractNewValueStateObjectBuilder</code>.
	 *
	 * @param parent The parent of the expression to build, which is only required when a JPQL
	 * fragment needs to be parsed
	 */
	protected AbstractNewValueStateObjectBuilder(UpdateItemStateObject parent) {
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() {
		getParent().setNewValue(pop());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UpdateItemStateObject getParent() {
		return (UpdateItemStateObject) super.getParent();
	}

	/**
	 * {@inheritDoc}
	 */
	public INewValueStateObjectBuilder NULL() {
		StateObject stateObject = new KeywordExpressionStateObject(getParent(), NULL);
		add(stateObject);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public INewValueStateObjectBuilder variable(String variable) {
		StateObject stateObject = buildIdentificationVariable(variable);
		add(stateObject);
		return this;
	}
}