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

/**
 *
 * @author guitp
 */
@WebServlet(name = "cart/misc", urlPatterns = {"/cart/misc"})
public class Misc extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(request.getParameter("actionType") != null){
            if("toggleTab".equals(request.getParameter("actionType")) ){
                
                if("true".equals(request.getParameter("actionVal")) ){
                    session.setAttribute("cartTabOut", true);
                    
                } else{
                    session.setAttribute("cartTabOut", false);
                }
                
            }
            
        }
    }



}
