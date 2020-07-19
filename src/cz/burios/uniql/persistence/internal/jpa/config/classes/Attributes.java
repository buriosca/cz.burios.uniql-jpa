/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Doug Clarke - initial API and implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.jpa.config.classes;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.XMLAttributes;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.BasicAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.BasicCollectionAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.BasicMapAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.ElementCollectionAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.EmbeddedAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.IdAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.ManyToManyAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.ManyToOneAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.OneToManyAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.OneToOneAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.TransformationAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.TransientAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.VariableOneToOneAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.VersionAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.structures.ArrayAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.structures.StructureAccessor;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class Attributes extends XMLAttributes {

    public Attributes() {
        
        setIds(new ArrayList<IdAccessor>());
        setBasics(new ArrayList<BasicAccessor>());
        setVersions(new ArrayList<VersionAccessor>());
        setOneToOnes(new ArrayList<OneToOneAccessor>());
        setOneToManys(new ArrayList<OneToManyAccessor>());
        setBasicCollections(new ArrayList<BasicCollectionAccessor>());
        setBasicMaps(new ArrayList<BasicMapAccessor>());
        setArrays(new ArrayList<ArrayAccessor>());
        setElementCollections(new ArrayList<ElementCollectionAccessor>());
        setEmbeddeds(new ArrayList<EmbeddedAccessor>());
        setManyToManys(new ArrayList<ManyToManyAccessor>());
        setManyToOnes(new ArrayList<ManyToOneAccessor>());
        setStructures(new ArrayList<StructureAccessor>());
        setTransformations(new ArrayList<TransformationAccessor>());
        setTransients(new ArrayList<TransientAccessor>());
        setVariableOneToOnes(new ArrayList<VariableOneToOneAccessor>());
        
    }

}