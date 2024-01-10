package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetchdata")
public class fetchdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empid=Integer.parseInt(request.getParameter("empid"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter ();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabatch","root","1234");
			System.out.println("Connection Success");
			
			PreparedStatement ps=con.prepareStatement("Select empid, empname, sal From employee Where empid=?");
			ps.setInt(1,empid);
			ResultSet rs=ps.executeQuery();
			
			out.println("<html><body>");
			out.println("<h2>Employee Details</h2>");
			out.println("<table>");
			out.println("<tr><th>empid</th><th>empname</th><th>sal</th></tr>");
			while(rs.next()) {
				int id=rs.getInt("empid");
				String name=rs.getString("empname");
				double sal=rs.getDouble("sal");
				System.out.println(id+"=="+name+"=="+sal);
				out.println("<tr><th>"+id+"</th><th>"+name+"</th><th>"+sal+"</th></tr>");
			}
			out.println("</table>");
			out.println("</body></html>");
			
		}catch(Exception e){
			System.out.println(e);
		}
	
	}

}
