/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.helper;

import java.lang.Class;
import java.lang.ClassNotFoundException;

import cz.burios.uniql.persistence.internal.helper.ConversionManager;
import cz.burios.uniql.persistence.sessions.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamClass;
import java.io.ObjectInputStream;

/**
 * INTERNAL:
 * Defines a custom ObjectInputStream that is used with SerializedObjectConverter
 * to ensure the correct class loader is used.
 * BUG# 2813583
 *
 * @author Guy Pelletier
 * @version 1.0 March 25/03
 */
public class CustomObjectInputStream extends ObjectInputStream {
    ConversionManager m_conversionManager;

    public CustomObjectInputStream(InputStream stream, Session session) throws IOException {
        super(stream);
        m_conversionManager = session.getDatasourceLogin().getDatasourcePlatform().getConversionManager();
    }

    public Class resolveClass(ObjectStreamClass classDesc) throws ClassNotFoundException, IOException {
        return m_conversionManager.convertClassNameToClass(classDesc.getName());
    }
}
