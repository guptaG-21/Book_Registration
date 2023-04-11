/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editScreen")
public class editScreenServlet extends HttpServlet {
    private static final String query = "select BOOKNAME,BOOKADDITION,BOOKPRICE FROM book_data where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
                PreparedStatement ps = con.prepareStatement(query);){
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        pw.println("<form action='editUrl?id="+id+"' method='post'>");
        pw.println("<table align='center'>");
        pw.println("<tr>");
        pw.println("<td>Book Name</td>"); 
        pw.println("<td><input type='text' name = 'bookName' value='"+rs.getString(1)+"'></td>"); 
        pw.println("</tr>");
        
        pw.println("<tr>");
        pw.println("<td>Book Edition</td>"); 
        pw.println("<td><input type='text' name = 'bookEdition' value='"+rs.getString(2)+"'></td>"); 
        pw.println("</tr>");
        
        pw.println("<tr>");
        pw.println("<td>Book Price</td>"); 
        pw.println("<td><input type='text' name = 'bookPrice' value='"+rs.getFloat(3)+"'></td>"); 
        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td><input type='submit' value='Edit'><td>");
        pw.println("<td><input type='reset' value='cancel'><td>");
        pw.println("<tr>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</form>");

       
        }catch(SQLException s){
                s.printStackTrace();
                pw.println("<h1>"+s.getMessage()+"</h1>");
        }catch(Exception e){
            e.printStackTrace();
            pw.println("<h1>"+e.getMessage()+"</h1>");
        }
        pw.println("<a href='Home.html' align='center'>Home</a>");
        
    }
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         doGet(req,res);
     }
}
