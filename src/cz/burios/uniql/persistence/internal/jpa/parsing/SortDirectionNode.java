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


import cz.burios.uniql.persistence.expressions.Expression;
import cz.burios.uniql.persistence.expressions.ExpressionOperator;

public class SortDirectionNode extends Node {
    private int sortDirection = ExpressionOperator.Ascending;

    /**
     * INTERNAL
     * Return the parent expression unmodified
     */
    public Expression addToExpression(Expression parentExpression, GenerationContext context) {
        return parentExpression.getFunction(getSortDirection());
    }

    public void useAscending() {
        setSortDirection(ExpressionOperator.Ascending);
    }

    public void useDescending() {
        setSortDirection(ExpressionOperator.Descending);
    }

    // Accessors
    public int getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(int sortDirection) {
        this.sortDirection = sortDirection;
    }
}
