/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import db.ConnectionDB;
import db.LoginDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.User;

/**
 *
 * @author guitp
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private LoginDAO loginDAO;
    private ConnectionDB connectionDB;
    
    private boolean isPasswordValid(String field){
        return field.length() > 8 && field.length() < 16;
    }
    
    private boolean isUsernameValid(String field){
        return field.length() > 3 && field.length() < 16;
    }

    private boolean isEmpty(String field){
        return field.isEmpty();
    }
    
    private boolean isIntegerFieldValid(String field){
        try{
            int temp = Integer.parseInt(field);
        }catch(NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }
    
    private List<LoginDAO.MESSAGE_CODE> validateFields(String username, 
                                                        String password, 
                                                        String firstname,
                                                        String lastname,
                                                        String address,
                                                        String ageStr){
        List<LoginDAO.MESSAGE_CODE> messageCodeList = new ArrayList<LoginDAO.MESSAGE_CODE>();
        
        if(isEmpty(username) || !isUsernameValid(username)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_USERNAME);
        }
        if(isEmpty(password) || !isPasswordValid(password)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_PASSWORD);
        }
        if(isEmpty(firstname)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_FIRSTNAME);
        }
        if(isEmpty(lastname)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_LASTNAME);
        }
        if(isEmpty(address)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_ADDRESS);
        }
        if(isEmpty(address) || !isIntegerFieldValid(ageStr)){
            messageCodeList.add(LoginDAO.MESSAGE_CODE.INVALID_AGE);
        }
        
        return messageCodeList;
    }
    
    private boolean addUser(String username, 
                            String password, 
                            String firstname,
                            String lastname,
                            String address,
                            String ageStr, 
                            String realPath){
        boolean isRegistered;
        User user = new User(username, password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setAge(Integer.parseInt(ageStr));
        
        connectionDB = new ConnectionDB(realPath);
        loginDAO = new LoginDAO(connectionDB.getConnection());
        isRegistered = loginDAO.registerUser(user);
		try {
            connectionDB.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectionDB.close();
        
        return isRegistered;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<LoginDAO.MESSAGE_CODE> messageCodeList;
        String responseJSONObject;
        String realPath = getServletContext().getRealPath("WEB-INF/sqlite.db");
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        String firstname = (String)request.getParameter("firstname");
        String lastname = (String)request.getParameter("lastname");
        String address = (String)request.getParameter("address");
        String ageStr = (String)request.getParameter("age");
        
        messageCodeList = validateFields(username, password, firstname, lastname, address, ageStr);
        
        if(messageCodeList.isEmpty()){
            if(addUser(username, password, firstname, lastname, address, ageStr, realPath)){
                responseJSONObject = "{\"state\": \"success\", \"message\": \"user successfully registered\"}";
            }else{
                responseJSONObject = "{\"state\": \"error\", \"message\": \"Error with the database. Please contact an administrator.\"}";
            }
        }else{
            String errors = "";
            for(LoginDAO.MESSAGE_CODE messageCode : messageCodeList){
                errors += " " + messageCode.toString();
            }
            responseJSONObject = String.format("{\"state\": \"error\", \"message\": \"Please correct these field errors:%s\"}", errors);
        }
        response.getWriter().println(responseJSONObject);
        request.getRequestDispatcher("login").include(request, response);
    }
}
