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
 *     Guy Pelletier - initial API and implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.jpa.config;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public interface TenantDiscriminatorColumn {

    public TenantDiscriminatorColumn setColumnDefinition(String columnDefinition);
    public TenantDiscriminatorColumn setContextProperty(String contextProperty);
    public TenantDiscriminatorColumn setDiscriminatorType(String discriminatorType);
    public TenantDiscriminatorColumn setLength(Integer length);
    public TenantDiscriminatorColumn setName(String name);
    public TenantDiscriminatorColumn setPrimaryKey(Boolean primaryKey);
    public TenantDiscriminatorColumn setTable(String table);

}
