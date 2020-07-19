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

import cz.burios.uniql.persistence.internal.jpa.config.MetadataImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.PLSQLComplexTypeMetadata;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public abstract class AbstractPlsqlTypeImpl<T extends PLSQLComplexTypeMetadata, R> extends MetadataImpl<T> {

    public AbstractPlsqlTypeImpl(T t) {
        super(t);
    }
    
    public R setCompatibleType(String compatibleType) {
        getMetadata().setCompatibleType(compatibleType);
        return (R) this;
    }

    public R setJavaType(String javaType) {
        getMetadata().setJavaType(javaType);
        return (R) this;
    }

    public R setName(String name) {
        getMetadata().setName(name);
        return (R) this;
    }
    
}
