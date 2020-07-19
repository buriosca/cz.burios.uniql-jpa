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
package cz.burios.uniql.persistence.oxm.mappings.converters;

import cz.burios.uniql.persistence.mappings.converters.Converter;
import cz.burios.uniql.persistence.oxm.XMLMarshaller;
import cz.burios.uniql.persistence.oxm.XMLUnmarshaller;
import cz.burios.uniql.persistence.sessions.Session;

/**
 * <p><b>Purpose</b>: Conversion interface to allow conversion between object and data types.
 * This can be used in any mapping to convert between the object and data types without requiring code
 * placed in the object model. This extension of the Converter interface allows for the XMLMarshaller
 * and XMLUnmarshaller to be passed into the conversion methods.
 * 
 * @see Converter
 * @see cz.burios.uniql.persistence.oxm.mappings.XMLDirectMapping XMLDirectMapping
 * @see cz.burios.uniql.persistence.oxm.mappings.XMLCompositeDirectCollectionMapping XMLCompositeDirectCollectionMapping
 * @see cz.burios.uniql.persistence.oxm.mappings.XMLCompositeObjectMapping XMLCompositeObjectMapping
 * @see cz.burios.uniql.persistence.oxm.mappings.XMLCompositeCollectionMapping XMLCompositeCollectionMapping
 * 
 */

public interface XMLConverter extends Converter {
    Object convertObjectValueToDataValue(Object objectValue, Session session, XMLMarshaller marshaller);
    Object convertDataValueToObjectValue(Object dataValue, Session session, XMLUnmarshaller unmarshaller);

}
