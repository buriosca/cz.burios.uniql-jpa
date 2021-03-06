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
package cz.burios.uniql.persistence.descriptors;

import cz.burios.uniql.persistence.exceptions.*;
import cz.burios.uniql.persistence.sessions.*;

/**
 * <p><b>Purpose</b>:
 * Abstract class to allow complex inheritance support.  Typically class indicators are used to define inheritance in the database,
 * however in complex cases the class type may be determined through another mechanism.
 * The class extractor must be able to determine and return the class type from the database row.
 *
 * @see cz.burios.uniql.persistence.descriptors.InheritancePolicy#setClassExtractor(ClassExtractor)
 */
public abstract class ClassExtractor {

    /**
     * Extract/compute the class from the database row and return the class.
     * Map is used as the public interface to database row, the key is the field name,
     * the value is the database value.
     */
    public abstract Class extractClassFromRow(Record databaseRow, Session session);

    /**
     * Allow for any initialization.
     */
    public void initialize(ClassDescriptor descriptor, Session session) throws DescriptorException {
        // Do nothing by default.
    }
}
