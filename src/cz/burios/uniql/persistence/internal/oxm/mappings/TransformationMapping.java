/*******************************************************************************
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Blaise Doughan - 2.5 - initial implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.oxm.mappings;

import java.util.List;

import cz.burios.uniql.persistence.core.descriptors.CoreDescriptor;
import cz.burios.uniql.persistence.core.mappings.CoreAttributeAccessor;
import cz.burios.uniql.persistence.internal.core.helper.CoreField;
import cz.burios.uniql.persistence.internal.core.queries.CoreContainerPolicy;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.internal.oxm.record.TransformationRecord;
import cz.burios.uniql.persistence.internal.oxm.record.UnmarshalRecord;
import cz.burios.uniql.persistence.internal.oxm.record.XMLRecord;

public interface TransformationMapping<
    ABSTRACT_SESSION extends CoreAbstractSession,
    ATTRIBUTE_ACCESSOR extends CoreAttributeAccessor,
    CONTAINER_POLICY extends CoreContainerPolicy,
    DESCRIPTOR extends CoreDescriptor,
    FIELD extends CoreField,
    TRANSFORMATION_RECORD extends TransformationRecord,
    XML_RECORD extends XMLRecord> extends Mapping<ABSTRACT_SESSION, ATTRIBUTE_ACCESSOR, CONTAINER_POLICY, DESCRIPTOR, FIELD, XML_RECORD> {
    /**
     * Add the name of field and the name of the method
     * that returns the value to be placed in said field
     * when the object is written to the database.
     * The method may take zero arguments, or it may
     * take a single argument of type
     * <code>cz.burios.uniql.persistence.sessions.Session</code>.
     */
    public void addFieldTransformation(String fieldName, String methodName);
    
    /**
     * INTERNAL:
     * Add the name of a field and the name of a class which implements
     * the FieldTransformer interface. When the object is written, the transform
     * method will be called on the FieldTransformer to acquire the value to put
     * in the field.
     */
    public void addFieldTransformerClassName(String fieldName, String className);

    /**
     * INTERNAL:
     * @return a vector which stores fields and their respective transformers.
     */
    public List<Object[]> getFieldToTransformers();

    /**
     * INTERNAL:
     * Extract value from the row and set the attribute to the value in the object.
     * @since EclipseLink 2.6.0
     */
    public Object readFromRowIntoObject(XML_RECORD row, Object object, ABSTRACT_SESSION executionSession, boolean isTargetProtected);

    /**
     * To set the attribute method name. The method is invoked internally by TopLink
     * to retrieve the value to store in the domain object. The method receives Record
     * as its parameter and optionally Session, and should extract the value from the
     * record to set into the object, but should not set the value on the object, only return it.
     */
    public void setAttributeTransformation(String methodName);
    
    /**
     * INTERNAL:
     * Set the Attribute Transformer Class Name
     * @param className
     */
    public void setAttributeTransformerClassName(String className);
    
    /**
     * Used to specify whether the value of this mapping may be null.
     * This is used when generating DDL.
     */
    public void setIsOptional(boolean isOptional);

    /**
     * INTERNAL:
     * Put value into a record keyed on field.
     * @since EclipseLink 2.6.0
     */
    public void writeFromAttributeIntoRow(UnmarshalRecord unmarshalRecord, Field field, Object value, boolean isElement);

}