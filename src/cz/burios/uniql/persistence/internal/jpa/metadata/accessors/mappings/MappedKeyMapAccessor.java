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
 *     03/27/2009-2.0 Guy Pelletier 
 *       - 241413: JPA 2.0 Add EclipseLink support for Map type attributes
 *     04/03/2009-2.0 Guy Pelletier
 *       - 241413: JPA 2.0 Add EclipseLink support for Map type attributes
 *     11/06/2009-2.0 Guy Pelletier 
 *       - 286317: UniqueConstraint xml element is changing (plus couple other fixes, see bug)
 *     11/19/2012-2.5 Guy Pelletier 
 *       - 389090: JPA 2.1 DDL Generation Support (foreign key metadata support)
 *     11/28/2012-2.5 Guy Pelletier 
 *       - 374688: JPA 2.1 Converter support
 *     07/16/2013-2.5.1 Guy Pelletier 
 *       - 412384: Applying Converter for parameterized basic-type for joda-time's DateTime does not work
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings;

import java.util.List;

import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataClass;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AssociationOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AttributeOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.ColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.ForeignKeyMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.JoinColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConvertMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.mappings.MapKeyMetadata;

/**
 * Interface class to define the common map mapping metadata.
 * 
 * @see CollectionAccessor
 * @see ElementCollectionAccessor
 *  
 * @author Guy Pelletier
 * @since EclipseLink 1.2
 */
public interface MappedKeyMapAccessor {
    /**
     * INTERNAL:
     */
    public List<AssociationOverrideMetadata> getMapKeyAssociationOverrides();
    
    /**
     * INTERNAL:
     */
    public List<AttributeOverrideMetadata> getMapKeyAttributeOverrides();
    
    /**
     * INTERNAL:
     */
    public MapKeyMetadata getMapKey();
    
    /**
     * INTERNAL:
     */
    public MetadataClass getMapKeyClass();
    
    /**
     * INTERNAL:
     */
    public MetadataClass getMapKeyClassWithGenerics();
    
    /**
     * INTERNAL:
     */
    public String getMapKeyConvert();
    
    /**
     * INTERNAL:
     */
    public List<ConvertMetadata> getMapKeyConverts();
    
    /**
     * INTERNAL:
     */
    public ColumnMetadata getMapKeyColumn();
    
    /**
     * INTERNAL:
     */
    public ForeignKeyMetadata getMapKeyForeignKey();
    
    /**
     * INTERNAL:
     */
    public List<JoinColumnMetadata> getMapKeyJoinColumns();
    
    /**
     * INTERNAL:
     */
    public void setMapKeyClass(MetadataClass cls);
}
