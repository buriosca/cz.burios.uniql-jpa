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

import cz.burios.uniql.persistence.internal.jpa.config.AbstractAccessorImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.MappingAccessor;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractMappingImpl<T extends MappingAccessor, R> extends AbstractAccessorImpl<T, R> {

    public AbstractMappingImpl(T t) {
        super(t);
    }
    
    public R setAttributeType(String attributeType) {
        getMetadata().setAttributeType(attributeType);
        return (R) this;
    }
    
}
