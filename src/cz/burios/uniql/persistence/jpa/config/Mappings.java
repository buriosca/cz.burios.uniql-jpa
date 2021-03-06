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
public interface Mappings {

    public Converter addConverter();
    public ConverterClass addConverterClass();
    public Embeddable addEmbeddable();
    public Entity addEntity();
    public HashPartitioning addHashPartitioning();
    public MappedSuperclass addMappedSuperclass();
    public NamedNativeQuery addNamedNativeQuery();
    public NamedPlsqlStoredFunctionQuery addNamedPlsqlStoredFunctionQuery();
    public NamedPlsqlStoredProcedureQuery addNamedPlsqlStoredProcedureQuery();
    public NamedQuery addNamedQuery();
    public NamedStoredFunctionQuery addNamedStoredFunctionQuery();
    public NamedStoredProcedureQuery addNamedStoredProcedureQuery();
    public ObjectTypeConverter addObjectTypeConverter();
    public OracleArray addOracleArray();
    public OracleObject addOracleObject();
    public Partitioning addPartitioning();
    public PinnedPartitioning addPinnedPartitioning();
    public PlsqlRecord addPlsqlRecord();
    public PlsqlTable addPlsqlTable();
    public RangePartitioning addRangePartitioning();
    public ReplicationPartitioning addReplicationPartititioning();
    public RoundRobinPartitioning addRoundRobinPartitioning();
    public SequenceGenerator addSequenceGenerator();
    public SqlResultSetMapping addSqlResultSetMapping();
    public StructConverter addStructConverter();
    public TableGenerator addTableGenerator();
    public TenantDiscriminatorColumn addTenantDiscriminatorColumn();
    public TypeConverter addTypeConverter();
    public UnionPartitioning addUnionPartitioning();
    public UuidGenerator addUuidGenerator();
    public ValuePartitioning addValuePartitioning();    
    public Mappings setAccess(String access);
    public AccessMethods setAccessMethods();
    public Mappings setCatalog(String catalog);
    public Mappings setPackage(String pkg);
    public PersistenceUnitMetadata setPersistenceUnitMetadata();
    public Mappings setSchema(String schema);
    public Mappings setVersion(String version);

}