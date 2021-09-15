package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login1Servlet
 */
@WebServlet("/Login1Servlet")
public class Login1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		//accepting username and password from loginForm.html
		String EName=request.getParameter("EName"); 
		String Pass=request.getParameter("Pass");
		
		//database
		try {
			  System.out.println("inside try one");
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con;
				try
				{
					System.out.println("inside try second");
					con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/accdb","root","Gayu@123");
					Statement stmt= con.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT username,password FROM employee;");
					boolean flag=false;
					while(rs.next())
					{
						String usertmp=rs.getString(1);
						String passtmp=rs.getString(2);
						
						if(usertmp.equals(EName)&&passtmp.equals(Pass))
						{
							System.out.println("Data :"+usertmp);
							System.out.println("Data :"+passtmp);
							flag=true;
					     }
					}
					if(flag)
					{
						System.out.println("inside if");
						//if username and password true then go to transaction.html 
						response.sendRedirect("Transaction.html");
					}
					else //if wrong username and password 
					{
						System.out.println("inside else");
						out.println("Wrong username and password");
					}
					con.close();
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
