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
package cz.burios.uniql.persistence.internal.jpa.config.mappings;

import cz.burios.uniql.persistence.internal.jpa.config.cache.CacheIndexImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.ColumnImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.FieldImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.GeneratedValueImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.SequenceGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.TableGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.sequencing.UuidGeneratorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.tables.IndexImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.BasicAccessor;
import cz.burios.uniql.persistence.jpa.config.CacheIndex;
import cz.burios.uniql.persistence.jpa.config.Column;
import cz.burios.uniql.persistence.jpa.config.Field;
import cz.burios.uniql.persistence.jpa.config.GeneratedValue;
import cz.burios.uniql.persistence.jpa.config.Index;
import cz.burios.uniql.persistence.jpa.config.ReturnInsert;
import cz.burios.uniql.persistence.jpa.config.SequenceGenerator;
import cz.burios.uniql.persistence.jpa.config.TableGenerator;
import cz.burios.uniql.persistence.jpa.config.UuidGenerator;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public class AbstractBasicMappingImpl<T extends BasicAccessor, R> extends AbstractDirectMappingImpl<T, R> {
    
    public AbstractBasicMappingImpl(T t) {
        super(t);
    }

    public CacheIndex setCacheIndex() {
        CacheIndexImpl cacheIndex = new CacheIndexImpl();
        getMetadata().setCacheIndex(cacheIndex.getMetadata());
        return cacheIndex;
    }

    public Column setColumn() {
        ColumnImpl column = new ColumnImpl();
        getMetadata().setColumn(column.getMetadata());
        return column;
    }

    public Field setField() {
        FieldImpl field = new FieldImpl();
        getMetadata().setField(field.getMetadata());
        return field;
    }

    public GeneratedValue setGeneratedValue() {
        GeneratedValueImpl generatedValue = new GeneratedValueImpl();
        getMetadata().setGeneratedValue(generatedValue.getMetadata());
        return generatedValue;
    }

    public Index setIndex() {
        IndexImpl index = new IndexImpl();
        getMetadata().setIndex(index.getMetadata());
        return index;
    }

    public R setMutable(Boolean mutable) {
        getMetadata().setMutable(mutable);
        return (R) this;
    }

    public ReturnInsert setReturnInsert() {
        ReturnInsertImpl returnInsert = new ReturnInsertImpl();
        getMetadata().setReturnInsert(returnInsert.getMetadata());
        return returnInsert;
    }

    public R setReturnUpdate() {
        getMetadata().setReturnUpdate(true);
        return (R) this;
    }

    public SequenceGenerator setSequenceGenerator() {
        SequenceGeneratorImpl sequenceGenerator = new SequenceGeneratorImpl();
        getMetadata().setSequenceGenerator(sequenceGenerator.getMetadata());
        return sequenceGenerator;
    }
    
    public TableGenerator setTableGenerator() {
        TableGeneratorImpl tableGenerator = new TableGeneratorImpl();
        getMetadata().setTableGenerator(tableGenerator.getMetadata());
        return tableGenerator;
    }
    
    public UuidGenerator setUuidGenerator() {
        UuidGeneratorImpl uuidGenerator = new UuidGeneratorImpl();
        getMetadata().setUuidGenerator(uuidGenerator.getMetadata());
        return uuidGenerator;
    }
}
