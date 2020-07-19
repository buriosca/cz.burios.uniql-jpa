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
package cz.burios.uniql.persistence.internal.jpa.config.mappings;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.columns.JoinColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.JoinFieldImpl;
import cz.burios.uniql.persistence.internal.jpa.config.tables.JoinTableImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.RelationshipAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.JoinColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.JoinFieldMetadata;
import cz.burios.uniql.persistence.jpa.config.BatchFetch;
import cz.burios.uniql.persistence.jpa.config.Cascade;
import cz.burios.uniql.persistence.jpa.config.JoinColumn;
import cz.burios.uniql.persistence.jpa.config.JoinField;
import cz.burios.uniql.persistence.jpa.config.JoinTable;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractRelationshipMappingImpl<T extends RelationshipAccessor, R> extends AbstractMappingImpl<T, R> {
    
    public AbstractRelationshipMappingImpl(T t) {
        super(t);
        
        getMetadata().setJoinColumns(new ArrayList<JoinColumnMetadata>());
        getMetadata().setJoinFields(new ArrayList<JoinFieldMetadata>());
    }
    
    public JoinColumn addJoinColumn() {
        JoinColumnImpl joinColumn = new JoinColumnImpl();
        getMetadata().getJoinColumns().add(joinColumn.getMetadata());
        return joinColumn;
    }
    
    public JoinField addJoinField() {
        JoinFieldImpl joinField = new JoinFieldImpl();
        getMetadata().getJoinFields().add(joinField.getMetadata());
        return joinField;
    }
    
    public BatchFetch setBatchFetch() {
        BatchFetchImpl batchFetch = new BatchFetchImpl();
        getMetadata().setBatchFetch(batchFetch.getMetadata());
        return batchFetch;
    }
    
    public Cascade setCascade() {
        CascadeImpl cascade = new CascadeImpl();
        getMetadata().setCascade(cascade.getMetadata());
        return cascade;
    }
    
    public R setCascadeOnDelete(Boolean cascadeOnDelete) {
        getMetadata().setCascadeOnDelete(cascadeOnDelete);
        return (R) this;
    }
    
    public R setFetch(String fetch) {
        getMetadata().setFetch(fetch);
        return (R) this;
    }
    
    public R setJoinFetch(String joinFetch) {
        getMetadata().setJoinFetch(joinFetch);
        return (R) this;
    }
    
    public JoinTable setJoinTable() {
        JoinTableImpl joinTable = new JoinTableImpl();
        getMetadata().setJoinTable(joinTable.getMetadata());
        return joinTable;
    }
    
    public R setMappedBy(String mappedBy) {
        getMetadata().setMappedBy(mappedBy);
        return (R) this;
    }
    
    public R setNonCacheable(Boolean nonCacheable) {
        getMetadata().setNonCacheable(nonCacheable);
        return (R) this;
    }
    
    public R setOrphanRemoval(Boolean orphanRemoval) {
        getMetadata().setOrphanRemoval(orphanRemoval);
        return (R) this;
    }
    
    public R setPrivateOwned(Boolean privateOwned) {
        getMetadata().setPrivateOwned(privateOwned);
        return (R) this;
    }
    
    public R setTargetEntity(String targetEntity) {
        getMetadata().setTargetEntityName(targetEntity);
        return (R) this;
    }
    
}
