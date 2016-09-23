/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Item;

/**
 *
 * @author guitp
 */
@WebServlet(name = "cart/add", urlPatterns = {"/cart/add"})
public class Add extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.addToCart(request, response);

    }
    
    private void addToCart(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", new CartObject());
        }
        
        CartObject cart = (CartObject)session.getAttribute("cart");
        Item i = new Item(Integer.parseInt(request.getParameter("id")),
            request.getParameter("name"),
            request.getParameter("desc"),
            request.getParameter("img")
        );
        cart.addItem(i);
        
    }


}
