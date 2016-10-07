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
        //errors
        USER_DOES_NOT_EXIST,
        USER_ALREADY_EXISTS,
        INVALID_PASSWORD,
        REGISTERING_ERROR,
    
        //success
        USER_REGISTERED
    }
    
    private static final String USER_LOGIN_QUERY = "SELECT id, username FROM user WHERE username = ? AND password = ?";
    private static final String USER_ID_QUERY = "SELECT id, username FROM user WHERE id = ?";
    private static final String USER_USERNAME_QUERY = "SELECT id, username FROM user WHERE username = ?";
    private static final String USER_REGISTER = "INSERT INTO user (username, password) VALUES(?, ?)";
    
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
                user = new User(resultSet.getInt(1), resultSet.getString(2));
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
                user = new User(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public LoginDAO.MESSAGE_CODE registerUser(User user){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USER_REGISTER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return LoginDAO.MESSAGE_CODE.REGISTERING_ERROR;
        }
        return LoginDAO.MESSAGE_CODE.USER_REGISTERED;
    }
    
}
