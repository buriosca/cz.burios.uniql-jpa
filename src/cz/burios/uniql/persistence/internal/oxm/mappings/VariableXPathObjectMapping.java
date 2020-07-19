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
 *     Denise Smith - 2.5 - initial implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.oxm.mappings;

import cz.burios.uniql.persistence.core.descriptors.CoreDescriptor;
import cz.burios.uniql.persistence.core.mappings.CoreAttributeAccessor;
import cz.burios.uniql.persistence.core.mappings.converters.CoreConverter;
import cz.burios.uniql.persistence.core.sessions.CoreSession;
import cz.burios.uniql.persistence.internal.core.helper.CoreField;
import cz.burios.uniql.persistence.internal.core.queries.CoreContainerPolicy;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.internal.oxm.Marshaller;
import cz.burios.uniql.persistence.internal.oxm.NamespaceResolver;
import cz.burios.uniql.persistence.internal.oxm.Unmarshaller;
import cz.burios.uniql.persistence.internal.oxm.XPathFragment;
import cz.burios.uniql.persistence.internal.oxm.record.XMLRecord;

public interface VariableXPathObjectMapping<
ABSTRACT_SESSION extends CoreAbstractSession,
ATTRIBUTE_ACCESSOR extends CoreAttributeAccessor,
CONTAINER_POLICY extends CoreContainerPolicy,
CONVERTER extends CoreConverter,
DESCRIPTOR extends CoreDescriptor,
FIELD extends CoreField,
MARSHALLER extends Marshaller,
SESSION extends CoreSession,
UNMARSHALLER extends Unmarshaller,
XML_RECORD extends XMLRecord> 
extends Mapping<ABSTRACT_SESSION, ATTRIBUTE_ACCESSOR, CONTAINER_POLICY, DESCRIPTOR, FIELD, XML_RECORD>, XMLConverterMapping<MARSHALLER, SESSION, UNMARSHALLER> {
			
	public ATTRIBUTE_ACCESSOR getVariableAttributeAccessor(); 

	public XPathFragment getXPathFragmentForValue(Object obj, NamespaceResolver nr, boolean isNamespaceAware,char sep);
	
	public boolean isAttribute();
		
	public void setAttribute(boolean isAttribute);
	
	public void setConverter(CONVERTER converter);

	public void setIsWriteOnly(boolean isWriteOnly);
	
	public void setReferenceClassName(String aClassName);
	
	public void setVariableAttributeAccessor(ATTRIBUTE_ACCESSOR variableAttributeAccessor);
	
	public void setVariableAttributeName(String variableAttributeName);
	
	public void setVariableGetMethodName(String variableGetMethodName);
	
	public void setVariableSetMethodName(String variableSetMethodName);
}
