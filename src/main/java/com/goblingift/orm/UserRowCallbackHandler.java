/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 * @author andre
 */
public class UserRowCallbackHandler implements RowCallbackHandler {

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("IDUSER: " + rs.getString("IDUSER"));
            System.out.println("USERNAME: " + rs.getString("USERNAME"));
            System.out.println("PASSWORD: " + rs.getString("PASSWORD"));
            System.out.println("ACTIVE: " + rs.getString("ACTIVE"));
        }
    }
}
