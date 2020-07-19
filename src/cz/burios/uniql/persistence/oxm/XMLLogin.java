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
package cz.burios.uniql.persistence.oxm;

import cz.burios.uniql.persistence.internal.databaseaccess.*;
import cz.burios.uniql.persistence.internal.helper.Helper;
import cz.burios.uniql.persistence.internal.oxm.mappings.Login;
import cz.burios.uniql.persistence.oxm.documentpreservation.DocumentPreservationPolicy;
import cz.burios.uniql.persistence.oxm.platform.SAXPlatform;
import cz.burios.uniql.persistence.sessions.DatasourceLogin;

/**
 * In OX, the platform determines which parsing method will be used, DOM vs SAX.
 *
 *<p><em>Code Sample</em><br>
 * <code>
 *
 * XMLLogin xmlLogin = new XMLLogin(new cz.burios.uniql.persistence.oxm.platform.DOMPlatform);<br>
 * Project myProject = new MyTopLinkProject(xmlLogin)<br>
 *
 * </code>
 *
 * @see cz.burios.uniql.persistence.oxm.platform.SAXPlatform
 * @see cz.burios.uniql.persistence.oxm.platform.DOMPlatform
 *
 */
public class XMLLogin extends DatasourceLogin implements Login<Platform> {
    private boolean equalNamespaceResolvers;

    private DocumentPreservationPolicy documentPreservationPolicy;
    /**
     * Default constructor.
     * Sets the platform to be the default platform which is cz.burios.uniql.persistence.oxm.platform.SAXPlatform.
     */
    public XMLLogin() {
        this(new SAXPlatform());        
    }

    /**
     * Constructor, create a new XMLLogin based on the given platform.
     * Valid platforms are instances of cz.burios.uniql.persistence.oxm.platform.DOMPlaform and
     * instances of cz.burios.uniql.persistence.oxm.platform.SAXPlatform.
     * @param platform The platform to base this login on
     */
    public XMLLogin(Platform platform) {
        super(platform);
        equalNamespaceResolvers = true;
    }

    /**
     * INTERNAL:
     * Returns the appropriate accessor
     * @return an instance of cz.burios.uniql.persistence.internal.oxm.XMLAccessor
     */
    public Accessor buildAccessor() {
        return new cz.burios.uniql.persistence.internal.oxm.XMLAccessor();
    }

    /**
     * Return a String representation of the object.
     * @return a string representation of the receiver
     */
    public String toString() {
        return Helper.getShortClassName(this) + "(" + this.getUserName() + ")\n\t( " + this.getPlatformClassName() + ")";
    }

    @Override
    public DocumentPreservationPolicy getDocumentPreservationPolicy() {
        return this.documentPreservationPolicy;
    }

    @Override
    public void setDocumentPreservationPolicy(DocumentPreservationPolicy policy) {
        this.documentPreservationPolicy = policy;
    }

    public void setEqualNamespaceResolvers(boolean equalNRs) {
        this.equalNamespaceResolvers = equalNRs;
    }

    @Override
    public boolean hasEqualNamespaceResolvers() {
        return equalNamespaceResolvers;
    }
}
