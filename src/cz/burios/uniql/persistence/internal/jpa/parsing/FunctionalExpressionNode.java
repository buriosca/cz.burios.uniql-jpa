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
package cz.burios.uniql.persistence.internal.jpa.parsing;

import cz.burios.uniql.persistence.queries.ObjectLevelReadQuery;
import cz.burios.uniql.persistence.queries.ReportQuery;


/**
 * INTERNAL
 * <p><b>Purpose</b>: The superclass of the Functional Expression nodes
 * <p><b>Responsibilities</b>:<ul>
 * <li> None
 * </ul>
 *    @author Jon Driscoll and Joel Lucuik
 *    @since TopLink 4.0
 */
public class FunctionalExpressionNode extends Node implements AliasableNode {

    /**
     * FunctionalExpressionNode constructor comment.
     */
    public FunctionalExpressionNode() {
        super();
    }
    
    /**
     * INTERNAL
     * Apply this node to the passed query
     */
    public void applyToQuery(ObjectLevelReadQuery theQuery, GenerationContext context) {
        if (theQuery.isReportQuery()) {
            ReportQuery reportQuery = (ReportQuery)theQuery;
            reportQuery.addAttribute(resolveAttribute(), generateExpression(context), (Class)getType());
        }
    }
    
    public boolean isAliasableNode(){
        return true;
    }
}
