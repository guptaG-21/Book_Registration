
package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUrl")
public class deleteServlet extends HttpServlet {
     private static final String query = "delete from book_data where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
//    
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
                PreparedStatement ps = con.prepareStatement(query);){
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if(count==1){
                pw.println("<h2> Record is Deleted successfully</h2>");
            }else{
                pw.println("<h2> failed to Delete</h2>");
            }
        }catch(SQLException s){
                s.printStackTrace();
                pw.println("<h1>"+s.getMessage()+"</h1>");
        }catch(Exception e){
            e.printStackTrace();
            pw.println("<h1>"+e.getMessage()+"</h1>");
        }
        pw.println("<a href='Home.html' align='center'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='booklist'>Book List</a>");
        
    }
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         doGet(req,res);
     }
    
}
