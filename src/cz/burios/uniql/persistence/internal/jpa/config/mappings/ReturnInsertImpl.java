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

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.mappings.ReturnInsertMetadata;
import cz.burios.uniql.persistence.jpa.config.ReturnInsert;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class ReturnInsertImpl extends MetadataImpl<ReturnInsertMetadata> implements ReturnInsert {

    public ReturnInsertImpl() {
        super(new ReturnInsertMetadata());
    }
    
    public ReturnInsert setReturnOnly(Boolean returnOnly) {
        getMetadata().setReturnOnly(returnOnly);
        return this;
    }

}
