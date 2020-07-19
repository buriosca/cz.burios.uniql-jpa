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

import cz.burios.uniql.persistence.internal.jpa.config.columns.DiscriminatorClassImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.DiscriminatorColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.VariableOneToOneAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.DiscriminatorClassMetadata;
import cz.burios.uniql.persistence.jpa.config.DiscriminatorClass;
import cz.burios.uniql.persistence.jpa.config.DiscriminatorColumn;
import cz.burios.uniql.persistence.jpa.config.VariableOneToOne;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class VariableOneToOneImpl extends AbstractObjectMappingImpl<VariableOneToOneAccessor, VariableOneToOne> implements VariableOneToOne {

    public VariableOneToOneImpl() {
        super(new VariableOneToOneAccessor());
        
        getMetadata().setDiscriminatorClasses(new ArrayList<DiscriminatorClassMetadata>());
    }

    public DiscriminatorClass addDiscriminatorClass() {
        DiscriminatorClassImpl discriminatorClass = new DiscriminatorClassImpl();
        getMetadata().getDiscriminatorClasses().add(discriminatorClass.getMetadata());
        return discriminatorClass;
    }

    public DiscriminatorColumn setDiscriminatorColumn() {
        DiscriminatorColumnImpl column = new DiscriminatorColumnImpl();
        getMetadata().setDiscriminatorColumn(column.getMetadata());
        return column;
    }

    public VariableOneToOne setTargetInterface(String targetInterface) {
        getMetadata().setTargetEntityName(targetInterface);
        return this;
    }

}
