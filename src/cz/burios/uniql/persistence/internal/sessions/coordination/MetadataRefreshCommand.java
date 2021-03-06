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
 *     01/19/2012-2.4 Chris Delahunt
 *       - 368490: Add support for Metadata to be refreshed through RCM 
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.sessions.coordination;

import java.util.Map;

import cz.burios.uniql.persistence.internal.sessions.AbstractSession;
import cz.burios.uniql.persistence.sessions.coordination.Command;
import cz.burios.uniql.persistence.sessions.coordination.MetadataRefreshListener;


/**
 * <p>
 * <b>Purpose</b>: A Command implementation used to signal JPA EntityManagerFactory to refresh its 
 * metadata.
 * <p>
 * @author Chris Delahunt
 * @since Eclipselink 2.3
 */
public class MetadataRefreshCommand extends Command {
    protected Map properties;
    
    public MetadataRefreshCommand(Map properties) {
        super();
        this.properties = properties;
    }

    @Override
    public void executeWithSession(AbstractSession session) {
        MetadataRefreshListener listener = session.getRefreshMetadataListener();
        if (listener != null) {
            listener.triggerMetadataRefresh(properties);
        }
    }

}
