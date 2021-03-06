/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.orm;

import com.goblingift.orm.entities.User;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andre
 */
@Transactional
public class UserManager {
    
    @Autowired
    private UserMapper userMapper;
    
    @PersistenceContext
    EntityManager entityManager;
    
    private JdbcTemplate jdbcTemplate;
    
    
    public UserManager(DatabaseService databaseService) {
        this.jdbcTemplate = databaseService.getJdbcTemplate();
    }
    
    public User getUserById(int id) {
        User foundUser = entityManager.find(User.class, id);
        return foundUser;
    }
    
    /**
     * Uses native SQL query to delete user.
     * @param id
     * @return true if deleted with success, false if otherwise.
     */
    public boolean deleteUserWithNativeQuery(int id) {
        Query query = entityManager.createNativeQuery("DELETE FROM USER WHERE IDUSER=?");
        query.setParameter(1, id);
        return query.executeUpdate() > 0;
    }
    
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
    
    public void readWholeDatabase() {
        jdbcTemplate.query("SELECT * FROM USER", (ResultSet rs) -> System.out.println(rs.getString(2)));
    }
    
    public void printAllUsers() {
        jdbcTemplate.query("SELECT * FROM USER", new UserRowCallbackHandler());
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USER", new UserResultSetExtractor());
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
    
    public boolean addUser(User user) {
        String sqlQuery = "INSERT INTO USER (USERNAME,PASSWORD,ACTIVE) VALUES (?,?,?)";
        int updated = jdbcTemplate.update(sqlQuery, user.getUsername(), user.getPassword(), user.isActive());
        return updated > 0;
    }
    
    public boolean addUserAndThrowException(User user) {
        addUser(user);
        throw new RuntimeException("BAM! EXCEPTION!");
    }
    
    public boolean deleteUserByUsername(String username) {
        String sqlQuery = "DELETE FROM USER WHERE USERNAME = ?";
        int updated = jdbcTemplate.update(sqlQuery, username);
        return updated > 0;
    }
    
    public boolean updateUser(User user) {
        String sqlQuery = "UPDATE USER SET ACTIVE = ?,PASSWORD = ? WHERE USERNAME = ?";
        int updated = jdbcTemplate.update(sqlQuery, user.isActive(), user.getPassword(), user.getUsername());
        return updated > 0;
    }
}
