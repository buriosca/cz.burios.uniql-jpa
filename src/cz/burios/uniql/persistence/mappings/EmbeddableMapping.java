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
 *     01/28/2009-2.0 Guy Pelletier 
 *       - 248293: JPA 2.0 Element Collections (part 1)
 *     02/06/2009-2.0 Guy Pelletier 
 *       - 248293: JPA 2.0 Element Collections (part 2)
 *     10/25/2012-2.5 Guy Pelletier 
 *       - 374688: JPA 2.1 Converter support
 ******************************************************************************/  
package cz.burios.uniql.persistence.mappings;

import cz.burios.uniql.persistence.internal.helper.DatabaseField;
import cz.burios.uniql.persistence.mappings.converters.Converter;

/**
 * INTERNAL
 * Common interface to those mappings that are used to map JPA Embedded objects.
 *  - ElementCollection {@literal ->} AggregateCollectionMapping
 *  - Embedded {@literal ->} AggregateObjectMapping
 *
 * This interface was build to ease the metadata processing, namely to avoid
 * costly casting between the mappings above since their common parent is 
 * DatabaseMapping.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 1.2
 */
public interface EmbeddableMapping {
    public String getAttributeName();
    public void addConverter(Converter converter, String attributeName);
    public void addOverrideManyToManyMapping(ManyToManyMapping mapping);
    public void addOverrideUnidirectionalOneToManyMapping(UnidirectionalOneToManyMapping mapping);
    public void addFieldTranslation(DatabaseField sourceFieldName, String aggregateFieldName);
    public void addNestedFieldTranslation(String attributeName, DatabaseField sourceField, String aggregateField);
}