/*******************************************************************************
 * Copyright (c) 1998, 2014 Oracle and/or its affiliates. All rights reserved.
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
package cz.burios.uniql.persistence.sessions.changesets;


/**
 * <p>
 * <b>Purpose</b>: Provides API for the ObjectReferenceChangeRecord.
 * <p>
 * <b>Description</b>: This Interface represents changes made in a one to one mapping and other single object reference mappings.
 */
public interface ObjectReferenceChangeRecord extends ChangeRecord {

    /**
     * ADVANCED:
     * Returns the new reference for this object
     * @return cz.burios.uniql.persistence.sessions.changesets.ObjectChangeSet
     */
    public ObjectChangeSet getNewValue();
    
    /**
     * Return the old value of the object reference.
     * This is used during the commit for private-owned references.
     */
    public Object getOldValue();
}
