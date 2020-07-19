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
import cz.burios.uniql.persistence.internal.jpa.config.columns.TenantDiscriminatorColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.config.listeners.EntityListenerImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.AccessMethodsImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.TenantDiscriminatorColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.listeners.EntityListenerMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.xml.XMLPersistenceUnitDefaults;
import cz.burios.uniql.persistence.jpa.config.AccessMethods;
import cz.burios.uniql.persistence.jpa.config.EntityListener;
import cz.burios.uniql.persistence.jpa.config.PersistenceUnitDefaults;
import cz.burios.uniql.persistence.jpa.config.TenantDiscriminatorColumn;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class PersistenceUnitDefaultsImpl extends MetadataImpl<XMLPersistenceUnitDefaults> implements PersistenceUnitDefaults {

    public PersistenceUnitDefaultsImpl() {
        super(new XMLPersistenceUnitDefaults());
        
        getMetadata().setEntityListeners(new ArrayList<EntityListenerMetadata>());
        getMetadata().setTenantDiscriminatorColumns(new ArrayList<TenantDiscriminatorColumnMetadata>());
    }
    
    public EntityListener addEntityListener() {
        EntityListenerImpl listener = new EntityListenerImpl();
        getMetadata().getEntityListeners().add(listener.getMetadata());
        return listener;
    }
    
    public TenantDiscriminatorColumn addTenantDiscriminatorColumn() {
        TenantDiscriminatorColumnImpl column = new TenantDiscriminatorColumnImpl();
        getMetadata().getTenantDiscriminatorColumns().add(column.getMetadata());
        return column;
    }

    public PersistenceUnitDefaults setAccess(String access) {
        getMetadata().setAccess(access);
        return this;
    }

    public AccessMethods setAccessMethods() {
        AccessMethodsImpl accessMethods = new AccessMethodsImpl();
        getMetadata().setAccessMethods(accessMethods.getMetadata());
        return accessMethods;
    }

    public PersistenceUnitDefaults setCascadePersist(Boolean cascadePersist) {
        getMetadata().setCascadePersist(cascadePersist);
        return this;
    }

    public PersistenceUnitDefaults setCatalog(String catalog) {
        getMetadata().setCatalog(catalog);
        return this;
    }

    public PersistenceUnitDefaults setDelimitedIdentifiers(Boolean delimitedIdentifiers) {
        getMetadata().setDelimitedIdentifiers(delimitedIdentifiers);
        return this;
    }

    public PersistenceUnitDefaults setSchema(String schema) {
        getMetadata().setSchema(schema);
        return this;
    }

}
