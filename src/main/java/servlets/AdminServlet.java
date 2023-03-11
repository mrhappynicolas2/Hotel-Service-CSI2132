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
public class AdminServlet extends HttpServlet {
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
			out.print("<li><a href=\"Reservation.html\">Access reservation</a></li>");
			session.setAttribute("role", "admin");
            request.getRequestDispatcher("Reservation.html").include(request, response);
		
			} 
        else {
			out.print("Sorry, your not a employee!");
			request.getRequestDispatcher("login.html").include(request,
					response);
		}
    }
    else{
        out.print("You are already logged in!");
        out.print("<li><a href=\"Reservation.html\">Access reservation</a></li>");
    }

		
		out.close();
	}

}
