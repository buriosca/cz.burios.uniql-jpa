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
 *      Oracle - initial impl
 ******************************************************************************/
package cz.burios.uniql.persistence.sessions.serializers;

import cz.burios.uniql.persistence.sessions.Session;

/**
 * Abstract Serializer class.  All serializers should subclass this class to be backward compatible.
 * @author James Sutherland
 */
public abstract class AbstractSerializer implements Serializer {
    
    public Class getType() {
        return byte[].class;
    }
    
    public void initialize(Class serializeClass, String serializePackage, Session session) { }
    
    public String toString() {
        return getClass().getSimpleName();
    }
}
