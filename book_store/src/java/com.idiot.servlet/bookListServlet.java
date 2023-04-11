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

@WebServlet("/booklist")
public class bookListServlet extends HttpServlet {
    private static final String query = "select ID,BOOKNAME,BOOKADDITION,BOOKPRICE FROM book_data";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
                PreparedStatement ps = con.prepareStatement(query);){
        ResultSet rs = ps.executeQuery();
        pw.println("<table border='1' align='center'>");
        pw.println("<tr>");
        pw.println("<th>Book Id</th>");
        pw.println("<th>Book Name</th>");
        pw.println("<th>Book Edition</th>");
        pw.println("<th>Price</th>");
        pw.println("<th>Edit</th>");
        pw.println("<th>Delete</th>");
        pw.println("</tr>");
        while(rs.next()){
           pw.println("<tr>");
           pw.println("<td>"+rs.getInt(1)+"</td>");
           pw.println("<td>"+rs.getString(2)+"</td>");
           pw.println("<td>"+rs.getString(3)+"</td>");
           pw.println("<td>"+rs.getFloat(4)+"</td>");
           pw.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'>Edit</a> </td>");
           pw.println("<td> <a href='deleteUrl?id="+rs.getInt(1)+"'>Delete</a> </td>");
           pw.println("<tr>");
        }
        pw.println("</table>");
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
