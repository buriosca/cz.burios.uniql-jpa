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
package cz.burios.uniql.persistence.internal.core.descriptors;

public abstract class CoreInstantiationPolicy {

    /**
     * Build and return a new instance, using the appropriate mechanism.
     */
    public abstract Object buildNewInstance();

    public abstract void useFactoryInstantiationPolicy(String factoryClassName, String methodName);
}
