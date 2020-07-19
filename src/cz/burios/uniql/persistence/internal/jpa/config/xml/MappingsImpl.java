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
package cz.burios.uniql.persistence.internal.jpa.config.xml;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.config.classes.ConverterClassImpl;
import cz.burios.uniql.persistence.internal.jpa.config.classes.EmbeddableImpl;
import cz.burios.uniql.persistence.internal.jpa.config.classes.EntityImpl;
import cz.burios.uniql.persistence.internal.jpa.config.classes.MappedSuperclassImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.TenantDiscriminatorColumnImpl;
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
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedNativeQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedPlsqlStoredFunctionQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedPlsqlStoredProcedureQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedStoredFunctionQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedStoredProcedureQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.OracleArrayImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.OracleObjectImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.PlsqlRecordImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.PlsqlTableImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.SqlResultSetMappingImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.SequenceGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.TableGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.UuidGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.ConverterAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.EmbeddableAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.EntityAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.MappedSuperclassAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.TenantDiscriminatorColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.MixedConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ObjectTypeConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.SerializedConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.StructConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.TypeConverterMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.HashPartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.PartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.PinnedPartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.RangePartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.ReplicationPartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.RoundRobinPartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.UnionPartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.partitioning.ValuePartitioningMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedNativeQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedPLSQLStoredFunctionQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedPLSQLStoredProcedureQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedStoredFunctionQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedStoredProcedureQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.OracleArrayTypeMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.OracleObjectTypeMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.PLSQLRecordMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.PLSQLTableMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.SQLResultSetMappingMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.sequencing.SequenceGeneratorMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.sequencing.TableGeneratorMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.sequencing.UuidGeneratorMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.xml.XMLEntityMappings;
import cz.burios.uniql.persistence.jpa.config.AccessMethods;
import cz.burios.uniql.persistence.jpa.config.Converter;
import cz.burios.uniql.persistence.jpa.config.ConverterClass;
import cz.burios.uniql.persistence.jpa.config.Embeddable;
import cz.burios.uniql.persistence.jpa.config.Entity;
import cz.burios.uniql.persistence.jpa.config.HashPartitioning;
import cz.burios.uniql.persistence.jpa.config.MappedSuperclass;
import cz.burios.uniql.persistence.jpa.config.Mappings;
import cz.burios.uniql.persistence.jpa.config.NamedNativeQuery;
import cz.burios.uniql.persistence.jpa.config.NamedPlsqlStoredFunctionQuery;
import cz.burios.uniql.persistence.jpa.config.NamedPlsqlStoredProcedureQuery;
import cz.burios.uniql.persistence.jpa.config.NamedQuery;
import cz.burios.uniql.persistence.jpa.config.NamedStoredFunctionQuery;
import cz.burios.uniql.persistence.jpa.config.NamedStoredProcedureQuery;
import cz.burios.uniql.persistence.jpa.config.ObjectTypeConverter;
import cz.burios.uniql.persistence.jpa.config.OracleArray;
import cz.burios.uniql.persistence.jpa.config.OracleObject;
import cz.burios.uniql.persistence.jpa.config.Partitioning;
import cz.burios.uniql.persistence.jpa.config.PersistenceUnitMetadata;
import cz.burios.uniql.persistence.jpa.config.PinnedPartitioning;
import cz.burios.uniql.persistence.jpa.config.PlsqlRecord;
import cz.burios.uniql.persistence.jpa.config.PlsqlTable;
import cz.burios.uniql.persistence.jpa.config.RangePartitioning;
import cz.burios.uniql.persistence.jpa.config.ReplicationPartitioning;
import cz.burios.uniql.persistence.jpa.config.RoundRobinPartitioning;
import cz.burios.uniql.persistence.jpa.config.SequenceGenerator;
import cz.burios.uniql.persistence.jpa.config.SqlResultSetMapping;
import cz.burios.uniql.persistence.jpa.config.StructConverter;
import cz.burios.uniql.persistence.jpa.config.TableGenerator;
import cz.burios.uniql.persistence.jpa.config.TenantDiscriminatorColumn;
import cz.burios.uniql.persistence.jpa.config.TypeConverter;
import cz.burios.uniql.persistence.jpa.config.UnionPartitioning;
import cz.burios.uniql.persistence.jpa.config.UuidGenerator;
import cz.burios.uniql.persistence.jpa.config.ValuePartitioning;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class MappingsImpl extends MetadataImpl<XMLEntityMappings> implements Mappings {

    public MappingsImpl() {
        super(new XMLEntityMappings());
        
        getMetadata().setConverters(new ArrayList<ConverterMetadata>());
        getMetadata().setConverterAccessors(new ArrayList<ConverterAccessor>());
        getMetadata().setEmbeddables(new ArrayList<EmbeddableAccessor>());
        getMetadata().setEntities(new ArrayList<EntityAccessor>());
        getMetadata().setHashPartitioning(new ArrayList<HashPartitioningMetadata>());
        getMetadata().setMappedSuperclasses(new ArrayList<MappedSuperclassAccessor>());
        getMetadata().setMixedConverters(new ArrayList<MixedConverterMetadata>());
        getMetadata().setNamedQueries(new ArrayList<NamedQueryMetadata>());
        getMetadata().setNamedStoredFunctionQueries(new ArrayList<NamedStoredFunctionQueryMetadata>());
        getMetadata().setNamedNativeQueries(new ArrayList<NamedNativeQueryMetadata>());
        getMetadata().setNamedPLSQLStoredFunctionQueries(new ArrayList<NamedPLSQLStoredFunctionQueryMetadata>());
        getMetadata().setNamedPLSQLStoredProcedureQueries(new ArrayList<NamedPLSQLStoredProcedureQueryMetadata>());
        getMetadata().setNamedStoredProcedureQueries(new ArrayList<NamedStoredProcedureQueryMetadata>());
        getMetadata().setObjectTypeConverters(new ArrayList<ObjectTypeConverterMetadata>());
        getMetadata().setOracleArrayTypes(new ArrayList<OracleArrayTypeMetadata>());
        getMetadata().setOracleObjectTypes(new ArrayList<OracleObjectTypeMetadata>());
        getMetadata().setPartitioning(new ArrayList<PartitioningMetadata>());
        getMetadata().setPinnedPartitioning(new ArrayList<PinnedPartitioningMetadata>());
        getMetadata().setPLSQLRecords(new ArrayList<PLSQLRecordMetadata>());
        getMetadata().setPLSQLTables(new ArrayList<PLSQLTableMetadata>());
        getMetadata().setRangePartitioning(new ArrayList<RangePartitioningMetadata>());
        getMetadata().setReplicationPartitioning(new ArrayList<ReplicationPartitioningMetadata>());
        getMetadata().setRoundRobinPartitioning(new ArrayList<RoundRobinPartitioningMetadata>());
        getMetadata().setSequenceGenerators(new ArrayList<SequenceGeneratorMetadata>());
        getMetadata().setSerializedConverters(new ArrayList<SerializedConverterMetadata>()); // TODO: add to config
        getMetadata().setSqlResultSetMappings(new ArrayList<SQLResultSetMappingMetadata>());
        getMetadata().setStructConverters(new ArrayList<StructConverterMetadata>());
        getMetadata().setTableGenerators(new ArrayList<TableGeneratorMetadata>());
        getMetadata().setTenantDiscriminatorColumns(new ArrayList<TenantDiscriminatorColumnMetadata>());
        getMetadata().setTypeConverters(new ArrayList<TypeConverterMetadata>());
        getMetadata().setUnionPartitioning(new ArrayList<UnionPartitioningMetadata>());
        getMetadata().setUuidGenerators(new ArrayList<UuidGeneratorMetadata>());
        getMetadata().setValuePartitioning(new ArrayList<ValuePartitioningMetadata>());
    }

    public Converter addConverter() {
        ConverterImpl converter = new ConverterImpl();
        getMetadata().getConverters().add(converter.getMetadata());
        return converter;
    }
    
    public ConverterClass addConverterClass() {
        ConverterClassImpl converterClass = new ConverterClassImpl();
        getMetadata().getConverterAccessors().add(converterClass.getMetadata());
        return converterClass;
    }

    public Embeddable addEmbeddable() {
        EmbeddableImpl embeddable = new EmbeddableImpl();
        getMetadata().getEmbeddables().add(embeddable.getMetadata());
        return embeddable;
    }
    
    public Entity addEntity() {
        EntityImpl entity = new EntityImpl();
        getMetadata().getEntities().add(entity.getMetadata());
        return entity;
    }

    public HashPartitioning addHashPartitioning() {
        HashPartitioningImpl partitioning = new HashPartitioningImpl();
        getMetadata().getHashPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public MappedSuperclass addMappedSuperclass() {
        MappedSuperclassImpl mappedSuperclass = new MappedSuperclassImpl();
        getMetadata().getMappedSuperclasses().add(mappedSuperclass.getMetadata());
        return mappedSuperclass;
    }

    public NamedNativeQuery addNamedNativeQuery() {
        NamedNativeQueryImpl query = new NamedNativeQueryImpl();
        getMetadata().getNamedNativeQueries().add(query.getMetadata());
        return query;
    }

    public NamedPlsqlStoredFunctionQuery addNamedPlsqlStoredFunctionQuery() {
        NamedPlsqlStoredFunctionQueryImpl query = new NamedPlsqlStoredFunctionQueryImpl();
        getMetadata().getNamedPLSQLStoredFunctionQueries().add(query.getMetadata());
        return query;
    }

    public NamedPlsqlStoredProcedureQuery addNamedPlsqlStoredProcedureQuery() {
        NamedPlsqlStoredProcedureQueryImpl query = new NamedPlsqlStoredProcedureQueryImpl();
        getMetadata().getNamedPLSQLStoredProcedureQueries().add(query.getMetadata());
        return query;
    }

    public NamedQuery addNamedQuery() {
        NamedQueryImpl query = new NamedQueryImpl();
        getMetadata().getNamedQueries().add(query.getMetadata());
        return query;
    }

    public NamedStoredFunctionQuery addNamedStoredFunctionQuery() {
        NamedStoredFunctionQueryImpl query = new NamedStoredFunctionQueryImpl();
        getMetadata().getNamedStoredFunctionQueries().add(query.getMetadata());
        return query;
    }

    public NamedStoredProcedureQuery addNamedStoredProcedureQuery() {
        NamedStoredProcedureQueryImpl query = new NamedStoredProcedureQueryImpl();
        getMetadata().getNamedStoredProcedureQueries().add(query.getMetadata());
        return query;
    }

    public ObjectTypeConverter addObjectTypeConverter() {
        ObjectTypeConverterImpl converter = new ObjectTypeConverterImpl();
        getMetadata().getObjectTypeConverters().add(converter.getMetadata());
        return converter;
    }
    
    public OracleArray addOracleArray() {
        OracleArrayImpl oracleArray = new OracleArrayImpl();
        getMetadata().getOracleArrayTypes().add(oracleArray.getMetadata());
        return oracleArray;
    }
    
    public OracleObject addOracleObject() {
        OracleObjectImpl oracleObject = new OracleObjectImpl();
        getMetadata().getOracleObjectTypes().add(oracleObject.getMetadata());
        return oracleObject;
    }

    public Partitioning addPartitioning() {
        PartitioningImpl partitioning = new PartitioningImpl();
        getMetadata().getPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public PinnedPartitioning addPinnedPartitioning() {
        PinnedPartitioningImpl partitioning = new PinnedPartitioningImpl();
        getMetadata().getPinnedPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public PlsqlRecord addPlsqlRecord() {
        PlsqlRecordImpl plsqlRecord = new PlsqlRecordImpl();
        getMetadata().getPLSQLRecords().add(plsqlRecord.getMetadata());
        return plsqlRecord;
    }

    public PlsqlTable addPlsqlTable() {
        PlsqlTableImpl plsqlTable = new PlsqlTableImpl();
        getMetadata().getPLSQLTables().add(plsqlTable.getMetadata());
        return plsqlTable;
    }

    public RangePartitioning addRangePartitioning() {
        RangePartitioningImpl partitioning = new RangePartitioningImpl();
        getMetadata().getRangePartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public ReplicationPartitioning addReplicationPartititioning() {
        ReplicationPartitioningImpl partitioning = new ReplicationPartitioningImpl();
        getMetadata().getReplicationPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public RoundRobinPartitioning addRoundRobinPartitioning() {
        RoundRobinPartitioningImpl partitioning = new RoundRobinPartitioningImpl();
        getMetadata().getRoundRobinPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public SequenceGenerator addSequenceGenerator() {
        SequenceGeneratorImpl generator = new SequenceGeneratorImpl();
        getMetadata().getSequenceGenerators().add(generator.getMetadata());
        return generator;
    }

    public SqlResultSetMapping addSqlResultSetMapping() {
        SqlResultSetMappingImpl sqlResultSetMapping = new SqlResultSetMappingImpl();
        getMetadata().getSqlResultSetMappings().add(sqlResultSetMapping.getMetadata());
        return sqlResultSetMapping;
    }

    public StructConverter addStructConverter() {
        StructConverterImpl converter = new StructConverterImpl();
        getMetadata().getStructConverters().add(converter.getMetadata());
        return converter;
    }

    public TableGenerator addTableGenerator() {
        TableGeneratorImpl generator = new TableGeneratorImpl();
        getMetadata().getTableGenerators().add(generator.getMetadata());
        return generator;
    }
    
    public TenantDiscriminatorColumn addTenantDiscriminatorColumn() {
        TenantDiscriminatorColumnImpl column = new TenantDiscriminatorColumnImpl();
        getMetadata().getTenantDiscriminatorColumns().add(column.getMetadata());
        return column;
    }

    public TypeConverter addTypeConverter() {
        TypeConverterImpl converter = new TypeConverterImpl();
        getMetadata().getTypeConverters().add(converter.getMetadata());
        return converter;
    }

    public UnionPartitioning addUnionPartitioning() {
        UnionPartitioningImpl partitioning = new UnionPartitioningImpl();
        getMetadata().getUnionPartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public UuidGenerator addUuidGenerator() {
        UuidGeneratorImpl generator = new UuidGeneratorImpl();
        getMetadata().getUuidGenerators().add(generator.getMetadata());
        return null;
    }

    public ValuePartitioning addValuePartitioning() {
        ValuePartitioningImpl partitioning = new ValuePartitioningImpl();
        getMetadata().getValuePartitioning().add(partitioning.getMetadata());
        return partitioning;
    }

    public Mappings setAccess(String access) {
        getMetadata().setAccess(access);
        return this;
    }

    public AccessMethods setAccessMethods() {
        AccessMethodsImpl accessMethods = new AccessMethodsImpl();
        getMetadata().setAccessMethods(accessMethods.getMetadata());
        return accessMethods;
    }

    public Mappings setCatalog(String catalog) {
        getMetadata().setCatalog(catalog);
        return this;
    }

    public Mappings setPackage(String pkg) {
        getMetadata().setPackage(pkg);
        return this;
    }

    public PersistenceUnitMetadata setPersistenceUnitMetadata() {
        PersistenceUnitMetadataImpl persistenceUnit = new PersistenceUnitMetadataImpl();
        getMetadata().setPersistenceUnitMetadata(persistenceUnit.getMetadata());
        return persistenceUnit;
    }

    public Mappings setSchema(String schema) {
        getMetadata().setSchema(schema);
        return this;
    }

    public Mappings setVersion(String version) {
        getMetadata().setVersion(version);
        return this;
    }
    
}
