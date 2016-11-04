/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Alex
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private int admin;
    
    public User(String username, String password) {
        this.password = password;
        this.username = username;
        this.admin = 0;
    }
    public User(int id, String username, int isAdmin) {
        this.id = id;
        this.username = username;
        this.admin = isAdmin;
    }
    public User(int id, String username, String password, int isAdmin) {
        this(id, username, isAdmin);
        this.password = password;
        this.admin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    public boolean IsAdmin(){
        return this.admin > 0;
    }
}
