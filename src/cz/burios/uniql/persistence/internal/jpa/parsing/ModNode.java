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

import cz.burios.uniql.persistence.exceptions.JPQLException;
import cz.burios.uniql.persistence.expressions.*;

public class ModNode extends ArithmeticFunctionNode {

    private Node denominator = null;

    /** 
     * INTERNAL 
     * Check the child nodes for an unqualified field access and if so,
     * replace it by a qualified field access.
     */
    public Node qualifyAttributeAccess(ParseTreeContext context) {
       if (left != null) {
           left = left.qualifyAttributeAccess(context);
       }
       if (denominator != null) {
           denominator = denominator.qualifyAttributeAccess(context);
       }
       return this;
    }

    /**
     * INTERNAL
     * Validate node and calculate its type.
     */
    public void validate(ParseTreeContext context) {
        TypeHelper typeHelper = context.getTypeHelper();
        if (left != null) {
            left.validate(context);
            left.validateParameter(context, typeHelper.getIntType());

            Object type = left.getType();
            if (!typeHelper.isIntegralType(type))
                throw JPQLException.invalidFunctionArgument(
                    context.getQueryInfo(), left.getLine(), left.getColumn(), 
                    "MOD", left.getAsString(), "integral type");
        }

        if (denominator != null) {
            denominator.validate(context);
            denominator.validateParameter(context, typeHelper.getIntType());

            Object denominatorType = denominator.getType();
            if (!typeHelper.isIntegralType(denominatorType))
                throw JPQLException.invalidFunctionArgument(
                    context.getQueryInfo(), denominator.getLine(), denominator.getColumn(), 
                    "MOD", denominator.getAsString(), "integral type");
        }

        setType(typeHelper.getIntType());
    }
    
    /** */
    public Expression generateExpression(GenerationContext context) {
        return ExpressionMath.mod(getLeft().generateExpression(context), 
                                  getDenominator().generateExpression(context));
    }

    // Accessors
    public Node getDenominator() {
        return denominator;
    }

    public void setDenominator(Node denominator) {
        this.denominator = denominator;
    }
}
