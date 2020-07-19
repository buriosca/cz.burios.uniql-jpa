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
package cz.burios.uniql.persistence.oxm.mappings;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.internal.helper.DatabaseField;
import cz.burios.uniql.persistence.internal.oxm.mappings.Mapping;
import cz.burios.uniql.persistence.internal.queries.ContainerPolicy;
import cz.burios.uniql.persistence.internal.sessions.AbstractSession;
import cz.burios.uniql.persistence.mappings.AttributeAccessor;
import cz.burios.uniql.persistence.oxm.record.XMLRecord;
/**
 * INTERNAL
 * All mappings which can be added to cz.burios.uniql.persistence.oxm.XMLDescriptor must
 * implement this interface.
 *
 *@see cz.burios.uniql.persistence.oxm.mappings
 */
public interface XMLMapping extends Mapping<AbstractSession, AttributeAccessor, ContainerPolicy, ClassDescriptor, DatabaseField, XMLRecord> {
    
    public void convertClassNamesToClasses(ClassLoader classLoader);

    /**
     * INTERNAL:
     * A method that marshals a single value to the provided Record based on this mapping's
     * XPath. Used for Sequenced marshalling.
     * @param value - The value to be marshalled
     * @param record - The Record the value is being marshalled too. 
     */
    public void writeSingleValue(Object value, Object parent, XMLRecord record, AbstractSession session);
    
    public boolean isWriteOnly();
    
    public void setIsWriteOnly(boolean b);
}
