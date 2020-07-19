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
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.EntityResultMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.FieldResultMetadata;
import cz.burios.uniql.persistence.jpa.config.EntityResult;
import cz.burios.uniql.persistence.jpa.config.FieldResult;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class EntityResultImpl extends MetadataImpl<EntityResultMetadata> implements EntityResult {

    public EntityResultImpl() {
        super(new EntityResultMetadata());
        
        getMetadata().setFieldResults(new ArrayList<FieldResultMetadata>());
    }
    
    public FieldResult addFieldResult() {
        FieldResultImpl fieldResult = new FieldResultImpl();
        getMetadata().getFieldResults().add(fieldResult.getMetadata());
        return fieldResult;
    }

    public EntityResult setDiscriminatorColumn(String discriminatorColumn) {
        getMetadata().setDiscriminatorColumn(discriminatorColumn);
        return this;
    }
    
    public EntityResult setEntityClass(String entityClass) {
        getMetadata().setEntityClassName(entityClass);
        return this;
    }

}
