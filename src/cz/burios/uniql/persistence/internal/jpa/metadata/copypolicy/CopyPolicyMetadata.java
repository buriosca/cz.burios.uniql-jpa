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
 *     tware - March 28/2008 - 1.0M7 - Initial implementation
 *     05/16/2008-1.0M8 Guy Pelletier 
 *       - 218084: Implement metadata merging functionality between mapping files
 *     04/27/2010-2.1 Guy Pelletier 
 *       - 309856: MappedSuperclasses from XML are not being initialized properly
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.copypolicy;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.descriptors.copying.CopyPolicy;
import cz.burios.uniql.persistence.internal.jpa.metadata.MetadataDescriptor;
import cz.burios.uniql.persistence.internal.jpa.metadata.ORMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;

/**
 * INTERNAL:
 * Incapsulates common behavior amount class for all the different types of 
 * copy policy metadata
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @see cz.burios.uniql.persistence.internal.jpa.metadata.copypolicy.CustomCopyPolicy
 * @see cz.burios.uniql.persistence.internal.jpa.metadata.copypolicy.InstantiationCopyPolicy
 * @see cz.burios.uniql.persistence.internal.jpa.metadata.copypolicy.CloneCopyPolicy
 * 
 * @author tware
 */
public abstract class CopyPolicyMetadata extends ORMetadata {   
    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    protected CopyPolicyMetadata(MetadataAnnotation annotation, MetadataAccessor accessor) {
        super(annotation, accessor);
    }
    
    /**
     * INTERNAL:
     * Used for XML loading.
     */
    protected CopyPolicyMetadata(String xmlElement) {
        super(xmlElement);
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        return objectToCompare instanceof CopyPolicyMetadata;
    }
    
    /**
     * INTERNAL:
     */
    public void process(MetadataDescriptor descriptor) {
        descriptor.setHasCopyPolicy();
        ClassDescriptor classDescriptor = descriptor.getClassDescriptor();       
        classDescriptor.setCopyPolicy(getCopyPolicy());
    }
    
    /**
     * INTERNAL:
     */
    public abstract CopyPolicy getCopyPolicy();
}
