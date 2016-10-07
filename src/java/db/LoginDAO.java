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
    
    private static final String USER_LOGIN_QUERY = "SELECT id, username FROM user WHERE username = ? AND password = ?";
    private static final String USER_ID_QUERY = "SELECT id, username FROM user WHERE id = ?";
    private static final String USER_USERNAME_QUERY = "SELECT id, username FROM user WHERE username = ?";
    
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
    
}
