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
 *     Guy Pelletier - initial API and implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.jpa.config.classes;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.columns.DiscriminatorColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.ForeignKeyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.PrimaryKeyJoinColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.config.converters.ConvertImpl;
import cz.burios.uniql.persistence.internal.jpa.config.inheritance.InheritanceImpl;
import cz.burios.uniql.persistence.internal.jpa.config.tables.IndexImpl;
import cz.burios.uniql.persistence.internal.jpa.config.tables.SecondaryTableImpl;
import cz.burios.uniql.persistence.internal.jpa.config.tables.TableImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.EntityAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.PrimaryKeyForeignKeyMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.PrimaryKeyJoinColumnMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.converters.ConvertMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.tables.IndexMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.tables.SecondaryTableMetadata;
import cz.burios.uniql.persistence.jpa.config.Convert;
import cz.burios.uniql.persistence.jpa.config.DiscriminatorColumn;
import cz.burios.uniql.persistence.jpa.config.Entity;
import cz.burios.uniql.persistence.jpa.config.ForeignKey;
import cz.burios.uniql.persistence.jpa.config.Index;
import cz.burios.uniql.persistence.jpa.config.Inheritance;
import cz.burios.uniql.persistence.jpa.config.PrimaryKeyJoinColumn;
import cz.burios.uniql.persistence.jpa.config.SecondaryTable;
import cz.burios.uniql.persistence.jpa.config.Table;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class EntityImpl extends AbstractMappedClassImpl<EntityAccessor, Entity> implements Entity {
    
    public EntityImpl() {
        super(new EntityAccessor());
        
        getMetadata().setConverts(new ArrayList<ConvertMetadata>());
        getMetadata().setIndexes(new ArrayList<IndexMetadata>());
        getMetadata().setPrimaryKeyJoinColumns(new ArrayList<PrimaryKeyJoinColumnMetadata>());
        getMetadata().setSecondaryTables(new ArrayList<SecondaryTableMetadata>());
    }

    public Convert addConvert() {
        ConvertImpl convert = new ConvertImpl();
        getMetadata().getConverts().add(convert.getMetadata());
        return convert;
    }
    
    public Index addIndex() {
        IndexImpl index = new IndexImpl();
        getMetadata().getIndexes().add(index.getMetadata());
        return index;
    }

    public PrimaryKeyJoinColumn addPrimaryKeyJoinColumn() {
        PrimaryKeyJoinColumnImpl primaryKeyJoinColumn = new PrimaryKeyJoinColumnImpl();
        getMetadata().getPrimaryKeyJoinColumns().add(primaryKeyJoinColumn.getMetadata());
        return primaryKeyJoinColumn;
    }

    public SecondaryTable addSecondaryTable() {
        SecondaryTableImpl secondaryTable = new SecondaryTableImpl();
        getMetadata().getSecondaryTables().add(secondaryTable.getMetadata());
        return secondaryTable;
    }

    public Entity setAccess(String access) {
        getMetadata().setAccess(access);
        return this;
    }

    public Entity setCascadeOnDelete(Boolean cascadeOnDelete) {
        getMetadata().setCascadeOnDelete(cascadeOnDelete);
        return this;
    }

    public Entity setClassExtractor(String classExtractor) {
        getMetadata().setClassExtractorName(classExtractor);
        return this;
    }

    public DiscriminatorColumn setDiscriminatorColumn() {
        DiscriminatorColumnImpl column = new DiscriminatorColumnImpl();
        getMetadata().setDiscriminatorColumn(column.getMetadata());
        return column;
    }

    public Entity setDiscriminatorValue(String discriminatorValue) {
        getMetadata().setDiscriminatorValue(discriminatorValue);
        return this;
    }

    public Inheritance setInheritance() {
        InheritanceImpl inheritance = new InheritanceImpl();
        getMetadata().setInheritance(inheritance.getMetadata());
        return inheritance;
    }
    
    public ForeignKey setPrimaryKeyForeignKey() {
        ForeignKeyImpl foreignKey = new ForeignKeyImpl();
        getMetadata().setPrimaryKeyForeignKey(new PrimaryKeyForeignKeyMetadata(foreignKey.getMetadata()));
        return foreignKey;
    }

    public Table setTable() {
        TableImpl table = new TableImpl();
        getMetadata().setTable(table.getMetadata());
        return table;
    }

}
