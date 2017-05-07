/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.orm;

import com.goblingift.orm.entities.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author andre
 */
public class UserManagerTransactionTemplate  {
    
    private TransactionTemplate transactionTemplate;
    private UserManager userManager;

    public UserManagerTransactionTemplate(PlatformTransactionManager transactionManager) {
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setTimeout(30);
    }
    
    public void doItInTransactionUserManager() {
        
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus ts) {
                System.out.println("status.isNewTransaction():" + ts.isNewTransaction());
                
                User testUser = new User("testTransactionUser", "testPw", true);
                boolean userAdded = userManager.addUser(testUser);
                System.out.println("Successful created user: " + userAdded);
                System.out.println("status.isCompleted():" + ts.isCompleted());
                
                userManager.printAllUsers();
            }
        });
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
}
