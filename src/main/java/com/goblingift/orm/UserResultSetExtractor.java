/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author andre
 */
public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        
        List<User> mappedUsers = new ArrayList<>();
        
        while (rs.next()) {
            User user = new User(rs.getString("IDUSER"), rs.getString("PASSWORD"), rs.getBoolean("ACTIVE"));
            mappedUsers.add(user);
        }
        return mappedUsers;        
    }
}
