
package com.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UserDAO;
import com.test.dao.impl.UserDAOImpl;
import com.test.model.User;
import com.test.model.Users;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao = new UserDAOImpl();
	{
		
	}
    /**
     * Default constructor. 
     */
    public UserServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathToXML = getServletContext().getRealPath("/");
		((UserDAOImpl)userDao).pathToFile = pathToXML;
		
		Users users = userDao.getAllUsers();
		request.setAttribute("us","us");
		request.setAttribute("usersList", users.getUsers());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathToXML = getServletContext().getRealPath("/");
		((UserDAOImpl)userDao).pathToFile = pathToXML;
		
		User user = extractUserFromRequest(request);
		userDao.saveUser(user);
		response.sendRedirect(request.getContextPath());
	}
	
	private User extractUserFromRequest(HttpServletRequest request){
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		User user = new User();
		
		user.setAge(Integer.valueOf(age));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setPassword(pass);
		
		return user;
	}

}
