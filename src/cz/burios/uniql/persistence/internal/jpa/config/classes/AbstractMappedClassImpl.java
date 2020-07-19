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
package cz.burios.uniql.persistence.internal.jpa.config.classes;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.additionalcriteria.AdditionalCriteriaImpl;
import cz.burios.uniql.persistence.internal.jpa.config.cache.CacheImpl;
import cz.burios.uniql.persistence.internal.jpa.config.cache.CacheIndexImpl;
import cz.burios.uniql.persistence.internal.jpa.config.cache.CacheInterceptorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.PrimaryKeyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.listeners.EntityListenerImpl;
import cz.burios.uniql.persistence.internal.jpa.config.locking.OptimisticLockingImpl;
import cz.burios.uniql.persistence.internal.jpa.config.multitenant.MultitenantImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.FetchGroupImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedNativeQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedPlsqlStoredFunctionQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedPlsqlStoredProcedureQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedStoredFunctionQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.NamedStoredProcedureQueryImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.QueryRedirectorsImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.SqlResultSetMappingImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.SequenceGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.TableGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.UuidGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.MappedSuperclassAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.cache.CacheIndexMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.listeners.EntityListenerMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.FetchGroupMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedNativeQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedPLSQLStoredFunctionQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedPLSQLStoredProcedureQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedStoredFunctionQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.NamedStoredProcedureQueryMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.SQLResultSetMappingMetadata;
import cz.burios.uniql.persistence.jpa.config.AdditionalCriteria;
import cz.burios.uniql.persistence.jpa.config.Cache;
import cz.burios.uniql.persistence.jpa.config.CacheIndex;
import cz.burios.uniql.persistence.jpa.config.CacheInterceptor;
import cz.burios.uniql.persistence.jpa.config.EntityListener;
import cz.burios.uniql.persistence.jpa.config.FetchGroup;
import cz.burios.uniql.persistence.jpa.config.Multitenant;
import cz.burios.uniql.persistence.jpa.config.NamedNativeQuery;
import cz.burios.uniql.persistence.jpa.config.NamedPlsqlStoredFunctionQuery;
import cz.burios.uniql.persistence.jpa.config.NamedPlsqlStoredProcedureQuery;
import cz.burios.uniql.persistence.jpa.config.NamedQuery;
import cz.burios.uniql.persistence.jpa.config.NamedStoredFunctionQuery;
import cz.burios.uniql.persistence.jpa.config.NamedStoredProcedureQuery;
import cz.burios.uniql.persistence.jpa.config.OptimisticLocking;
import cz.burios.uniql.persistence.jpa.config.PrimaryKey;
import cz.burios.uniql.persistence.jpa.config.QueryRedirectors;
import cz.burios.uniql.persistence.jpa.config.SequenceGenerator;
import cz.burios.uniql.persistence.jpa.config.SqlResultSetMapping;
import cz.burios.uniql.persistence.jpa.config.TableGenerator;
import cz.burios.uniql.persistence.jpa.config.UuidGenerator;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public abstract class AbstractMappedClassImpl<T extends MappedSuperclassAccessor, R> extends AbstractClassImpl<T, R> {

    public AbstractMappedClassImpl(T t) {
        super(t);
        
        getMetadata().setCacheIndexes(new ArrayList<CacheIndexMetadata>());
        getMetadata().setEntityListeners(new ArrayList<EntityListenerMetadata>());
        getMetadata().setFetchGroups(new ArrayList<FetchGroupMetadata>());
        getMetadata().setNamedNativeQueries(new ArrayList<NamedNativeQueryMetadata>());
        getMetadata().setNamedQueries(new ArrayList<NamedQueryMetadata>());
        getMetadata().setNamedStoredFunctionQueries(new ArrayList<NamedStoredFunctionQueryMetadata>());
        getMetadata().setNamedStoredProcedureQueries(new ArrayList<NamedStoredProcedureQueryMetadata>());
        getMetadata().setNamedPLSQLStoredFunctionQueries(new ArrayList<NamedPLSQLStoredFunctionQueryMetadata>());
        getMetadata().setNamedPLSQLStoredProcedureQueries(new ArrayList<NamedPLSQLStoredProcedureQueryMetadata>());        
        getMetadata().setSqlResultSetMappings(new ArrayList<SQLResultSetMappingMetadata>());
    }
    
    public CacheIndex addCacheIndex() {
        CacheIndexImpl cacheIndex = new CacheIndexImpl();
        getMetadata().getCacheIndexes().add(cacheIndex.getMetadata());
        return cacheIndex;
    }
    
    public EntityListener addEntityListener() {
        EntityListenerImpl listener = new EntityListenerImpl();
        getMetadata().getEntityListeners().add(listener.getMetadata());
        return listener;
    }
    
    public FetchGroup addFetchGroup() {
        FetchGroupImpl fetchGroup = new FetchGroupImpl();
        getMetadata().getFetchGroups().add(fetchGroup.getMetadata());
        return fetchGroup;
    }
    
    public NamedNativeQuery addNamedNativeQuery() {
        NamedNativeQueryImpl query = new NamedNativeQueryImpl();
        getMetadata().getNamedNativeQueries().add(query.getMetadata());
        return query;
    }

    public NamedPlsqlStoredFunctionQuery addNamedPLSQLStoredFunctionQuery() {
        NamedPlsqlStoredFunctionQueryImpl query = new NamedPlsqlStoredFunctionQueryImpl();
        getMetadata().getNamedPLSQLStoredFunctionQueries().add(query.getMetadata());
        return query;
    }

    public NamedPlsqlStoredProcedureQuery addNamedPLSQLStoredProcedureQuery() {
        NamedPlsqlStoredProcedureQueryImpl query = new NamedPlsqlStoredProcedureQueryImpl();
        getMetadata().getNamedPLSQLStoredProcedureQueries().add(query.getMetadata());
        return query;
    }
    
    public NamedQuery addNamedQuery() {
        NamedQueryImpl namedQuery = new NamedQueryImpl();
        getMetadata().getNamedQueries().add(namedQuery.getMetadata());
        return namedQuery;
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
    
    public SqlResultSetMapping addSqlResultSetMapping() {
        SqlResultSetMappingImpl sqlResultSetMapping = new SqlResultSetMappingImpl();
        getMetadata().getSqlResultSetMappings().add(sqlResultSetMapping.getMetadata());
        return sqlResultSetMapping;
    }
    
    public AdditionalCriteria setAdditionalCriteria() {
        AdditionalCriteriaImpl additionalCriteria = new AdditionalCriteriaImpl();
        getMetadata().setAdditionalCriteria(additionalCriteria.getMetadata());
        return additionalCriteria;
    }
    
    public Cache setCache() {
        CacheImpl cache = new CacheImpl();
        getMetadata().setCache(cache.getMetadata());
        return cache;
    }

    public R setCacheable(Boolean cacheable) {
        getMetadata().setCacheable(cacheable);
        return (R) this;
    }
    
    public CacheInterceptor setCacheInterceptor() {
        CacheInterceptorImpl cacheInterceptor = new CacheInterceptorImpl();
        getMetadata().setCacheInterceptor(cacheInterceptor.getMetadata());
        return cacheInterceptor;
    }
    
    public R setExcludeDefaultListeners(Boolean excludeDefaultListeners) {
        getMetadata().setExcludeDefaultListeners(excludeDefaultListeners);
        return (R) this;
    }

    public R setExcludeSuperclassListeners(Boolean excludeSuperclassListeners) {
        getMetadata().setExcludeSuperclassListeners(excludeSuperclassListeners);
        return (R) this;
    }
    
    public R setExistenceChecking(String existenceChecking) {
        getMetadata().setExistenceChecking(existenceChecking);
        return (R) this;
    }
    
    public R setIdClass(String idClass) {
        getMetadata().setIdClassName(idClass);
        return (R) this;
    }
    
    public Multitenant setMultitenant() {
        MultitenantImpl multitenant = new MultitenantImpl();
        getMetadata().setMultitenant(multitenant.getMetadata());
        return multitenant;
    }
    
    public OptimisticLocking setOptimisticLocking() {
        OptimisticLockingImpl optimisticLocking = new OptimisticLockingImpl();
        getMetadata().setOptimisticLocking(optimisticLocking.getMetadata());
        return optimisticLocking;
    }
    
    public R setPostLoad(String methodName) {
        getMetadata().setPostLoad(methodName);
        return (R) this;
    }

    public R setPostPersist(String methodName) {
        getMetadata().setPostPersist(methodName);
        return (R) this;
    }

    public R setPostRemove(String methodName) {
        getMetadata().setPostRemove(methodName);
        return (R) this;
    }

    public R setPostUpdate(String methodName) {
        getMetadata().setPostUpdate(methodName);
        return (R) this;
    }

    public R setPrePersist(String methodName) {
        getMetadata().setPrePersist(methodName);
        return (R) this;
    }

    public R setPreRemove(String methodName) {
        getMetadata().setPreRemove(methodName);
        return (R) this;
    }

    public R setPreUpdate(String methodName) {
        getMetadata().setPreUpdate(methodName);
        return (R) this;
    }
    
    public PrimaryKey setPrimaryKey() {
        PrimaryKeyImpl primaryKey = new PrimaryKeyImpl();
        getMetadata().setPrimaryKey(primaryKey.getMetadata());
        return primaryKey;
    }
    
    public QueryRedirectors setQueryRedirectors() {
        QueryRedirectorsImpl queryRedirectors = new QueryRedirectorsImpl();
        getMetadata().setQueryRedirectors(queryRedirectors.getMetadata());
        return queryRedirectors;
    }
    
    public R setReadOnly(Boolean readOnly) {
        getMetadata().setReadOnly(readOnly);
        return (R) this;
    }

    public SequenceGenerator setSequenceGenerator() {
        SequenceGeneratorImpl sequenceGenerator = new SequenceGeneratorImpl();
        getMetadata().setSequenceGenerator(sequenceGenerator.getMetadata());
        return sequenceGenerator;
    }
    
    public TableGenerator setTableGenerator() {
        TableGeneratorImpl tableGenerator = new TableGeneratorImpl();
        getMetadata().setTableGenerator(tableGenerator.getMetadata());
        return tableGenerator;
    }

    public UuidGenerator setUuidGenerator() {
        UuidGeneratorImpl uuidGenerator = new UuidGeneratorImpl();
        getMetadata().setUuidGenerator(uuidGenerator.getMetadata());
        return uuidGenerator;
    }
}
