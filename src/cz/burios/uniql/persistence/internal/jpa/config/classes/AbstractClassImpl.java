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

import cz.burios.uniql.persistence.internal.jpa.config.AbstractAccessorImpl;
import cz.burios.uniql.persistence.internal.jpa.config.changetracking.ChangeTrackingImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.AssociationOverrideImpl;
import cz.burios.uniql.persistence.internal.jpa.config.columns.AttributeOverrideImpl;
import cz.burios.uniql.persistence.internal.jpa.config.copypolicy.CloneCopyPolicyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.copypolicy.CopyPolicyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.copypolicy.InstantiationCopyPolicyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.BasicImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.ElementCollectionImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.EmbeddedIdImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.EmbeddedImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.IdImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.ManyToManyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.ManyToOneImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.OneToManyImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.OneToOneImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.TransformationImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.TransientImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.VariableOneToOneImpl;
import cz.burios.uniql.persistence.internal.jpa.config.mappings.VersionImpl;
import cz.burios.uniql.persistence.internal.jpa.config.nosql.NoSqlImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.OracleArrayImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.OracleObjectImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.PlsqlRecordImpl;
import cz.burios.uniql.persistence.internal.jpa.config.queries.PlsqlTableImpl;
import cz.burios.uniql.persistence.internal.jpa.config.structures.ArrayImpl;
import cz.burios.uniql.persistence.internal.jpa.config.structures.StructImpl;
import cz.burios.uniql.persistence.internal.jpa.config.structures.StructureImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.classes.ClassAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.accessors.mappings.IdAccessor;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AssociationOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AttributeOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.OracleArrayTypeMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.OracleObjectTypeMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.PLSQLRecordMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.queries.PLSQLTableMetadata;
import cz.burios.uniql.persistence.jpa.config.Array;
import cz.burios.uniql.persistence.jpa.config.AssociationOverride;
import cz.burios.uniql.persistence.jpa.config.AttributeOverride;
import cz.burios.uniql.persistence.jpa.config.Basic;
import cz.burios.uniql.persistence.jpa.config.ChangeTracking;
import cz.burios.uniql.persistence.jpa.config.CloneCopyPolicy;
import cz.burios.uniql.persistence.jpa.config.CopyPolicy;
import cz.burios.uniql.persistence.jpa.config.ElementCollection;
import cz.burios.uniql.persistence.jpa.config.Embedded;
import cz.burios.uniql.persistence.jpa.config.EmbeddedId;
import cz.burios.uniql.persistence.jpa.config.Id;
import cz.burios.uniql.persistence.jpa.config.InstantiationCopyPolicy;
import cz.burios.uniql.persistence.jpa.config.ManyToMany;
import cz.burios.uniql.persistence.jpa.config.ManyToOne;
import cz.burios.uniql.persistence.jpa.config.NoSql;
import cz.burios.uniql.persistence.jpa.config.OneToMany;
import cz.burios.uniql.persistence.jpa.config.OneToOne;
import cz.burios.uniql.persistence.jpa.config.OracleArray;
import cz.burios.uniql.persistence.jpa.config.OracleObject;
import cz.burios.uniql.persistence.jpa.config.PlsqlRecord;
import cz.burios.uniql.persistence.jpa.config.PlsqlTable;
import cz.burios.uniql.persistence.jpa.config.Struct;
import cz.burios.uniql.persistence.jpa.config.Structure;
import cz.burios.uniql.persistence.jpa.config.Transformation;
import cz.burios.uniql.persistence.jpa.config.Transient;
import cz.burios.uniql.persistence.jpa.config.VariableOneToOne;
import cz.burios.uniql.persistence.jpa.config.Version;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
@SuppressWarnings("unchecked")
public abstract class AbstractClassImpl<T extends ClassAccessor, R> extends AbstractAccessorImpl<T, R> {
    
    public AbstractClassImpl(T t) {
        super(t);
        
        getMetadata().setAttributes(new Attributes());
        getMetadata().setAssociationOverrides(new ArrayList<AssociationOverrideMetadata>());
        getMetadata().setAttributeOverrides(new ArrayList<AttributeOverrideMetadata>());
        getMetadata().setOracleArrayTypes(new ArrayList<OracleArrayTypeMetadata>());
        getMetadata().setOracleObjectTypes(new ArrayList<OracleObjectTypeMetadata>());
        getMetadata().setPLSQLRecords(new ArrayList<PLSQLRecordMetadata>());
        getMetadata().setPLSQLTables(new ArrayList<PLSQLTableMetadata>());
    }
    
    public Array addArray() {
        ArrayImpl array = new ArrayImpl();
        getMetadata().getAttributes().getArrays().add(array.getMetadata());
        return array;
    }
    
    public AssociationOverride addAssociationOverride() {
        AssociationOverrideImpl associationOverride = new AssociationOverrideImpl();
        getMetadata().getAssociationOverrides().add(associationOverride.getMetadata());
        return associationOverride;
    }

    public AttributeOverride addAttributeOverride() {
        AttributeOverrideImpl attributeOverride = new AttributeOverrideImpl();
        getMetadata().getAttributeOverrides().add(attributeOverride.getMetadata());
        return attributeOverride;
    }
    
    public Basic addBasic() {
        BasicImpl basic = new BasicImpl();
        getMetadata().getAttributes().getBasics().add(basic.getMetadata());
        return basic;
    }
    
    public ElementCollection addElementCollection() {
        ElementCollectionImpl elementCollection = new ElementCollectionImpl();
        getMetadata().getAttributes().getElementCollections().add(elementCollection.getMetadata());
        return elementCollection;
    }
    
    public Embedded addEmbedded() {
        EmbeddedImpl embedded = new EmbeddedImpl();
        getMetadata().getAttributes().getEmbeddeds().add(embedded.getMetadata());
        return embedded;
    }
    
    public Id addId() {
        IdImpl id = new IdImpl();
        getMetadata().getAttributes().getIds().add((IdAccessor) id.getMetadata());
        return id;
    }
    
    public ManyToMany addManyToMany() {
        ManyToManyImpl manyToMany = new ManyToManyImpl();
        getMetadata().getAttributes().getManyToManys().add(manyToMany.getMetadata());
        return manyToMany;
    }
    
    public ManyToOne addManyToOne() {
        ManyToOneImpl manyToOne = new ManyToOneImpl();
        getMetadata().getAttributes().getManyToOnes().add(manyToOne.getMetadata());
        return manyToOne;
    }
    
    public OneToMany addOneToMany() {
        OneToManyImpl oneToMany = new OneToManyImpl();
        getMetadata().getAttributes().getOneToManys().add(oneToMany.getMetadata());
        return oneToMany;
    }

    public OneToOne addOneToOne() {
        OneToOneImpl oneToOne = new OneToOneImpl();
        getMetadata().getAttributes().getOneToOnes().add(oneToOne.getMetadata());
        return oneToOne;
    }
    
    public OracleArray addOracleArray() {
        OracleArrayImpl oracleArray = new OracleArrayImpl();
        getMetadata().getOracleArrayTypes().add(oracleArray.getMetadata());
        return oracleArray;
    }
    
    public OracleObject addOracleObject() {
       OracleObjectImpl oracleObject = new OracleObjectImpl();
       getMetadata().getOracleObjectTypes().add(oracleObject.getMetadata());
       return oracleObject;
    }

    public PlsqlRecord addPlsqlRecord() {
        PlsqlRecordImpl plsqlRecord = new PlsqlRecordImpl();
        getMetadata().getPLSQLRecords().add(plsqlRecord.getMetadata());
        return plsqlRecord;
    }

    public PlsqlTable addPlsqlTable() {
        PlsqlTableImpl plsqlTable = new PlsqlTableImpl();
        getMetadata().getPLSQLTables().add(plsqlTable.getMetadata());
        return plsqlTable;
    }
    
    public Structure addStructure() {
        StructureImpl structure = new StructureImpl();
        getMetadata().getAttributes().getStructures().add(structure.getMetadata());
        return structure;
    }
    
    public Transformation addTransformation() {
        TransformationImpl transformation = new TransformationImpl();
        getMetadata().getAttributes().getTransformations().add(transformation.getMetadata());
        return transformation;
    }
    
    public Transient addTransient() {
        TransientImpl trans = new TransientImpl();
        getMetadata().getAttributes().getTransients().add(trans.getMetadata());
        return trans;
    }
    
    public VariableOneToOne addVariableOneToOne() {
        VariableOneToOneImpl variableOneToOne = new VariableOneToOneImpl();
        getMetadata().getAttributes().getVariableOneToOnes().add(variableOneToOne.getMetadata());
        return variableOneToOne;
    }
    
    public Version addVersion() {
        VersionImpl version = new VersionImpl();
        getMetadata().getAttributes().getVersions().add(version.getMetadata());
        return version;
    }
    
    public ChangeTracking setChangeTracking() {
        ChangeTrackingImpl changeTracking = new ChangeTrackingImpl();
        getMetadata().setChangeTracking(changeTracking.getMetadata());
        return changeTracking;
    }

    public R setClass(String cls) {
        getMetadata().setClassName(cls);
        return (R) this;
    }
    
    public CloneCopyPolicy setCloneCopyPolicy() {
        CloneCopyPolicyImpl cloneCopyPolicy = new CloneCopyPolicyImpl();
        getMetadata().setCloneCopyPolicy(cloneCopyPolicy.getMetadata());
        return cloneCopyPolicy;
    }

    public CopyPolicy setCopyPolicy() {
        CopyPolicyImpl copyPolicy = new CopyPolicyImpl();
        getMetadata().setCustomCopyPolicy(copyPolicy.getMetadata());
        return copyPolicy;
    }
    
    public R setCustomizer(String customizer) {
        getMetadata().setCustomizerClassName(customizer);
        return (R) this;
    }
    
    public EmbeddedId setEmbeddedId() {
        EmbeddedIdImpl embeddedId = new EmbeddedIdImpl();
        getMetadata().getAttributes().setEmbeddedId(embeddedId.getMetadata());
        return embeddedId;
    }
    
    public R setExcludeDefaultMappings(Boolean excludeDefaultMappings) {
        getMetadata().setExcludeDefaultMappings(excludeDefaultMappings);
        return (R) this;
    }
    
    public InstantiationCopyPolicy setInstantiationCopyPolicy() {
        InstantiationCopyPolicyImpl copyPolicy = new InstantiationCopyPolicyImpl();
        getMetadata().setInstantiationCopyPolicy(copyPolicy.getMetadata());
        return copyPolicy;
    }
    
    public NoSql setNoSql() {
        NoSqlImpl noSql = new NoSqlImpl();
        getMetadata().setNoSql(noSql.getMetadata());
        return noSql;
    }
    
    public R setMetadataComplete(Boolean metadataComplete) {
        getMetadata().setMetadataComplete(metadataComplete);
        return (R) this;
    }
    
    public R setParentClass(String parentClass) {
        getMetadata().setParentClassName(parentClass);
        return (R) this;
    }
    
    public Struct setStruct() {
        StructImpl struct = new StructImpl();
        getMetadata().setStruct(struct.getMetadata());
        return struct;
    }
}
