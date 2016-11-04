/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import db.ConnectionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Item;
import main.User;

/**
 *
 * @author guitp
 */
@WebServlet(name = "Order", urlPatterns = {"/order"})
public class Order extends HttpServlet {

    private static String ADD_ORDER = "INSERT INTO orders (user_id, product_id, quantity) VALUES(?,?,?)";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String realPath = getServletContext().getRealPath( "WEB-INF/sqlite.db" );
        User user = (User)request.getSession().getAttribute("user");
        ConnectionDB connection = new ConnectionDB(realPath);
        try{
            PreparedStatement p = connection.getConnection().prepareStatement("SELECT orders.id, user_id, product_id, quantity, name, description, imgFileName FROM orders INNER JOIN product on product.id = orders.product_id WHERE user_id = ? GROUP BY product_id");
            p.setInt(1, user.getId());
            ResultSet r = p.executeQuery();
            Vector<main.Order> orders = new Vector<main.Order>();
            System.out.println(orders.size());

            while(r.next()){
                main.Order order = new main.Order(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4));
                order.setItem( new Item(r.getInt(3), r.getString(5), r.getString(6), r.getString(7)));
                orders.add( order );
                
            }
            request.setAttribute("orders", orders);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        connection.close();
        request.getRequestDispatcher("/Orders.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String realPath = getServletContext().getRealPath( "WEB-INF/sqlite.db" );
        User user = (User)request.getSession().getAttribute("user");
        ConnectionDB connection = new ConnectionDB(realPath);
        
        Vector<main.Order> orders = new Vector<main.Order>();
        String[] idsString = request.getParameterValues("ids[]");
        String[] qttyString = request.getParameterValues("qttys[]");
        int[] ids = new int[idsString.length];
        int[] qttys = new int[idsString.length];
        
        for(int i = 0; i < idsString.length; i++){
            ids[i] = Integer.parseInt(idsString[i]);
            qttys[i] = Integer.parseInt(qttyString[i]);
            System.out.println(user.getId() + " --- " + ids[i] + " q:" + qttys[i]);
        }
        
        
        
        try {
            connection.getConnection().setAutoCommit(false);
            PreparedStatement prep = connection.getConnection().prepareStatement(ADD_ORDER);
            
            for(int i = 0; i < ids.length; i++){
                prep.setInt(1, user.getId());
                prep.setInt(2, ids[i]);
                prep.setInt(3, qttys[i]);
                prep.execute();
            }
            
            connection.getConnection().commit();
            request.getSession().setAttribute("cart", new CartObject());

        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
    }


}
