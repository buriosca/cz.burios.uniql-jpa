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
package cz.burios.uniql.persistence.internal.oxm.record.deferred;

import org.xml.sax.SAXException;

import cz.burios.uniql.persistence.internal.oxm.record.UnmarshalRecord;

/**
 * <p><b>Purpose</b>: Class to represent the endElement event
 * <p><b>Responsibilities</b>:<ul>
 * <li> Execute the endElement event on the given unmarshalRecord with the specified arguments 
 * </ul>
 */
public class IgnorableWhitespaceEvent extends SAXEvent {
    private char[] characters;
    private int start;
    private int end;

    public IgnorableWhitespaceEvent(char[] theCharacters, int theStart, int theEnd) {
        super();
        characters = theCharacters;
        start = theStart;
        end = theEnd;
    }

    public void processEvent(UnmarshalRecord unmarshalRecord) throws SAXException {
        unmarshalRecord.ignorableWhitespace(characters, start, end);
    }
}
