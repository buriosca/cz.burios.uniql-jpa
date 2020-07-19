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
package cz.burios.uniql.persistence.sessions.remote.corba.sun;


/**
 * INTERNAL:
* cz/burios/uniql/persistence/remote/corba/sun/CORBARemoteSessionControllerOperations.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from CorbaRemoteSessionControllerSun.idl
* Monday, November 19, 2001 1:51:44 o'clock PM EST
*/
public interface CORBARemoteSessionControllerOperations {
    cz.burios.uniql.persistence.internal.sessions.remote.Transporter getLogin();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter getDefaultReadOnlyClasses();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorCurrentIndex(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter commitRootUnitOfWork(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorAbsolute(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0, int arg1);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter cursoredStreamNextPage(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0, int arg1);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter executeQuery(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorFirst(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorAfterLast(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter cursoredStreamClose(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter getSequenceNumberNamed(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorClose(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter processCommand(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter cursorSelectObjects(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorLast(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter executeNamedQuery(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0, cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg1, cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg2);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorBeforeFirst(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorIsBeforeFirst(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter beginTransaction();
    
    cz.burios.uniql.persistence.internal.sessions.remote.Transporter beginEarlyTransaction();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter initializeIdentityMapsOnServerSession();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorIsLast(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorSize(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorIsFirst(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter getDescriptorForAlias(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter getDescriptor(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter cursoredStreamSize(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorRelative(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0, int arg1);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter commitTransaction();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter rollbackTransaction();

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter instantiateRemoteValueHolderOnServer(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorNextObject(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorPreviousObject(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);

    cz.burios.uniql.persistence.internal.sessions.remote.Transporter scrollableCursorIsAfterLast(cz.burios.uniql.persistence.internal.sessions.remote.Transporter arg0);
}// interface CORBARemoteSessionControllerOperations