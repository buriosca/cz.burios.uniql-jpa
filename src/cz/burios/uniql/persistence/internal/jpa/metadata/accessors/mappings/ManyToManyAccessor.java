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
 *     05/16/2008-1.0M8 Guy Pelletier 
 *       - 218084: Implement metadata merging functionality between mapping files
 *     12/12/2008-1.1 Guy Pelletier 
 *       - 249860: Implement table per class inheritance support.
 *     02/06/2009-2.0 Guy Pelletier 
 *       - 248293: JPA 2.0 Element Collections (part 2)
 *     06/02/2009-2.0 Guy Pelletier 
 *       - 278768: JPA 2.0 Association Override Join Table
 *     09/29/2009-2.0 Guy Pelletier 
 *       - 282553: JPA 2.0 JoinTable support for OneToOne and ManyToOne
 *     04/27/2010-2.1 Guy Pelletier 
 *       - 309856: MappedSuperclasses from XML are not being initialized properly
 *     06/14/2010-2.2 Guy Pelletier 
 *       - 264417: Table generation is incorrect for JoinTables in AssociationOverrides
 *     03/24/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 *     05/17/2012-2.3.3 Arron Ferguson  
 *       - 379829: NPE Thrown with OneToOne Relationship
 *     11/19/2012-2.5 Guy Pelletier 
 *       - 389090: JPA 2.1 DDL Generation Support (foreign key metadata support)
 ******************************************************************************/  
package cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings;

import cz.burios.uniql.persistence.eis.mappings.EISOneToManyMapping;
import cz.burios.uniql.persistence.exceptions.ValidationException;
import cz.burios.uniql.persistence.internal.helper.DatabaseTable;
import cz.burios.uniql.persistence.internal.jpa.metadata.MetadataLogger;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.ClassAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotatedElement;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;
import cz.burios.uniql.persistence.mappings.CollectionMapping;
import cz.burios.uniql.persistence.mappings.DatabaseMapping;
import cz.burios.uniql.persistence.mappings.ManyToManyMapping;

/**
 * INTERNAL:
 * A many to many relationship accessor.
 * 
 * Key notes:
 * - any metadata mapped from XML to this class must be compared in the
 *   equals method.
 * - any metadata mapped from XML to this class must be handled in the merge
 *   method. (merging is done at the accessor/mapping level)
 * - any metadata mapped from XML to this class must be initialized in the
 *   initXMLObject  method.
 * - methods should be preserved in alphabetical order.
 * 
 * @author Guy Pelletier
 * @since TopLink EJB 3.0 Reference Implementation
 */
public class ManyToManyAccessor extends CollectionAccessor {
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public ManyToManyAccessor() {
        super("<many-to-many>");
    }
    
    /**
     * INTERNAL:
     */
    public ManyToManyAccessor(MetadataAnnotation manyToMany, MetadataAnnotatedElement annotatedElement, ClassAccessor classAccessor) {
        super(manyToMany, annotatedElement, classAccessor);
        
        setMappedBy(manyToMany.getAttributeString("mappedBy"));
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean equals(Object objectToCompare) {
        return super.equals(objectToCompare) && objectToCompare instanceof ManyToManyAccessor;
    }
    
    /**
     * INTERNAL:
     * Return the default table to hold the foreign key of a MapKey when
     * and Entity is used as the MapKey
     * @return
     */
    @Override
    protected DatabaseTable getDefaultTableForEntityMapKey(){
        return getJoinTableMetadata().getDatabaseTable();
    }
    
    /**
     * INTERNAL: 
     * Return the logging context for this accessor.
     */
    protected String getLoggingContext() {
        return MetadataLogger.MANY_TO_MANY_MAPPING_REFERENCE_CLASS;
    }
    
    /**
     * INTERNAL:
     */
    @Override
    public boolean isManyToMany() {
        return true;
    }
    
    /**
     * INTERNAL: 
     * A PrivateOwned setting on a ManyToMany is ignored. A log warning is
     * issued.
     */
    @Override
    public boolean isPrivateOwned() {
        if (super.isPrivateOwned()) {
            getLogger().logWarningMessage(MetadataLogger.IGNORE_PRIVATE_OWNED_ANNOTATION, this);
        }
        
        return false;
    }
    
    /**
     * INTERNAL:
     * Process a many to many metadata accessor into a EclipseLink 
     * ManyToManyMapping.
     */
    @Override
    public void process() {
        super.process();
        
        // Create a M-M mapping and process common collection mapping metadata.
        // Allow for different descriptor types (EIS) to create different mapping types.
        CollectionMapping mapping = getDescriptor().getClassDescriptor().newManyToManyMapping();
        process(mapping);

        if (hasMappedBy()) {
            // We are processing the non-owning side of a M-M relationship. Get 
            // the owning mapping from the reference descriptor metadata and
            // process the keys from it.
            DatabaseMapping owningMapping = getOwningMapping();
            if (owningMapping.isManyToManyMapping()){
                ManyToManyMapping ownerMapping = (ManyToManyMapping) owningMapping;
                processMappedByRelationTable(ownerMapping.getRelationTableMechanism(), ((ManyToManyMapping) mapping).getRelationTableMechanism());
                
                // Set the mapping read-only.
                mapping.setIsReadOnly(true);
            } else {
                // Invalid owning mapping type, throw an exception.
                throw ValidationException.invalidMapping(getJavaClass(), getReferenceClass());
            }
        } else if (mapping instanceof ManyToManyMapping) { 
            // Processing the owning side of a M-M, process the join table.
            processJoinTable(mapping, ((ManyToManyMapping) mapping).getRelationTableMechanism(), getJoinTableMetadata());
        } else if (mapping instanceof EISOneToManyMapping) {
            processEISOneToManyMapping((EISOneToManyMapping) mapping);
        }
    }
}

