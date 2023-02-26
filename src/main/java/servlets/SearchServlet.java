package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

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
		out.print("the name of the hotel is, " + hotelName);
		out.print("\nthe number of the hotel is, " + hotelNumber);
			
		String[] Column = {"name","number_hotels","adress","email","phone"};
		String[] where = {"name = '"+hotelName+"'", "number_hotels > "+hotelNumber};

		String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
        String username = "postgres";
        String password = "password"; //FIXME: this will not work unless you change it to your custom password
        Application app = new Application(url, username, password);
		List<String> result = app.selectFromTable("Hotels", Column, "Hotels", where);
			

		//TODO: Change the table to be dynamic and not hardcoded
		String begin = 
	"<head>"
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
	+"				<th>Item Name</th>"
	+"				<th>Description</th>"
	+"				<th>Price</th>"
	+"			</tr>"
	+"		</thead>"
	+"		<tbody>";

	String middle = "";
		for (int i = 0; i < result.size(); i++) {
			middle += "<"
			+"<tr>"
				+"<td>" + result.get(i) + "</td>"
				+"<td>" + result.get(i) + "</td>"
				+"<td>" + result.get(i) + "</td>"
			+"</tr>"
			
			
			;
		}
	String end =
	"			<tr>"
	+"				<td>Item 1</td>"
	+"				<td>Description of Item 1</td>"
	+"				<td>$9.99</td>"
	+"			</tr>"
	+"			<tr>"
	+"				<td>Item 2</td>"
	+"				<td>Description of Item 2</td>"
	+"				<td>$19.99</td>"
	+"			</tr>"
	+"			<tr>"
	+"				<td>Item 3</td>"
	+"				<td>Description of Item 3</td>"
	+"				<td>$29.99</td>"
	+"			</tr>"
	+"		</tbody>"
	+"	</table>"
	+"</body>;";
		out.print(begin + middle + end);

		out.close();
	}
}
