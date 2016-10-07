/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import db.ConnectionDB;
import db.LoginDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private LoginDAO loginDAO;
    private ConnectionDB connectionDB;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        
        connectionDB = new ConnectionDB();
        loginDAO = new LoginDAO(connectionDB.getConnection());
        
        boolean validUser = loginDAO.isUserValid(username, password);
        request.getSession().setAttribute("validUser", validUser);
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}