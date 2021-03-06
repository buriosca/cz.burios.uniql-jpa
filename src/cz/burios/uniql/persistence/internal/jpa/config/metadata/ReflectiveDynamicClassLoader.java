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
package cz.burios.uniql.persistence.internal.jpa.config.metadata;

import java.lang.reflect.Method;

import cz.burios.uniql.persistence.dynamic.DynamicClassLoader;

/**
 * Extended {@link DynamicClassLoader} used to create classes using reflective
 * access to defineClass. This causes the class to be created on the provided
 * delegate/parent loader instead of just within this custom loader. Required
 * for frameworks that don't take and use a loader.
 * 
 * @author dclarke
 * @since EclipseLink 2.4.2
 */
public class ReflectiveDynamicClassLoader extends DynamicClassLoader {

    private Method defineClassMethod;

    public ReflectiveDynamicClassLoader(ClassLoader delegate) {
        super(delegate);
    }

    protected Method getDefineClassMethod() {
        if (this.defineClassMethod == null) {
            try {
                this.defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", new Class<?>[] { String.class, byte[].class, int.class, int.class });
                this.defineClassMethod.setAccessible(true);
            } catch (Exception e) {
                throw new RuntimeException("ReflectiveDynamicClassLoader could not access defineClass method", e);
            }
        }
        return this.defineClassMethod;
    }

    @Override
    protected Class<?> defineDynamicClass(String name, byte[] b) {
        try {
            return (Class<?>) getDefineClassMethod().invoke(getParent(), new Object[] { name, b, 0, b.length });
        } catch (Exception e) {
            throw new RuntimeException("ReflectiveDynamicClassLoader falied to create class: " + name, e);
        }
    }

}
