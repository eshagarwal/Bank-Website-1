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
 * Servlet implementation class TransferServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		 out.println("<h1> Congratulation !!</h1>");
		 out.println("<br><br><h2>Your transfer was successfull</h2>");
		 
		 String RecipientName = request.getParameter("name");
		 int RecipientAccNo= Integer.parseInt(request.getParameter("AccNo"));
		 int AmtToTransfer= Integer.parseInt(request.getParameter("Amt"));
		 int RecieverAccNo= Integer.parseInt(request.getParameter("RAccNo"));
		 
		 out.println("<br><br>");
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost/accdb", "root", "Gayu@123");
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    
		    Statement getCurrentBalance=con.createStatement();
           ResultSet rs1=getCurrentBalance.executeQuery("SELECT Balance from employee where id="+RecipientAccNo+";");
           int RecipientBal=-1;
           while(rs1.next())
           {
           	RecipientBal=rs1.getInt(1);
           	System.out.println("Balance is"+RecipientBal);
           }
           
           int newRecipientBal = RecipientBal - AmtToTransfer;
           
           Statement getCurrentBalance2=con.createStatement();
           ResultSet rs2=getCurrentBalance2.executeQuery("SELECT Balance from employee where id="+RecieverAccNo+";");
           int RecieverBal=-1;
           while(rs2.next())
           {
           	RecieverBal=rs2.getInt(1);
           	System.out.println("Balance is"+RecieverBal);
           }
           
           int newRecieverBal = RecieverBal + AmtToTransfer;
           
           
           final String sqlUpdateQuery = "UPDATE employee "+
					"SET amount=" + AmtToTransfer+
					", Balance=" + newRecipientBal +
					" WHERE id=" + RecipientAccNo + ";";
			Statement updateQeuryStatement = con.createStatement();
			int rowsAffected = updateQeuryStatement.executeUpdate(sqlUpdateQuery);
			
			if (rowsAffected >= 1)
			out.println("Money has been withdrawn successfully.");
			else
			out.println("Something went wrong");
           
			rowsAffected = -1;
			final String sqlUpdateQuery2 = "UPDATE employee "+
					"SET amount=" + AmtToTransfer+
					", Balance=" + newRecieverBal +
					" WHERE id=" + RecieverAccNo + ";";
			Statement updateQeuryStatement2 = con.createStatement();
			rowsAffected = updateQeuryStatement2.executeUpdate(sqlUpdateQuery2);
			
			if (rowsAffected >= 1)
				out.println("Money has been deposited successfully.");
				else
				out.println("Something went wrong");
			
		}
		 catch (ClassNotFoundException ex)
       {
          ex.printStackTrace();
       }
       catch (SQLException ex)
		{
           ex.printStackTrace();
       }
		  
	}


	}


