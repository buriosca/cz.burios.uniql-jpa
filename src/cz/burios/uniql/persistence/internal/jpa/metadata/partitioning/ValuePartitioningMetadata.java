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
 *     James Sutherland - initial API and implementation
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 *     30/05/2012-2.4 Guy Pelletier    
 *       - 354678: Temp classloader is still being used during metadata processing
 *     11/19/2012-2.5 Guy Pelletier 
 *       - 389090: JPA 2.1 DDL Generation Support (foreign key metadata support)
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.partitioning;

import java.util.ArrayList;
import java.util.List;

import cz.burios.uniql.persistence.descriptors.partitioning.PartitioningPolicy;
import cz.burios.uniql.persistence.descriptors.partitioning.ValuePartitioningPolicy;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAccessibleObject;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;
import cz.burios.uniql.persistence.internal.jpa.metadata.xml.XMLEntityMappings;

/**
 * INTERNAL:
 * Define JPA meta-data for partitioning policy.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - any metadata mapped from XML to this class must be initialized in the
 *   initXMLObject method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @author James Sutherland
 * @since EclipseLink 2.2
 */
public class ValuePartitioningMetadata extends FieldPartitioningMetadata {
    protected List<ValuePartitionMetadata> partitions;
    protected String defaultConnectionPool;

    /**
     * INTERNAL:
     * Used for XML loading.
     */
    public ValuePartitioningMetadata() {
        super("<value-partitioning>");
    }

    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    public ValuePartitioningMetadata(MetadataAnnotation annotation, MetadataAccessor accessor) {
        super(annotation, accessor);
        
        this.defaultConnectionPool = annotation.getAttributeString("defaultConnectionPool");
        
        this.partitions = new ArrayList<ValuePartitionMetadata>();
        for (Object partitionAnnotation : annotation.getAttributeArray("partitions")) {
            this.partitions.add(new ValuePartitionMetadata((MetadataAnnotation) partitionAnnotation, accessor));
        }
    }

    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (super.equals(objectToCompare) && (objectToCompare instanceof ValuePartitioningMetadata)) {
            ValuePartitioningMetadata policy = (ValuePartitioningMetadata) objectToCompare;
            
            if (!valuesMatch(this.defaultConnectionPool, policy.getDefaultConnectionPool())) {
                return false;
            }
            
            return valuesMatch(this.partitions, policy.getPartitions());
        }
        
        return false;
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public void initXMLObject(MetadataAccessibleObject accessibleObject, XMLEntityMappings entityMappings) {
        super.initXMLObject(accessibleObject, entityMappings);
        
        // Initialize list of objects.
        initXMLObjects(partitions, accessibleObject);
    }

    /**
     * INTERNAL:
     * Build/process the partitioning policy.
     */
    @Override
    public PartitioningPolicy buildPolicy() {
        ValuePartitioningPolicy policy = new ValuePartitioningPolicy();
        super.buildPolicy(policy);
        
        // Set the partition value type name. Will be used to initialize the
        // individual partition values.
        policy.setPartitionValueTypeName(getPartitionValueType().getName());

        // Set the default connection pool.
        policy.setDefaultConnectionPool(this.defaultConnectionPool);
        
        // Set only the partition value names (they will be initialized at runtime).
        for (ValuePartitionMetadata partition : getPartitions()) {
            policy.addPartitionName(partition.getValue(), partition.getConnectionPool());
        }
        
        return policy;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getDefaultConnectionPool() {
        return defaultConnectionPool;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public List<ValuePartitionMetadata> getPartitions() {
        return partitions;
    }

    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setDefaultConnectionPool(String defaultConnectionPool) {
        this.defaultConnectionPool = defaultConnectionPool;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setPartitions(List<ValuePartitionMetadata> partitions) {
        this.partitions = partitions;
    }
}
