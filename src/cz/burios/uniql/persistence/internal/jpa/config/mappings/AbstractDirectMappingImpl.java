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

import cz.burios.uniql.persistence.internal.jpa.config.converters.ConvertImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.EnumeratedImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.LobImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.TemporalImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.DirectAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConvertMetadata;
import cz.burios.uniql.persistence.jpa.config.Convert;
import cz.burios.uniql.persistence.jpa.config.Enumerated;
import cz.burios.uniql.persistence.jpa.config.Lob;
import cz.burios.uniql.persistence.jpa.config.Temporal;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractDirectMappingImpl<T extends DirectAccessor, R> extends AbstractMappingImpl<T, R> {

    public AbstractDirectMappingImpl(T t) {
        super(t);
        
        getMetadata().setConverts(new ArrayList<ConvertMetadata>());
    }

    /**
     * This covers the JPA 2.1 use case where multiple converts can be added.
     */
    public Convert addConvert() {
        ConvertImpl convert = new ConvertImpl();
        getMetadata().getConverts().add(convert.getMetadata());
        return convert;
    }
    
    /**
     * This covers the EclipseLink Convert, single TEXT convert element.
     */
    public R setConvert(String name) {
        ConvertMetadata convert = new ConvertMetadata();
        convert.setText(name);
        getMetadata().getConverts().add(convert);
        return (R) this;
    }
    
    public Enumerated setEnumerated() {
        EnumeratedImpl enumerated = new EnumeratedImpl();
        getMetadata().setEnumerated(enumerated.getMetadata());
        return enumerated;
    }
    
    public R setFetch(String fetch) {
        getMetadata().setFetch(fetch);
        return (R) this;
    }
    
    public Lob setLob() {
        LobImpl lob = new LobImpl();
        getMetadata().setLob(lob.getMetadata());
        return lob;
    }
    
    public R setOptional(Boolean optional) {
        getMetadata().setOptional(optional);
        return (R) this;
    }
    
    public Temporal setTemporal() {
        TemporalImpl temporal = new TemporalImpl();
        getMetadata().setTemporal(temporal.getMetadata());
        return temporal;
    }
    
}
