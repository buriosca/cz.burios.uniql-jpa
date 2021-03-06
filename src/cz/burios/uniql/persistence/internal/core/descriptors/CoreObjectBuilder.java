/*******************************************************************************
 * Copyright (c) 2012, 2015 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Blaise Doughan - 2.5 - initial implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.core.descriptors;

import cz.burios.uniql.persistence.core.mappings.CoreMapping;
import cz.burios.uniql.persistence.internal.core.helper.CoreField;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractRecord;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.oxm.XMLContext;

public abstract class CoreObjectBuilder<
    ABSTRACT_RECORD extends CoreAbstractRecord, 
    ABSTRACT_SESSION extends CoreAbstractSession,
    FIELD extends CoreField,
    MAPPING extends CoreMapping> {

    /**
     * Return a new instance of the receiver's javaClass.
     */
    public abstract Object buildNewInstance();

    /**
     * Create a new row/record for the object builder.
     * This allows subclasses to define different record types.
     */
    public abstract ABSTRACT_RECORD createRecord(ABSTRACT_SESSION session);

    /**
     * Create a new row/record from XMLContext.
     */
    public abstract ABSTRACT_RECORD createRecordFromXMLContext(XMLContext context);

    /**
     * Extract primary key attribute values from the domainObject.
     */
    public abstract Object extractPrimaryKeyFromObject(Object domainObject, ABSTRACT_SESSION session);

    /**
     * Return the mapping for the specified field.
     */
    public abstract MAPPING getMappingForField(FIELD field);

}