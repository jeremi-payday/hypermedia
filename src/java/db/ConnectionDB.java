/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guitp
 */
public class ConnectionDB {
    
    private static final String URL_CONNECTION = "jdbc:sqlite:/WEB-INF/sqlite.db";
    
    private Connection connection;
    
    public ConnectionDB(){
        init();
    }
    
    private void init(){
        connect();
    }
    
    public void connect(){
        if(connection != null){
            try {
                Class.forName("org.sqlite.JDBC");
                this.connection = DriverManager.getConnection(URL_CONNECTION);
            } catch (ClassNotFoundException classNotFoundException) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, classNotFoundException);
            } catch (SQLException sqlException) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }
    
    public void close(){
        if(connection != null){
            try {
                this.connection.close();
            } catch (SQLException sqlException) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
}
