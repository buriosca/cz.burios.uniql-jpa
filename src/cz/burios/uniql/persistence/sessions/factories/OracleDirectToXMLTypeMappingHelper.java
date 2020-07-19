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
package cz.burios.uniql.persistence.sessions.factories;

import cz.burios.uniql.persistence.internal.codegen.NonreflectiveMethodDefinition;
import cz.burios.uniql.persistence.internal.sessions.DatabaseSessionImpl;
import cz.burios.uniql.persistence.internal.sessions.factories.DirectToXMLTypeMappingHelper;
import cz.burios.uniql.persistence.mappings.DatabaseMapping;
import cz.burios.uniql.persistence.mappings.DirectToFieldMapping;
import cz.burios.uniql.persistence.mappings.xdb.DirectToXMLTypeMapping;
import cz.burios.uniql.persistence.oxm.NamespaceResolver;
import cz.burios.uniql.persistence.oxm.XMLDescriptor;
import cz.burios.uniql.persistence.oxm.mappings.XMLDirectMapping;

/**
 * Helper class to abstract the XML mapping for DirectToXMLType.
 * 
 * @author djclarke
 * @since EclipseLink 0.1
 */
public class OracleDirectToXMLTypeMappingHelper extends DirectToXMLTypeMappingHelper {

    protected String namespaceXPath;
    /**
     * Add the XMLType mapping indicator to the DatabaseMapping descriptor.
     */
    @Override
    public void addClassIndicator(XMLDescriptor descriptor, String namespaceXPath) {
        this.namespaceXPath = namespaceXPath;
        descriptor.getInheritancePolicy().addClassIndicator(DirectToXMLTypeMapping.class,
            namespaceXPath + "direct-xml-type-mapping");
    }

    /**
     * Write the Project.class code for the XMLType property.
     */
    public void writeShouldreadWholeDocument(NonreflectiveMethodDefinition method, String mappingName, DatabaseMapping mapping) {
        method.addLine(mappingName + ".setShouldReadWholeDocument(" + ((DirectToXMLTypeMapping)mapping).shouldReadWholeDocument() + ");");
    }
    
    /**
     * Invoked from a descriptor is not found.
     */
    @Override
    public void addXDBDescriptors(String name, DatabaseSessionImpl session,
        NamespaceResolver namespaceResolver) {
        
        if (session.getDescriptorForAlias(name) == null){
            XMLDescriptor descriptor = new XMLDescriptor();
    
            descriptor.setJavaClass(DirectToXMLTypeMapping.class);
            descriptor.descriptorIsAggregate();
            descriptor.getInheritancePolicy().setParentClass(DirectToFieldMapping.class);
    
            XMLDirectMapping directtofieldmapping = new XMLDirectMapping();
            directtofieldmapping.setAttributeName("shouldReadWholeDocument");
            directtofieldmapping.setGetMethodName("shouldReadWholeDocument");
            directtofieldmapping.setSetMethodName("setShouldReadWholeDocument");
            directtofieldmapping.setXPath(namespaceXPath + "read-whole-document/text()");
            directtofieldmapping.setNullValue(Boolean.FALSE);
            descriptor.addMapping(directtofieldmapping);
    
            // Need to set the namespace resolver.
            descriptor.setNamespaceResolver(namespaceResolver);
            
            session.addDescriptor(descriptor);
        }
    }
    
}
