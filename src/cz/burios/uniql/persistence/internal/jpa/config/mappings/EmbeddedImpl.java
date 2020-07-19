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

import cz.burios.uniql.persistence.internal.jpa.config.columns.AssociationOverrideImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.FieldImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.ConvertImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.EmbeddedAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AssociationOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConvertMetadata;
import cz.burios.uniql.persistence.jpa.config.AssociationOverride;
import cz.burios.uniql.persistence.jpa.config.Convert;
import cz.burios.uniql.persistence.jpa.config.Embedded;
import cz.burios.uniql.persistence.jpa.config.Field;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class EmbeddedImpl extends AbstractEmbeddedMappingImpl<EmbeddedAccessor, Embedded> implements Embedded {

    public EmbeddedImpl() {
        super(new EmbeddedAccessor());
        
        getMetadata().setAssociationOverrides(new ArrayList<AssociationOverrideMetadata>());
        getMetadata().setConverts(new ArrayList<ConvertMetadata>());
    }

    public AssociationOverride addAssociationOverride() {
        AssociationOverrideImpl override = new AssociationOverrideImpl();
        getMetadata().getAssociationOverrides().add(override.getMetadata());
        return override;
    }
    
    public Convert addConvert() {
        ConvertImpl convert = new ConvertImpl();
        getMetadata().getConverts().add(convert.getMetadata());
        return convert;
    }

    public Field setField() {
        FieldImpl field = new FieldImpl();
        getMetadata().setField(field.getMetadata());
        return field;
    }
    
}
