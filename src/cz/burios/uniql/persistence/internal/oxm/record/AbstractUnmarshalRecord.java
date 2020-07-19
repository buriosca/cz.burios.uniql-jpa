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
 *     Blaise Doughan - 2.5 - initial implementation
 ******************************************************************************/
package cz.burios.uniql.persistence.internal.oxm.record;

import cz.burios.uniql.persistence.internal.core.helper.CoreField;
import cz.burios.uniql.persistence.internal.core.sessions.CoreAbstractSession;
import cz.burios.uniql.persistence.internal.oxm.Unmarshaller;

/**
 * This class represents unmarshal record behaviour that is common to all XML 
 * platforms.
 */
public interface AbstractUnmarshalRecord<
    ABSTRACT_SESSION extends CoreAbstractSession,
    FIELD extends CoreField,
    UNMARSHALLER extends Unmarshaller> extends XMLRecord<ABSTRACT_SESSION> {

    public Object get(FIELD field);

    public UNMARSHALLER getUnmarshaller();

    public String resolveNamespacePrefix(String prefix);

}
