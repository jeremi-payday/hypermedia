/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.User;

/**
 *
 * @author Xblade45
 */
public class LoginDAO {
    
    public static enum MESSAGE_CODE {
        INVALID_USERNAME,
        INVALID_PASSWORD,
        INVALID_FIRSTNAME,
        INVALID_LASTNAME,
        INVALID_ADDRESS,
        INVALID_AGE,
        USER_DOES_NOT_EXIST,
        USER_ALREADY_EXISTS,
        REGISTERING_ERROR,
        USER_REGISTERED
    }
    
    private static final String USER_LOGIN_QUERY = "SELECT id, username FROM user WHERE username = ? AND password = ?";
    private static final String USER_ID_QUERY = "SELECT id, username, adminStatus FROM user WHERE id = ?";
    private static final String USER_USERNAME_QUERY = "SELECT id, username, adminStatus FROM user WHERE username = ?";
    private static final String USER_REGISTER = "INSERT INTO user (username, password, firstname, lastname, address, age, adminStatus) VALUES(?, ?, ?, ?, ?, ?, 0)";
    
    private Connection connection;
    
    public LoginDAO(Connection connection){
        this.connection = connection;
    }
    
    public boolean isUserValid(String username, String password){
        boolean valid = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USER_LOGIN_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }
    
    public User getUser(int id){
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USER_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet.getInt(1), resultSet.getString(2), 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public User getUser(String username){
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USER_USERNAME_QUERY);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet.getInt(1), resultSet.getString(2), 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public boolean registerUser(User user){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USER_REGISTER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
