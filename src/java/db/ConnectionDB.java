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
    
    private static final String URL_CONNECTION = "jdbc:sqlite:";
    
    private Connection connection;
    private String realPath;
    
    public ConnectionDB(String realPath){
        this.realPath = realPath;
        init();
    }
    
    private void init(){
        connect();
    }
    
    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(URL_CONNECTION + this.realPath);
        } catch (ClassNotFoundException classNotFoundException) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, classNotFoundException);
        } catch (SQLException sqlException) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, sqlException);
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
