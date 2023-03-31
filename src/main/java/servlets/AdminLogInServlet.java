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
public class AdminLogInServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();



		if(String.valueOf(session.getAttribute("role")) != "admin"){
			if (password.equals("admin")) {
				out.print("Welcome, " + userid);
				out.print("<li><a href=\"admin_panel.html\">Access database</a></li>");
				session.setAttribute("role", "admin");
				request.getRequestDispatcher("admin_panel.html").include(request, response);

			}
			else {
				out.print("Sorry, your not a employee!");
				request.getRequestDispatcher("admin_login.html").include(request,
						response);
			}
		}
		else{
			out.print("You are already logged in!");
			out.print("<li><a href=\"admin_panel.html\">Access reservation</a></li>");
		}

		out.close();
	}

}
