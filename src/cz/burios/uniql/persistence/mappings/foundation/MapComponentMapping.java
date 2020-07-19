/*******************************************************************************
 * Copyright (c) 1998, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     tware - initial API check-in for MappedKeyMapContainerPolicy
 *     14/05/2012-2.4 Guy Pelletier   
 *       - 376603: Provide for table per tenant support for multitenant applications
 ******************************************************************************/
package cz.burios.uniql.persistence.mappings.foundation;

import cz.burios.uniql.persistence.internal.identitymaps.CacheKey;
import cz.burios.uniql.persistence.internal.sessions.AbstractRecord;
import cz.burios.uniql.persistence.internal.sessions.AbstractSession;
import cz.burios.uniql.persistence.queries.ObjectBuildingQuery;

/**
 * A MapComponentMapping is any mapping that can be used as the key or the value
 * in a mapping that uses a MappedKeyMapContainerPolicy.  This interface is generally implemented
 * by mappings that provide the value in a mapping to a Map.  Mappings that provide the key generally 
 * implement sub-interface MapKeyMapping
 * 
 * @see cz.burios.uniql.persistence.internal.queries.MappedKeyMapContainerPolicy
 * @see cz.burios.uniql.persistence.mappings.foundation.MapKeyMapping
 * @see cz.burios.uniql.persistence.mappings.DirectCollectionMapping
 * @see cz.burios.uniql.persistence.mappings.AggregateCollectionMapping
 * @see cz.burios.uniql.persistence.mappings.OneToManyMapping
 * @see cz.burios.uniql.persistence.mappings.UnidirectionalOneToManyMapping
 * @see cz.burios.uniql.persistence.mappings.ManyToManyMapping
 * @author tware
 *
 */
public interface MapComponentMapping {

    /**
     * INTERNAL
     * Called when a DatabaseMapping is used to map the key in a collection.  Returns the key.
     */
    public Object createMapComponentFromRow(AbstractRecord dbRow, ObjectBuildingQuery query, CacheKey parentCacheKey, AbstractSession session, boolean isTargetProtected);

    /**
     * INTERNAL
     * Called when cloning the container policy.
     */
    public Object clone();
}
