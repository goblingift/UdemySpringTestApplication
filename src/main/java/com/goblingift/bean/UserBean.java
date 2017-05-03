/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class UserBean {
    
    @Value("${user_guest}")
    private String username;

    public int calculateRandom(int i) {
        System.out.println("Quizbean is calculating...");
        return 121;
    }
    
    public double takeOff(double amount) {
        System.out.println("Bank employee is takeOff " + amount + " bucks");
        return amount;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBean{" + "username=" + username + '}';
    }
    
}
