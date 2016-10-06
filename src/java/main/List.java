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
import java.util.StringTokenizer;
import java.util.Vector;
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

    Vector<Item> items;
    String filename = "/WEB-INF/items.txt";
    ServletContext context;
    InputStream inputStream;
    
    @Override
    public void init(){
        context = getServletContext();
        inputStream = context.getResourceAsStream(filename);
        this.items = new Vector<Item>();
        try{
            initItems();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
    
    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        request.setAttribute("items", this.items);
    }
    
    private void initItems()throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
