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
package cz.burios.uniql.persistence.internal.jpa.config.transformers;

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.ColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.transformers.WriteTransformerMetadata;
import cz.burios.uniql.persistence.jpa.config.Column;
import cz.burios.uniql.persistence.jpa.config.WriteTransformer;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class WriteTransformerImpl extends MetadataImpl<WriteTransformerMetadata> implements WriteTransformer {

    public WriteTransformerImpl() {
        super(new WriteTransformerMetadata());
    }
    
    public Column setColumn() {
        ColumnImpl column = new ColumnImpl();
        getMetadata().setColumn(column.getMetadata());
        return column;
    }

    public WriteTransformer setMethod(String method) {
        getMetadata().setMethod(method);
        return this;
    }

    public WriteTransformer setTransformerClass(String transformerClass) {
        getMetadata().setTransformerClassName(transformerClass);
        return this;
    }

}
