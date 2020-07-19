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
 ******************************************************************************/
 
package cz.burios.uniql.persistence.internal.sessions.factories;

import cz.burios.uniql.persistence.descriptors.ClassDescriptor;
import cz.burios.uniql.persistence.internal.sessions.factories.model.log.DefaultSessionLogConfig;
import cz.burios.uniql.persistence.oxm.XMLDescriptor;
/**
 * INTERNAL:
 * OX mapping project provides back compatibility for toplink 
 * 10g and 11g sessions XML meta-data reading.
 */

public class XMLSessionConfigToplinkProject extends  XMLSessionConfigProject{
    public ClassDescriptor buildSessionConfigsDescriptor() {
        XMLDescriptor descriptor = (XMLDescriptor)super.buildSessionConfigsDescriptor();
        descriptor.setDefaultRootElement("toplink-sessions");
        return descriptor;
    }
    
    public ClassDescriptor buildLogConfigDescriptor() {
        XMLDescriptor descriptor = (XMLDescriptor)super.buildLogConfigDescriptor();
        descriptor.getInheritancePolicy().addClassIndicator(DefaultSessionLogConfig.class, "toplink-log");
        return descriptor;
    }

}
