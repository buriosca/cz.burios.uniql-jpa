/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     David McCann - Jan.10, 2013 - 2.5.0 - initial API and implementation
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.burios.uniql.persistence.internal.helper.DatabaseType;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.MetadataAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAccessibleObject;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;
import cz.burios.uniql.persistence.internal.jpa.metadata.xml.XMLEntityMappings;
import cz.burios.uniql.persistence.platform.database.oracle.jdbc.OracleObjectType;

/**
 * INTERNAL:
 * Object to hold onto Oracle object type meta-data.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - all metadata mapped from XML should be initialized in the initXMLObject 
 *   method.
 * - when loading from annotations, the constructor accepts the metadata
 *   accessor this metadata was loaded from. Used it to look up any 
 *   'companion' annotation needed for processing.
 * - methods should be preserved in alphabetical order.
 * 
 * @author David McCann
 * @since EclipseLink 2.5
 */
public class OracleObjectTypeMetadata extends OracleComplexTypeMetadata {
    private List<PLSQLParameterMetadata> fields = new ArrayList<PLSQLParameterMetadata>();
    
    /**
     * INTERNAL:
     * Used for XML loading.
     */
    public OracleObjectTypeMetadata() {
        super("<oracle-object>");
    }
    
    /**
     * INTERNAL:
     * Used for annotation loading.
     */
    public OracleObjectTypeMetadata(MetadataAnnotation objectType, MetadataAccessor accessor) {
        super(objectType, accessor);
                
        for (Object field : objectType.getAttributeArray("fields")) {
            this.fields.add(new PLSQLParameterMetadata((MetadataAnnotation) field, accessor));
        }
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        return super.equals(objectToCompare) && 
                objectToCompare instanceof OracleObjectTypeMetadata &&
                valuesMatch(this.fields, ((OracleObjectTypeMetadata) objectToCompare).getFields());
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public List<PLSQLParameterMetadata> getFields() {
        return fields;
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public void initXMLObject(MetadataAccessibleObject accessibleObject, XMLEntityMappings entityMappings) {
        super.initXMLObject(accessibleObject, entityMappings);
        
        // Initialize lists of ORMetadata objects.
        initXMLObjects(fields, accessibleObject);
    }
    
    /**
     * INTERNAL:
     * Build a runtime Oracle object type from the meta-data.
     */
    public OracleObjectType process() {
        OracleObjectType objectType = new OracleObjectType();
        super.process(objectType);
        
        Map<String, DatabaseType> typeFields = new HashMap<String, DatabaseType>();
        for (PLSQLParameterMetadata field : this.fields) {
            typeFields.put(field.getName(), getDatabaseTypeEnum(field.getDatabaseType()));
        }
        
        objectType.setFields(typeFields);
        return objectType;
    }

    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setFields(List<PLSQLParameterMetadata> fields) {
        this.fields = fields;
    }
    
    /**
     * Indicates an instance of OracleObjectTypeMetadata.
     * @return
     */
    public boolean isOracleObjectTypeMetadata() {
        return true;
    }
}
