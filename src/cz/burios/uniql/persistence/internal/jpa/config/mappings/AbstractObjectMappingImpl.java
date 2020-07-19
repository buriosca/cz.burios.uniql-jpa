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

import cz.burios.uniql.persistence.internal.jpa.config.columns.ForeignKeyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.PrimaryKeyJoinColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.ObjectAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.PrimaryKeyJoinColumnMetadata;
import cz.burios.uniql.persistence.jpa.config.ForeignKey;
import cz.burios.uniql.persistence.jpa.config.PrimaryKeyJoinColumn;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractObjectMappingImpl<T extends ObjectAccessor, R> extends AbstractRelationshipMappingImpl<T, R> {

    public AbstractObjectMappingImpl(T t) {
        super(t);
        
        getMetadata().setPrimaryKeyJoinColumns(new ArrayList<PrimaryKeyJoinColumnMetadata>());
    }
    
    public PrimaryKeyJoinColumn addPrimaryKeyJoinColumn() {
        PrimaryKeyJoinColumnImpl primaryKeyJoinColumn = new PrimaryKeyJoinColumnImpl();
        getMetadata().getPrimaryKeyJoinColumns().add(primaryKeyJoinColumn.getMetadata());
        return primaryKeyJoinColumn;
    }
    
    public ForeignKey setForeignKey() {
        ForeignKeyImpl foreignKey = new ForeignKeyImpl();
        getMetadata().setForeignKey(foreignKey.getMetadata());
        return foreignKey;
    }
    
    public R setId(Boolean id) {
        getMetadata().setId(id);
        return (R) this;
    }
    
    public R setMapsId(String mapsId) {
        getMetadata().setMapsId(mapsId);
        return (R) this;
    }
    
    public R setOptional(Boolean optional) {
        getMetadata().setOptional(optional);
        return (R) this;
    }
}
