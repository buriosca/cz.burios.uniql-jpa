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
 *     Guy Pelletier - initial API and implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.jpa.config.partitioning;

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.PartitioningMetadata;
import cz.burios.uniql.persistence.jpa.config.Partitioning;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class PartitioningImpl extends MetadataImpl<PartitioningMetadata> implements Partitioning {

    public PartitioningImpl() {
        super(new PartitioningMetadata());
    }
    
    public Partitioning setClass(String className) {
        getMetadata().setClassName(className);
        return this;
    }
    
    public Partitioning setName(String name) {
        getMetadata().setName(name);
        return this;
    }
    
}
