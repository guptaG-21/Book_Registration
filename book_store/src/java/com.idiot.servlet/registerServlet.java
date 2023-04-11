/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idiot.servlet;
import java.sql.Connection;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    private static final String query = "INSERT INTO book_data(BOOKNAME,BOOKADDITION,BOOKPRICE) VALUES(?,?,?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
//        String id = req.getParameter("id");
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
                PreparedStatement ps = con.prepareStatement(query);){
//            ps.setString(1, id);
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            int count = ps.executeUpdate();
            if(count==1){
                pw.println("<h2> Registered successfully! </h2>");
            }else{
                pw.println("<h2> Recored not registered successfully </h2>");
            }
        }catch(SQLException s){
                s.printStackTrace();
                pw.println("<h1>"+s.getMessage()+"</h1>");
        }catch(Exception e){
            e.printStackTrace();
            pw.println("<h1>"+e.getMessage()+"</h1>");
        }
        pw.println("<a href='Home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='booklist'>Book List</a>");
    }
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         doGet(req,res);
     }
}
    
