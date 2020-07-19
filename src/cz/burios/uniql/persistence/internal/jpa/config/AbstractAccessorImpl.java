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
package cz.burios.uniql.persistence.internal.jpa.config;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.converters.ConverterImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.ObjectTypeConverterImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.StructConverterImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.TypeConverterImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.AccessMethodsImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.HashPartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.PartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.PinnedPartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.RangePartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.ReplicationPartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.RoundRobinPartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.UnionPartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.config.partitioning.ValuePartitioningImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.PropertyMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ObjectTypeConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.StructConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.TypeConverterMetadata;
import cz.burios.uniql.persistence.jpa.config.AccessMethods;
import cz.burios.uniql.persistence.jpa.config.Converter;
import cz.burios.uniql.persistence.jpa.config.HashPartitioning;
import cz.burios.uniql.persistence.jpa.config.ObjectTypeConverter;
import cz.burios.uniql.persistence.jpa.config.Partitioning;
import cz.burios.uniql.persistence.jpa.config.PinnedPartitioning;
import cz.burios.uniql.persistence.jpa.config.Property;
import cz.burios.uniql.persistence.jpa.config.RangePartitioning;
import cz.burios.uniql.persistence.jpa.config.ReplicationPartitioning;
import cz.burios.uniql.persistence.jpa.config.RoundRobinPartitioning;
import cz.burios.uniql.persistence.jpa.config.StructConverter;
import cz.burios.uniql.persistence.jpa.config.TypeConverter;
import cz.burios.uniql.persistence.jpa.config.UnionPartitioning;
import cz.burios.uniql.persistence.jpa.config.ValuePartitioning;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractAccessorImpl<T extends MetadataAccessor, R> extends MetadataImpl<T> {

    public AbstractAccessorImpl(T t) {
        super(t);
        
        getMetadata().setConverters(new ArrayList<ConverterMetadata>());
        getMetadata().setObjectTypeConverters(new ArrayList<ObjectTypeConverterMetadata>());
        getMetadata().setProperties(new ArrayList<PropertyMetadata>());
        getMetadata().setStructConverters(new ArrayList<StructConverterMetadata>());
        getMetadata().setTypeConverters(new ArrayList<TypeConverterMetadata>());
    }
    
    public Converter addConverter() {
        ConverterImpl converter = new ConverterImpl();
        getMetadata().getConverters().add(converter.getMetadata());
        return converter;
    }
    
    public Property addProperty() {
        PropertyImpl property = new PropertyImpl();
        getMetadata().getProperties().add(property.getMetadata());
        return property;
    }
    
    public ObjectTypeConverter addObjectTypeConverter() {
        ObjectTypeConverterImpl converter = new ObjectTypeConverterImpl();
        getMetadata().getObjectTypeConverters().add(converter.getMetadata());
        return converter;
    }
    
    public StructConverter addStructConverter() {
        StructConverterImpl converter = new StructConverterImpl();
        getMetadata().getStructConverters().add(converter.getMetadata());
        return converter;
    }
    
    public TypeConverter addTypeConverter() {
        TypeConverterImpl converter = new TypeConverterImpl();
        getMetadata().getTypeConverters().add(converter.getMetadata());
        return converter;
    }
    
    public R setAccess(String access) {
        getMetadata().setAccess(access);
        return (R) this;
    }
    
    public AccessMethods setAccessMethods() {
        AccessMethodsImpl accessMethods = new AccessMethodsImpl();
        getMetadata().setAccessMethods(accessMethods.getMetadata());
        return accessMethods;
    }
    
    public Converter setConverter() {
        return addConverter();
    }
    
    public HashPartitioning setHashPartitioning() {
        HashPartitioningImpl hashPartitioning = new HashPartitioningImpl();
        getMetadata().setHashPartitioning(hashPartitioning.getMetadata());
        return hashPartitioning;
    }
    
    public R setName(String name) {
        getMetadata().setName(name);
        return (R) this;
    }
    
    public ObjectTypeConverter setObjectTypeConverter() {
        return addObjectTypeConverter();
    }
    
    public R setPartitioned(String partitioned) {
        getMetadata().setPartitioned(partitioned);
        return (R) this;
    }

    public Partitioning setPartitioning() {
        PartitioningImpl partitioning = new PartitioningImpl();
        getMetadata().setPartitioning(partitioning.getMetadata());
        return partitioning;
    }
    
    public PinnedPartitioning setPinnedPartitioning() {
        PinnedPartitioningImpl pinnedPartitioning = new PinnedPartitioningImpl();
        getMetadata().setPinnedPartitioning(pinnedPartitioning.getMetadata());
        return pinnedPartitioning;
    }
    
    public RangePartitioning setRangePartitioning() {
        RangePartitioningImpl rangePartitioning = new RangePartitioningImpl();
        getMetadata().setRangePartitioning(rangePartitioning.getMetadata());
        return rangePartitioning;
    }
    
    public ReplicationPartitioning setReplicationPartitioning() {
        ReplicationPartitioningImpl replicationPartitioning = new ReplicationPartitioningImpl();
        getMetadata().setReplicationPartitioning(replicationPartitioning.getMetadata());
        return replicationPartitioning;
    }
    
    public RoundRobinPartitioning setRoundRobinPartitioning() {
        RoundRobinPartitioningImpl roundRobinPartitioning = new RoundRobinPartitioningImpl();
        getMetadata().setRoundRobinPartitioning(roundRobinPartitioning.getMetadata());
        return roundRobinPartitioning;
    }

    public StructConverter setStructConverter() {
        return addStructConverter();
    }
    
    public TypeConverter setTypeConverter() {
        return addTypeConverter();
    }
    
    public UnionPartitioning setUnionPartitioning() {
        UnionPartitioningImpl unionPartitioning = new UnionPartitioningImpl();
        getMetadata().setUnionPartitioning(unionPartitioning.getMetadata());
        return unionPartitioning;
    }
    
    public ValuePartitioning setValuePartitioning() {
        ValuePartitioningImpl valuePartitioning = new ValuePartitioningImpl();
        getMetadata().setValuePartitioning(valuePartitioning.getMetadata());
        return valuePartitioning;
    }
}
