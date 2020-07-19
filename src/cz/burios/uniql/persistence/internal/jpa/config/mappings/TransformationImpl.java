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

import cz.burios.uniql.persistence.internal.jpa.config.transformers.ReadTransformerImpl;
import cz.burios.uniql.persistence.internal.jpa.config.transformers.WriteTransformerImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.TransformationAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.transformers.WriteTransformerMetadata;
import cz.burios.uniql.persistence.jpa.config.ReadTransformer;
import cz.burios.uniql.persistence.jpa.config.Transformation;
import cz.burios.uniql.persistence.jpa.config.WriteTransformer;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class TransformationImpl extends AbstractDirectMappingImpl<TransformationAccessor, Transformation> implements Transformation {

    public TransformationImpl() {
        super(new TransformationAccessor());
        
        getMetadata().setWriteTransformers(new ArrayList<WriteTransformerMetadata>());
    }

    public WriteTransformer addWriteTransformer() {
        WriteTransformerImpl transformer = new WriteTransformerImpl();
        getMetadata().getWriteTransformers().add(transformer.getMetadata());
        return transformer;
    }

    public Transformation setMutable(Boolean mutable) {
        getMetadata().setMutable(mutable);
        return this;
    }

    public ReadTransformer setReadTransformer() {
        ReadTransformerImpl transformer = new ReadTransformerImpl();
        getMetadata().setReadTransformer(transformer.getMetadata());
        return transformer;
    }

}
