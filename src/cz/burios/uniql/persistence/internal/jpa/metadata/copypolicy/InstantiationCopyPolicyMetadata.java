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

import cz.burios.uniql.persistence.descriptors.copying.CopyPolicy;
import cz.burios.uniql.persistence.descriptors.copying.InstantiationCopyPolicy;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;

/**
 * INTERNAL:
 * Used to store information about InstantiationCopyPolicy as it is read from 
 * XML or annotations
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @see cz.burios.uniql.persistence.annotations.InstantiationCopyPolicy
 * @author tware
 */
public class InstantiationCopyPolicyMetadata extends CopyPolicyMetadata {
    /**
     * INTERNAL:
     * Used for XML loading.
     */
    public InstantiationCopyPolicyMetadata() {
        super("<instantiation-copy-policy>");
    }
    
    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    public InstantiationCopyPolicyMetadata(MetadataAnnotation copyPolicy, MetadataAccessor accessor) { 
        super(copyPolicy, accessor);
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        return super.equals(objectToCompare) && objectToCompare instanceof InstantiationCopyPolicyMetadata;
    }

    /**
     * INTERNAL:
     */
    public CopyPolicy getCopyPolicy() {
        return new InstantiationCopyPolicy();
    }
}
