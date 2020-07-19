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
package cz.burios.uniql.persistence.internal.sessions.factories.model.log;


/**
 * INTERNAL:
 */
public abstract class LogConfig {
    private LoggingOptionsConfig m_loggingOptions;

    public LogConfig() {
    }

    public void setLoggingOptions(LoggingOptionsConfig loggingOptions) {
        m_loggingOptions = loggingOptions;
    }

    public LoggingOptionsConfig getLoggingOptions() {
        return m_loggingOptions;
    }
}
