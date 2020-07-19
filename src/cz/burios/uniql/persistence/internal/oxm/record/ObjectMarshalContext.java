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
 package cz.burios.uniql.persistence.internal.oxm.record;

import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.internal.oxm.NamespaceResolver;
import cz.burios.uniql.persistence.internal.oxm.NodeValue;
import cz.burios.uniql.persistence.internal.oxm.XPathFragment;
import cz.burios.uniql.persistence.internal.oxm.XPathNode;
import cz.burios.uniql.persistence.internal.oxm.mappings.Mapping;

/**
 * An implementation of MarshalContext for handling plain old java objects that
 * are mapped to XML. 
 */
public class ObjectMarshalContext implements MarshalContext {

    private static final ObjectMarshalContext INSTANCE = new ObjectMarshalContext();

    public static ObjectMarshalContext getInstance() {
        return INSTANCE;
    }

    private ObjectMarshalContext() {
        super();
    }

    public MarshalContext getMarshalContext(int index) {
        return this;
    }

    public int getNonAttributeChildrenSize(XPathNode xPathNode) {
        return xPathNode.getNonAttributeChildren().size();
    }

    public Object getNonAttributeChild(int index, XPathNode xPathNode) {
        return xPathNode.getNonAttributeChildren().get(index);
    }

    public Object getAttributeValue(Object object, Mapping mapping) {
        return mapping.getAttributeValueFromObject(object);
    }

    public boolean marshal(NodeValue nodeValue, XPathFragment xPathFragment, MarshalRecord marshalRecord, Object object, CoreAbstractSession session, NamespaceResolver namespaceResolver) {
        return nodeValue.marshal(xPathFragment, marshalRecord, object, session, namespaceResolver, this);
    }
    
    public boolean marshal(NodeValue nodeValue, XPathFragment xPathFragment, MarshalRecord marshalRecord, Object object, CoreAbstractSession session, NamespaceResolver namespaceResolver, XPathFragment rootFragment) {
        return nodeValue.marshal(xPathFragment, marshalRecord, object, session, namespaceResolver, this, rootFragment);
    }

}
