/*******************************************************************************
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     24 April 2013-2.5.1 ailitche
 *       SerializedObjectPolicy initial API and implementation
 ******************************************************************************/  
package cz.burios.uniql.persistence.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.persistence.Column;

import cz.burios.uniql.persistence.config.QueryHints;

/**
 * SerializedObject annotation is used to set an 
 * cz.burios.uniql.persistence.descriptors.SerializedObjectPolicy on an Entity or MappedSuperClass.
 * 
 * If SerializedObjectPolicy is specified Eclipselink writes out the whole entity object with its
 * privately owned (and nested privately owned) entities and element collections into an additional 
 * (likely BLOB) field in the database. That field could be specified in the annotation, it defaults to "SOP" in the main table. 
 * 
 * <p>
 * Examples:
 * <pre><code>
 * {@literal @}Entity
 * {@literal @}SerializedObject(MySerializedObjectPolicy.class);
 * public class Employee {...
 * </code></pre>
 * <pre><code>
 * {@literal @}Entity
 * {@literal @}SerializedObject(value = MySerializedObjectPolicy.class, column = @Column(name="SERIALIZED"));
 * public class Address {...
 * </code></pre>
 * 
 * If SerializedObjectPolicy is set on an entity then SerializedObjectPolicies with the same field are set
 * on all inheriting entities.
 * 
 * The query that uses SerializedObjectPolicy extracts the whole object from that field.
 * To read object(s) using SerializedObjectPolicy the query should specify
 * @see QueryHints#SERIALIZED_OBJECT
 *  
 * <p>
 * Examples:
 * <pre><code>
 * Query query = em.createQuery("SELECT e FROM Employee e").setHint(QueryHints.SERIALIZED_OBJECT, "true");
 * </code></pre>
 * <pre><code>
 * Map hints = new HashMap();
 * hints.put("eclipselink.serialized-object", "true");
 * Address address = em.find(Address.class, id, hints);
 * </code></pre>
 *
 * The goal is to make reads from the database faster.
 * The draw back is slower writes into the database.
 * So SerializedObjectPolicy may make sense for read-only / read-mostly application 
 * for Entity, which always loads all its dependent entities and / or ElementCollections.
 * 
 * In case the serialized object column contains null or obsolete version of the object
 * the query using SerializedObjectPolicy would either throw exception or - if all other fields have been read, too -
 * would build the object using these fields (exactly as in case SerializedObjectPolicy is not used). 
 * 
 * Note that currently no default implementation of SerializedObjectPolicy is available
 * and this class should be provided by the user.
 * 
 * @see cz.burios.uniql.persistence.descriptors.SerializedObjectPolicy
 * 
 * @author ailitche
 * @since EclipseLink 2.5.1
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface SerializedObject {
    /**
     * The Class that implements cz.burios.uniql.persistence.descriptors.SerializedObjectPolicy interface.
     * This class must be specified. 
     */ 
    Class value();
    
    /**
     * (Optional) The column that holds the serialized object. By default it's a BLOB column named "SOP" in entity's main table. 
     */
    Column column() default @Column(name="SOP");
}
