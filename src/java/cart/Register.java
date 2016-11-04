/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import db.ConnectionDB;
import db.LoginDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        String responseJSONObject = "{\"state\": \"error\", \"message\": \"failed to register user\"}";;
        String realPath = getServletContext().getRealPath("WEB-INF/sqlite.db");
        
        User newUser = new User(username, password);
        
        connectionDB = new ConnectionDB(realPath);
        try {
            connectionDB.getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginDAO = new LoginDAO(connectionDB.getConnection());
        
        LoginDAO.MESSAGE_CODE register_state = loginDAO.registerUser( newUser );
        try {
            connectionDB.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectionDB.close();
        
        
        switch(register_state){
            case USER_REGISTERED:
                responseJSONObject = "{\"state\": \"success\", \"message\": \"user successfully registered\"}";
                break;
            
        }
        request.getRequestDispatcher("login").include(request, response);
        response.getWriter().println(responseJSONObject);
        

    }


}
