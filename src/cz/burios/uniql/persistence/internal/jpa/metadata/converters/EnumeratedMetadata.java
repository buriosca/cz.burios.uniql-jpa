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
 *     05/16/2008-1.0M8 Guy Pelletier 
 *       - 218084: Implement metadata merging functionality between mapping files
 *     03/27/2009-2.0 Guy Pelletier 
 *       - 241413: JPA 2.0 Add EclipseLink support for Map type attributes
 *     04/27/2010-2.1 Guy Pelletier 
 *       - 309856: MappedSuperclasses from XML are not being initialized properly
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.converters;

import static cz.burios.uniql.persistence.internal.jpa.metadata.MetadataConstants.JPA_ENUM_ORDINAL;

import cz.burios.uniql.persistence.exceptions.ValidationException;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.MappingAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataClass;
import cz.burios.uniql.persistence.mappings.DatabaseMapping;
import cz.burios.uniql.persistence.mappings.converters.EnumTypeConverter;

/**
 * INTERNAL:
 * Abstract converter class that parents both the JPA and Eclipselink 
 * converters.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 1.2
 */
public class EnumeratedMetadata extends MetadataConverter {
    private String m_enumeratedType;
    
    /**
     * INTERNAL:
     * Used for defaulting case.
     */
    public EnumeratedMetadata() {
        super("<enumerated>");
        m_enumeratedType = JPA_ENUM_ORDINAL;
    }
    
    /**
     * INTERNAL:
     * Used for defaulting.
     */
    public EnumeratedMetadata(MetadataAccessor accessor) {
        super(null, accessor);
    }
    
    /**
     * INTERNAL:
     * Used for annotation loading
     */
    public EnumeratedMetadata(MetadataAnnotation enumerated, MetadataAccessor accessor) {
        super(enumerated, accessor);
        
        m_enumeratedType = enumerated.getAttributeString("value");
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (super.equals(objectToCompare) && objectToCompare instanceof EnumeratedMetadata) {
            EnumeratedMetadata enumerated = (EnumeratedMetadata) objectToCompare;
            return valuesMatch(m_enumeratedType, enumerated.getEnumeratedType());
        }
        
        return false;
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public String getEnumeratedType() {
        return m_enumeratedType;
    }
    
    /**
     * INTERNAL:
     * Return true if the given class is a valid enum type.
     */
    public static boolean isValidEnumeratedType(MetadataClass cls) {
        return cls.isEnum();    
    }
    
    /**
     * INTERNAL:
     * Every converter needs to be able to process themselves.
     */
    public void process(DatabaseMapping mapping, MappingAccessor accessor, MetadataClass referenceClass, boolean isForMapKey) {
        // Create an EnumTypeConverter and set it on the mapping.
        if (! EnumeratedMetadata.isValidEnumeratedType(referenceClass)) {
            throw ValidationException.invalidTypeForEnumeratedAttribute(mapping.getAttributeName(), referenceClass, accessor.getJavaClass());
        }
        boolean isOrdinal = true;
        if (m_enumeratedType != null) {
            isOrdinal = m_enumeratedType.equals(JPA_ENUM_ORDINAL);
        }
        setConverter(mapping, new EnumTypeConverter(mapping, referenceClass.getName(), isOrdinal), isForMapKey);
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setEnumeratedType(String enumerated) {
        m_enumeratedType = enumerated;
    }
}