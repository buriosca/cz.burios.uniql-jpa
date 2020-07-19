/*******************************************************************************
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates. All rights reserved.
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
package cz.burios.uniql.persistence.internal.oxm.mappings;

import cz.burios.uniql.persistence.core.descriptors.CoreDescriptor;
import cz.burios.uniql.persistence.core.mappings.CoreAttributeAccessor;
import cz.burios.uniql.persistence.core.mappings.CoreMapping;
import cz.burios.uniql.persistence.internal.core.helper.CoreField;
import cz.burios.uniql.persistence.internal.core.queries.CoreContainerPolicy;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.internal.oxm.record.XMLRecord;

public interface InverseReferenceMapping<
    ABSTRACT_SESSION extends CoreAbstractSession,
    ATTRIBUTE_ACCESSOR extends CoreAttributeAccessor,
    CONTAINER_POLICY extends CoreContainerPolicy,
    DESCRIPTOR extends CoreDescriptor,
    FIELD extends CoreField,
    MAPPING extends CoreMapping,
    XML_RECORD extends XMLRecord> extends Mapping<ABSTRACT_SESSION, ATTRIBUTE_ACCESSOR, CONTAINER_POLICY, DESCRIPTOR, FIELD, XML_RECORD> {
	/**
     * This method is invoked reflectively on the reference object to return the value of the
     * attribute in the object. This method returns the name of the getMethodName or null if not using method access.
     */
    public String getGetMethodName();
	
    public MAPPING getInlineMapping();
    
    public String getReferenceClassName();
    
    public void setContainerPolicy(CONTAINER_POLICY containerPolicy);

    public void setInlineMapping(MAPPING inlineMapping);
    
    public void setMappedBy(String mappedBy);

    public void setReferenceClassName(String aClassName);
	
    public void useCollectionClass(Class concreteClass);
}