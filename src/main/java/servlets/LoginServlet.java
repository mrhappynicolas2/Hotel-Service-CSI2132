package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Sample servlet class for login related methods
 */
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		


		
		if (!password.equals("admin")) {
			out.print("Welcome, " + userid);
			out.print("<li><a href=\"index.html\">Home</a></li>");
			HttpSession session = request.getSession();
			session.setAttribute("name", userid+"_"+password);
			

			String[] args = { "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public", userid, password};
			Application.main(args);
		
			 } else {
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("login.html").include(request,
					response);
		}

		
		out.close();
	}

}
