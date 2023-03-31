package servlets;


import java.sql.*;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Search.html").include(request, response);
		String hotelName = request.getParameter("hotel");
		String hotelNumber = request.getParameter("number");
		String areaList = request.getParameter("arealist"); // This odes not seem to work
		String numberRooms = request.getParameter("numberRooms");
		String maxPrice = request.getParameter("maxPrice");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		// out.print("the name of the hotel is, " + hotelName);
		// out.print("\nthe number of the hotel is, " + hotelNumber);

		// get user inormation from session
		HttpSession session = request.getSession();
		String user = String.valueOf(session.getAttribute("name"));

		if (user == "null") {
			out.print("Sorry, you are not logged in!");
			// request.getRequestDispatcher("login.html").include(request, response);
		}

		else {
			System.out.println("Hotel Name: " + hotelName);
			System.out.println("Hotel Number: " + hotelNumber);
			System.out.println("Area List: " + areaList);
			System.out.println("Number of Rooms: " + numberRooms);
			System.out.println("Max Price: " + maxPrice);
			System.out.println("Start Date: " + startDate);
			System.out.println("End Date: " + endDate);
			String username = user.split("_")[0];
			String password = user.split("_")[1];

			String[] Column = { "room_num", "room_type", "room_price", "room_capacity", "room_status",
					"room_annimities", "hotel_name", "agreement_num" };
			String[] where = { "name = '" + hotelName + "'", "number_hotels > " + hotelNumber };

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";
			Application app = new Application(url, username, password);
			List<String> result = app.selectFromTable("Hotels", Column, "Hotels", where);

			String begin = "<br>"
					+ "<hr>"
					+ "<head>"
					+ "	<title>Search Results</title>"
					+ "	<style>"
					+ "		table, th, td {"
					+ "			border: 1px solid black;"
					+ "			border-collapse: collapse;"
					+ "			padding: 5px;"
					+ "		}"
					+ "		th {"
					+ "			background-color: #ddd;"
					+ "		}"
					+ "	</style>"
					+ "</head>"
					+ "<body>"
					+ "	<h1>Search Results</h1>"
					+ "	<table>"
					+ "		<thead>"
					+ "			<tr>"
					+ "				<th>Hotel Name</th>"
					+ "				<th>Number of hotels</th>"
					+ "				<th>Adress</th>"
					+ "				<th>Email</th>"
					+ "				<th>Phone</th>"
					+ "			</tr>"
					+ "		</thead>"
					+ "		<tbody>";

			String middle = "";
			for (int i = 0; i < result.size(); i++) {
				String[] Categori = result.get(i).split(",");
				middle += "<tr>"
						+ "<td>" + Categori[0] + "</td>"
						+ "<td>" + Categori[1] + "</td>"
						+ "<td>" + Categori[2] + "</td>"
						+ "<td>" + Categori[3] + "</td>"
						+ "<td>" + Categori[4] + "</td>"
						+ "<td>" + Categori[5] + "</td>"
						+ "<td>" + Categori[6] + "</td>"
						+ "<td>" + Categori[7] + "</td>"
						+ "</tr>";
			}
			String end = "<table border=\"1\">" + "<thead>" + "<tr>" + "<th>" + hotelName + "</th>" + "<th>"
					+ numberRooms + "</th>"
					+ "<th>Price</th>" + "<th>Capacity</th>" + "<th>Status</th>" + "<th>Amenities</th>"
					+ "<th>Hotel Name</th>" + "<th>Agreement Number</th>" + "</tr>" + "</thead>" + "<tbody>";
			out.print(begin + middle + end);

			out.close();
		}
	}
}
