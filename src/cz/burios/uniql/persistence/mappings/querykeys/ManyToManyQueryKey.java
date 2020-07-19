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
package cz.burios.uniql.persistence.mappings.querykeys;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.exceptions.QueryException;
import cz.burios.uniql.persistence.internal.helper.DatabaseTable;


/** <p>
 * <b>Purpose</b>:Represents a m-m join query.
 */
public class ManyToManyQueryKey extends ForeignReferenceQueryKey {

    /**
     * INTERNAL:
     * override the isCollectionQueryKey() method in the superclass to return true.
     * @return boolean
     */
    public boolean isCollectionQueryKey() {
        return true;
    }

    /**
     * INTERNAL:
     * override the isManyToManyQueryKey() method in the superclass to return true.
     * @return boolean
     */
    public boolean isManyToManyQueryKey() {
        return true;
    }
    
    /**
     * PUBLIC:
     * Returns the reference table.
     */
    public DatabaseTable getRelationTable(ClassDescriptor referenceDescriptor) {
        DatabaseTable relationTable = super.getRelationTable(referenceDescriptor);
        if(relationTable != null) {
            return relationTable;
        } else {
            throw QueryException.noRelationTableInManyToManyQueryKey(this, this.joinCriteria);        
        }
    }
}
