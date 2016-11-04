/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guitp
 */
@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class Admin extends HttpServlet {
    
    private Vector<Item> items;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || !user.IsAdmin()){
            response.sendRedirect("index.jsp");
            return;
        }
        ServletContext ctx = getServletContext();
        if(ctx.getAttribute("items") == null){
            initItems();
        }
        ctx.setAttribute("items", this.items);
        request.getRequestDispatcher("admin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        int selId = Integer.parseInt(request.getParameter("id"));
        Item feat = null;
        for(Item i : items){
            if(i.getId() == selId){
                feat = i;
                break;
            }
        }
        ctx.setAttribute("featuredItem", feat);
        
    }
    
    private void initItems()throws IOException{
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/items.txt");
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        this.items = new Vector<Item>();
        try{
            String item = bf.readLine();
            do{
                StringTokenizer st = new StringTokenizer(item, ";");
                this.items.add(new Item(Integer.parseInt(st.nextToken()), 
                        st.nextToken(), st.nextToken(), st.nextToken()));
                
                item = bf.readLine();
            }while(item != null);
        }finally{
            bf.close();
        }
    }


}
