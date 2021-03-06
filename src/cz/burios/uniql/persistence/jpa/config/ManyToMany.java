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
package cz.burios.uniql.persistence.jpa.config;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public interface ManyToMany {
    
    public JoinField addJoinField();
    public AssociationOverride addMapKeyAssociationOverride();
    public AttributeOverride addMapKeyAttributeOverride();
    public Convert addMapKeyConvert();
    public JoinColumn addMapKeyJoinColumn();
    public Property addProperty();
    public ManyToMany setAccess(String access);
    public AccessMethods setAccessMethods();
    public ManyToMany setAttributeType(String attributeType);
    public BatchFetch setBatchFetch();
    public Cascade setCascade();
    public ManyToMany setCascadeOnDelete(Boolean cascadeOnDelete);
    public Converter setConverter();
    public ManyToMany setFetch(String fetch);
    public HashPartitioning setHashPartitioning();
    public ManyToMany setJoinFetch(String joinFetch);
    public JoinTable setJoinTable();
    public MapKey setMapKey();
    public ManyToMany setMapKeyClass(String mapKeyClass);
    public Column setMapKeyColumn();
    public ManyToMany setMapKeyConvert(String mapKeyConvert);
    public Enumerated setMapKeyEnumerated();
    public ForeignKey setMapKeyForeignKey();
    public Temporal setMapKeyTemporal();
    public ManyToMany setMappedBy(String mappedBy);
    public ManyToMany setName(String name);
    public ManyToMany setNonCacheable(Boolean nonCacheable);
    public ObjectTypeConverter setObjectTypeConverter();
    public ManyToMany setOrderBy(String orderBy);
    public OrderColumn setOrderColumn();
    public Partitioning setPartitioning();
    public PinnedPartitioning setPinnedPartitioning();
    public RangePartitioning setRangePartitioning();
    public ReplicationPartitioning setReplicationPartitioning();
    public RoundRobinPartitioning setRoundRobinPartitioning();
    public StructConverter setStructConverter();
    public ManyToMany setTargetEntity(String targetEntity);
    public TypeConverter setTypeConverter();
    public UnionPartitioning setUnionPartitioning();
    public ValuePartitioning setValuePartitioning();
    
}
