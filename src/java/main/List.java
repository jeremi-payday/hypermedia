/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.ConnectionDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import misc.FlashSession;

/**
 *
 * @author jeremi
 */
@WebServlet(name = "List", urlPatterns = {"/list"})
public class List extends HttpServlet {
    
    private static String SELECT_PRODUCTS = "SELECT id, name, description, imgfilename FROM product";
    Vector<Item> items;
    String filename = "/WEB-INF/items.txt";
    ServletContext context;
    
    @Override
    public void init(){
        context = getServletContext();
        this.items = new Vector<Item>();
        if(context.getAttribute("featuredItem") == null){
            initItems();
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect("index.jsp");
            return;
        }
        String username = user.getUsername();
        if(username != null){
            FlashSession fs;
            listItems(request, response);

            if(request.getSession().getAttribute("flashSession") == null){
                fs = new FlashSession();
                request.getSession().setAttribute("flashSession", fs);
            }else{
                fs = (FlashSession)request.getSession().getAttribute("flashSession");
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        request.setAttribute("items", this.items);
    }
    
    private void initItems(){
        String realPath = getServletContext().getRealPath( "WEB-INF/sqlite.db" );
        ConnectionDB connection = new ConnectionDB(realPath);
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECT_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            String imgDirectory = "img/items/";
            while(resultSet.next()){
                this.items.add(new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),imgDirectory +resultSet.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
