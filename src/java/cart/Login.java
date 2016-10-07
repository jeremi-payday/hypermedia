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
import main.User;

/**
 *
 * @author Alex
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
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
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        
        String realPath = getServletContext().getRealPath("WEB-INF/sqlite.db");
        
        connectionDB = new ConnectionDB(realPath);
        loginDAO = new LoginDAO(connectionDB.getConnection());
        
        if(loginDAO.isUserValid(username, password)){
            User userValid = loginDAO.getUser(username);
            request.getSession().setAttribute("userID", userValid.getId());
            request.getSession().setAttribute("username", userValid.getUsername());
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
