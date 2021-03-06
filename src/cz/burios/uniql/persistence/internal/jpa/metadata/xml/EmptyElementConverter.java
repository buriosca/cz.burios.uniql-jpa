/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *      Gordon Yorke - Inititial implementation
 */
package cz.burios.uniql.persistence.internal.jpa.metadata.xml;

import cz.burios.uniql.persistence.mappings.DatabaseMapping;
import cz.burios.uniql.persistence.mappings.converters.Converter;
import cz.burios.uniql.persistence.sessions.Session;

/**
 * Converts from empty node to true or false
 * 
 * @author Gordon Yorke
 * @since EclipseLink 2.2
 */
public class EmptyElementConverter implements Converter {

    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        if ("".equals(dataValue)) {
            return Boolean.TRUE;
        }
        return session.getDatasourcePlatform().getConversionManager().convertObject(dataValue, Boolean.class);
    }

    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue;
    }

    public void initialize(DatabaseMapping mapping, Session session) {}

    public boolean isMutable() {
        return false;
    }
}
