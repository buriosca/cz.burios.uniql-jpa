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
 *     24 April 2013-2.5.1 ailitche
 *       SerializedObjectPolicy initial API and implementation
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.descriptors;

import java.util.List;

import cz.burios.uniql.persistence.descriptors.SerializedObjectPolicy;
import cz.burios.uniql.persistence.internal.helper.DatabaseField;
import cz.burios.uniql.persistence.internal.sessions.AbstractRecord;
import cz.burios.uniql.persistence.internal.sessions.AbstractSession;
import cz.burios.uniql.persistence.queries.ObjectLevelReadQuery;

/**
 * It's a transition class that holds SerializedObjectPolicy class name
 * and field obtained from matadata before it can be instantiated.
 * 
 * @author Andrei Ilitchev
 * @since EclipseLink 2.5.1
 */
public class SerializedObjectPolicyWrapper extends AbstractSerializedObjectPolicy {
    protected String serializedObjectPolicyClassName;
    
    public SerializedObjectPolicyWrapper(String serializedObjectPolicyClassName) {
        super();
        this.serializedObjectPolicyClassName = serializedObjectPolicyClassName;
    }
    
    public String getSerializedObjectPolicyClassName() {
        return this.serializedObjectPolicyClassName;
    }

    @Override
    public SerializedObjectPolicyWrapper clone() {
        throw new UnsupportedOperationException("clone");
    }
    @Override
    public SerializedObjectPolicy instantiateChild() {
        throw new UnsupportedOperationException("instantiateChild");
    }
    @Override
    public void initializeField(AbstractSession session) {
        throw new UnsupportedOperationException("initializeField");
    }
    @Override
    public void initialize(AbstractSession session) {
        throw new UnsupportedOperationException("initialize");
    }
    @Override
    public void postInitialize(AbstractSession session) {
        throw new UnsupportedOperationException("postInitialize");
    }
    @Override
    public void putObjectIntoRow(AbstractRecord databaseRow, Object object, AbstractSession session) {
        throw new UnsupportedOperationException("putObjectIntoRow");
    }
    @Override
    public Object getObjectFromRow(AbstractRecord databaseRow, AbstractSession session, ObjectLevelReadQuery query) {
        throw new UnsupportedOperationException("getObjectFromRow");
    }
    @Override
    public List<DatabaseField> getSelectionFields() {
        throw new UnsupportedOperationException("getSelectionFields");
    }
    @Override
    public List<DatabaseField> getAllSelectionFields() {
        throw new UnsupportedOperationException("getAllSelectionFields");
    }
}
