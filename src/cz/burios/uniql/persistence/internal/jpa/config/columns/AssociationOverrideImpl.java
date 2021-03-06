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
package cz.burios.uniql.persistence.internal.jpa.config.columns;

import java.util.ArrayList;

import cz.burios.uniql.persistence.internal.jpa.config.tables.JoinTableImpl;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.AssociationOverrideMetadata;
import cz.burios.uniql.persistence.internal.jpa.metadata.columns.JoinColumnMetadata;
import cz.burios.uniql.persistence.jpa.config.AssociationOverride;
import cz.burios.uniql.persistence.jpa.config.ForeignKey;
import cz.burios.uniql.persistence.jpa.config.JoinColumn;
import cz.burios.uniql.persistence.jpa.config.JoinTable;

/**
 * JPA scripting API implementation.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class AssociationOverrideImpl extends AbstractOverrideImpl<AssociationOverrideMetadata, AssociationOverride> implements AssociationOverride {

    public AssociationOverrideImpl() {
        super(new AssociationOverrideMetadata());
        
        getMetadata().setJoinColumns(new ArrayList<JoinColumnMetadata>());
    }

    public JoinColumn addJoinColumn() {
        JoinColumnImpl joinColumn = new JoinColumnImpl();
        getMetadata().getJoinColumns().add(joinColumn.getMetadata());
        return joinColumn;
    }

    public ForeignKey setForeignKey() {
        ForeignKeyImpl foreignKey = new ForeignKeyImpl();
        getMetadata().setForeignKey(foreignKey.getMetadata());
        return foreignKey;
    }
    
    public JoinTable setJoinTable() {
        JoinTableImpl joinTable = new JoinTableImpl();
        getMetadata().setJoinTable(joinTable.getMetadata());
        return joinTable;
    }

}
