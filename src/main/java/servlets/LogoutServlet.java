package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		HttpSession session = request.getSession();
		session.invalidate();
		out.print(
	"<style>"
+"	margin: 0;"
+"	list-style-type: none;"
+"    ul {"
+"      padding: 0;"
+"      overflow: hidden;"
+"      background-color: #333333;"
+"    }"
+"    "
+"}"
+"	float: left;"
+"    li {"
+"    "
+"    li a {"
+"      display: block;"
+"      color: white;"
+"      text-align: center;"
+"      padding: 16px;"
+"      text-decoration: none;"
+"    }"
+"    "
+"    li a:hover {"
+"      background-color: #111111;"
+"    }"
+"    </style>"
+"  <head>"
+"    <title>Hotel Company</title>"
+"  </head>"
+"  <nav>"
+"    <ul>"
+"      <li><a href=\"Homepage.html\">Home</a></li>"
+"      <li><a href=\"#\">Rooms</a></li>"
+"      <li><a href=\"#\">Services</a></li>"
+"      <li><a href=\"#\">Contact Us</a></li>"
+"      <li><a href=\"Search.html\">Search</a></li>"
+"      <li><a href=\"login.html\">Login</a></li>"
+"      <li><a href=\"LogoutServlet\">Logout</a></li>"
+"    </ul>"
+"  </nav>"
		);
		out.print("You are successfully logged out!");
		out.close();
	}

}
