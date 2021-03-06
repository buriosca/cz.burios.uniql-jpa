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

import cz.burios.uniql.persistence.core.sessions.CoreLogin;
import cz.burios.uniql.persistence.internal.core.databaseaccess.CorePlatform;
import cz.burios.uniql.persistence.oxm.documentpreservation.DocumentPreservationPolicy;

public interface Login<PLATFORM extends CorePlatform> extends CoreLogin<PLATFORM> {

    public DocumentPreservationPolicy getDocumentPreservationPolicy();

    public boolean hasEqualNamespaceResolvers();
    
    public void setDocumentPreservationPolicy(DocumentPreservationPolicy documentPreservationPolicy);

}