package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

/**
 * Sample servlet class for search related methods
 */
public class SearchServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Search.html").include(request, response);
		String hotelName = request.getParameter("hotel");
		String hotelNumber = request.getParameter("number");
		//out.print("the name of the hotel is, " + hotelName);
		//out.print("\nthe number of the hotel is, " + hotelNumber);
		
		//get user inormation from session
		HttpSession session = request.getSession();
		String user = String.valueOf(session.getAttribute("name"));
		
		if (user == "null") {
			out.print("Sorry, you are not logged in!");
			//request.getRequestDispatcher("login.html").include(request, response);
		}

		else{
			String username = user.split("_")[0];
			String password = user.split("_")[1];
			

				
			String[] Column = {"name","number_hotels","adress","email","phone"};
			String[] where = {"name = '"+hotelName+"'", "number_hotels > "+hotelNumber};

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
			Application app = new Application(url, username, password);
			List<String> result = app.selectFromTable("Hotels", Column, "Hotels", where);
				

			//TODO: Change the table to be dynamic and not hardcoded
			String begin = 
		"<br>"
		+"<hr>"
		+"<head>"
		+"	<title>Search Results</title>"
		+"	<style>"
		+"		table, th, td {"
		+"			border: 1px solid black;"
		+"			border-collapse: collapse;"
		+"			padding: 5px;"
		+"		}"
		+"		th {"
		+"			background-color: #ddd;"
		+"		}"
		+"	</style>"
		+"</head>"
		+"<body>"
		+"	<h1>Search Results</h1>"
		+"	<table>"
		+"		<thead>"
		+"			<tr>"
		+"				<th>Hotel Name</th>"
		+"				<th>Number of hotels</th>"
		+"				<th>Adress</th>"
		+"				<th>Email</th>"
		+"				<th>Phone</th>"
		+"			</tr>"
		+"		</thead>"
		+"		<tbody>";

		String middle = "";
			for (int i = 0; i < result.size(); i++) {
				String[] Categori = result.get(i).split(",");
				middle += "<"
				+"<tr>"
					+"<td>" + Categori[0] + "</td>"
					+"<td>" + Categori[1] + "</td>"
					+"<td>" + Categori[2] + "</td>"
					+"<td>" + Categori[3] + "</td>"
					+"<td>" + Categori[4] + "</td>"
				+"</tr>"
				
				
				;
			}
		String end =
		"		</tbody>"
		+"	</table>"
		+"</body>;";
			out.print(begin + middle + end);

			out.close();
		}
	}
}
