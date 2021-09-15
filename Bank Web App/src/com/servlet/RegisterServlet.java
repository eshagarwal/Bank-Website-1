package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jdbc.UserRegistration;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	 
	public RegisterServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String EName= request.getParameter("EName");
		String pass= request.getParameter("pass");
		String Email= request.getParameter("Email");
		
		
		
    	UserRegistration rDao= new UserRegistration();
		boolean result = rDao.insert(EName,pass,Email);
		//response.getWriter().print(result);
		response.getWriter().print(result ? "Data entered!!" : "Failure.");
		
	}

}
