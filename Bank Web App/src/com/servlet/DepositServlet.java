package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
        {
            Connection con;
            


            PrintWriter  out = response.getWriter();
            response.setContentType("text/html");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://@localhost/accdb", "root", "Gayu@123");
            ServletContext context = getServletContext();
            int id=Integer.parseInt(request.getParameter("id"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            System.out.println("amount="+amount);
            Statement getCurrentBalance=con.createStatement();
            ResultSet rs2=getCurrentBalance.executeQuery("SELECT Balance from employee where id="+id+";");
            int Balance=-1;
            while(rs2.next())
            {
            	Balance=rs2.getInt(1);
            	System.out.println("Balance is"+Balance);
            }
            // updating balance with the amount given
            int newBalance = Balance+amount;

			/*
			ref : "UPDATE Registration " +
            	"SET age = 30 WHERE id in (100, 101)";
			*/
			final String sqlUpdateQuery = "UPDATE employee "+
										"SET amount=" + amount +
										", Balance=" + newBalance +
										" WHERE id=" + id + ";";
            Statement updateQeuryStatement = con.createStatement();
			int rowsAffected = updateQeuryStatement.executeUpdate(sqlUpdateQuery);

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
