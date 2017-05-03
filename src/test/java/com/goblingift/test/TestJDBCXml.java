/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.test;

import com.goblingift.bean.UserBean;
import com.goblingift.orm.User;
import com.goblingift.orm.UserManager;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
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
        User user = userManager.getUserByQueryForObject(1);
        System.out.println(user);
        
        Map<String, Object> userByQueryForMap = userManager.getUserByQueryForMap(1);
        System.out.println(userByQueryForMap);
        
        List<Map<String, Object>> usersByQueryForList = userManager.getUsersByQueryForList();
        for (Map<String, Object> actUser : usersByQueryForList) {
            System.out.println("Another user found: " + actUser);
        }
        
    }
}