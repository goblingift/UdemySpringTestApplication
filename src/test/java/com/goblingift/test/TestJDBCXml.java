/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.test;

import com.goblingift.orm.User;
import com.goblingift.orm.UserManager;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *
 * @author andre
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-jdbc.xml"})
public class TestJDBCXml {
    @Autowired
    private ApplicationContext context;
    
    @Test
    public void doTest() {
        UserManager userManager = context.getBean(UserManager.class);
        
        User peter = new User("Peter", "geheim123", true);
        userManager.addUser(peter);
        displayAllUsers();
        peter.setActive(false);
        userManager.updateUser(peter);
        displayAllUsers();
        
        userManager.printAllUsers();
        
        List<User> allUsers = userManager.getAllUsers();
        for (User actUser : allUsers) {
            System.out.println("ITS:" + actUser);
        }
    }
    
    private void displayAllUsers() {
        UserManager userManager = context.getBean(UserManager.class);
        System.out.println("This are the users:");
        List<Map<String, Object>> usersByQueryForList = userManager.getUsersByQueryForList();
        for (Map<String, Object> actUser : usersByQueryForList) {
            System.out.println("Another user found: " + actUser);
        }
    }
    
}