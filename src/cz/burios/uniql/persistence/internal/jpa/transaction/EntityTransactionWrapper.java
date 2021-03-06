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
package cz.burios.uniql.persistence.internal.jpa.transaction;

import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import cz.burios.uniql.persistence.exceptions.TransactionException;
import cz.burios.uniql.persistence.internal.jpa.EntityManagerImpl;
import cz.burios.uniql.persistence.internal.jpa.transaction.EntityTransactionImpl;
import cz.burios.uniql.persistence.internal.localization.ExceptionLocalization;
import cz.burios.uniql.persistence.internal.sessions.UnitOfWorkImpl;

/**
 * INTERNAL:
 * JDK 1.5 specific version of EntityTransactionWrapper. Differs from the JDK 1.4 version
 * in that it implements a different version of the TransactionWrapper interface, 
 * uses a different EntityManager, and returns a different EntityTransaction version.
 * 
 * @see cz.burios.uniql.persistence.internal.jpa.transaction.EntityTransactionWrapper
 */
public class EntityTransactionWrapper extends TransactionWrapperImpl implements TransactionWrapper {
    protected EntityTransactionImpl entityTransaction;

    public EntityTransactionWrapper(EntityManagerImpl entityManager) {
        super(entityManager);
    }
    

    /**
     * INTERNAL:
     * This method will be used to check for a transaction and throws exception if none exists.
     * If this method returns without exception then a transaction exists.
     * This method must be called before accessing the localUOW.
     */
    public Object checkForTransaction(boolean validateExistence){
        if (entityTransaction != null && entityTransaction.isActive()) {
            return entityTransaction;
        }
        if (validateExistence){
            throwCheckTransactionFailedException();
        }
        return null;
    }

    public EntityManagerImpl getEntityManager(){
        return entityManager;
    }

    /**
     * Lazy initialize the EntityTransaction.
     * There can only be one EntityTransaction at a time.
     */
    public EntityTransaction getTransaction(){
        if (entityTransaction == null){
            entityTransaction = new EntityTransactionImpl(this);
        }
        return entityTransaction;
    }

    public boolean isJoinedToTransaction(UnitOfWorkImpl uow){
        return (entityTransaction != null) && entityTransaction.isActive();
    }
    
    /**
     * Mark the current transaction so that the only possible
     * outcome of the transaction is for the transaction to be
     * rolled back.
     * This is an internal method and if the txn is not active will do nothing
     */
     public void setRollbackOnlyInternal(){
         if (this.getTransaction().isActive()){
             this.getTransaction().setRollbackOnly();
         }
     }

    protected void throwCheckTransactionFailedException() {
        throw new TransactionRequiredException(TransactionException.transactionNotActive().getMessage());
    }    

    public void registerIfRequired(UnitOfWorkImpl uow){
        throw new TransactionRequiredException(ExceptionLocalization.buildMessage("join_trans_called_on_entity_trans"));// no JTA transactions availab
    }
}
