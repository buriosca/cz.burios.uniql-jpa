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
package cz.burios.uniql.persistence.internal.jpa.config.queries;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.ColumnResultMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.ConstructorResultMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.EntityResultMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.SQLResultSetMappingMetadata;
import cz.burios.uniql.persistence.jpa.config.ColumnResult;
import cz.burios.uniql.persistence.jpa.config.ConstructorResult;
import cz.burios.uniql.persistence.jpa.config.EntityResult;
import cz.burios.uniql.persistence.jpa.config.SqlResultSetMapping;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class SqlResultSetMappingImpl extends MetadataImpl<SQLResultSetMappingMetadata> implements SqlResultSetMapping {

    public SqlResultSetMappingImpl() {
        super(new SQLResultSetMappingMetadata());
        
        getMetadata().setColumnResults(new ArrayList<ColumnResultMetadata>());
        getMetadata().setConstructorResults(new ArrayList<ConstructorResultMetadata>());
        getMetadata().setEntityResults(new ArrayList<EntityResultMetadata>());
    }
    
    public ColumnResult addColumnResult() {
        ColumnResultImpl columnResult = new ColumnResultImpl();
        getMetadata().getColumnResults().add(columnResult.getMetadata());
        return columnResult;
    }

    public ConstructorResult addConstructorResult() {
        ConstructorResultImpl constructorResult = new ConstructorResultImpl();
        getMetadata().getConstructorResults().add(constructorResult.getMetadata());
        return constructorResult;
    }

    public EntityResult addEntityResult() {
        EntityResultImpl entityResult = new EntityResultImpl();
        getMetadata().getEntityResults().add(entityResult.getMetadata());
        return entityResult;
    }

    public SqlResultSetMapping setName(String name) {
        getMetadata().setName(name);
        return this;
    }

}
