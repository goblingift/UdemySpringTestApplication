/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 * @author andre
 */
public class UserManager {
    
    @Autowired
    private UserMapper userMapper;
    
    private JdbcTemplate jdbcTemplate;
    
    
    public UserManager(DatabaseService databaseService) {
        this.jdbcTemplate = databaseService.getJdbcTemplate();
    }
    
    public void readWholeDatabase() {
        jdbcTemplate.query("SELECT * FROM USER", (ResultSet rs) -> System.out.println(rs.getString(2)));
    }
    
    public User getUserByQueryForObject(int idUser) {
        String sqlQuery = "SELECT * FROM USER WHERE IDUSER=?";
        User user = jdbcTemplate.queryForObject(sqlQuery, new Object[]{idUser}, userMapper);
        return user;
    }
    
    public Map<String, Object> getUserByQueryForMap(int idUser) {
        String sqlQuery = "SELECT * FROM USER WHERE IDUSER=?";
        return jdbcTemplate.queryForMap(sqlQuery, new Object[]{idUser});
    }
    
    public List<Map<String, Object>> getUsersByQueryForList() {
        String sqlQuery = "SELECT * FROM USER";
        return jdbcTemplate.queryForList(sqlQuery);
    }
    
}
