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

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.ColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.RangePartitionMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.RangePartitioningMetadata;
import cz.burios.uniql.persistence.jpa.config.Column;
import cz.burios.uniql.persistence.jpa.config.RangePartition;
import cz.burios.uniql.persistence.jpa.config.RangePartitioning;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class RangePartitioningImpl extends MetadataImpl<RangePartitioningMetadata> implements RangePartitioning {

    public RangePartitioningImpl() {
        super(new RangePartitioningMetadata());
        
        getMetadata().setPartitions(new ArrayList<RangePartitionMetadata>());
    }
    
    public RangePartition addPartition() {
        RangePartitionImpl rangePartition = new RangePartitionImpl();
        getMetadata().getPartitions().add(rangePartition.getMetadata());
        return rangePartition;
    }
    
    public RangePartitioning setName(String name) {
        getMetadata().setName(name);
        return this;
    }

    public Column setPartitionColumn() {
        ColumnImpl column = new ColumnImpl();
        getMetadata().setPartitionColumn(column.getMetadata());
        return column;
    }
    
    public RangePartitioning setPartitionValueType(String partitionValueType) {
        getMetadata().setPartitionValueTypeName(partitionValueType);
        return this;
    }

    public RangePartitioning setUnionUnpartitionableQueries(Boolean unionUnpartitionableQueries) {
        getMetadata().setUnionUnpartitionableQueries(unionUnpartitionableQueries);
        return this;
    }
    
}
