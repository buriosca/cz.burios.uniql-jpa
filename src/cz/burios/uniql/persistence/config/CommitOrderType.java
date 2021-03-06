/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
 
 package cz.burios.uniql.persistence.config;


/**
 * Commit order type persistence property values.
 * 
 * <p>JPA persistence property Usage:
 * 
 * <p><code>properties.add(PersistenceUnitProperties.PERSISTENCE_CONTEXT_COMMIT_ORDER, CommitOrderType.Changes);</code>
 * <p>Property values are case-insensitive.
 * 
 * Defines the ordering of updates and deletes of a set of the same entity type during a commit or flush operation.
 * The commit order of entities is defined by their foreign key constraints, and then sorted alphabetically.\
 * <p>
 * By default the commit of a set of the same entity type is not ordered.
 * <p>
 * Entity type commit order can be modified using a DescriptorCustomizer and the ClassDescriptor.addConstraintDependency() API.
 * Commit order can also be controlled using the EntityManager.flush() API.
 */
public class CommitOrderType {
    /** Updates and deletes are ordered by the object's id.  This can help avoid deadlocks on highly concurrent systems. */
    public static final String Id = "Id";
    /** Updates are ordered by the object's changes, then by id.  This can improve batch writing efficiency. */
    public static final String Changes = "Changes";
    /** No ordering is done. */
    public static final String None = "None";

    public static final String DEFAULT = Id;
}

