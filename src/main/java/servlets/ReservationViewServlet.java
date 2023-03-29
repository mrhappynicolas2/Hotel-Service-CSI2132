package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ReservationViewServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Reservation.html").include(request, response);
	
        HttpSession session = request.getSession();
        
        String agreementNum = (String)session.getAttribute("num");

        String user = String.valueOf(session.getAttribute("name"));
		
        
        
		    String username = user.split("_")[0];
			String password = user.split("_")[1];
            System.out.println("test");
			

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
			Application app = new Application(url, username, password);
			
            

            String[] tablename = {"agreement", "rooms"};
			String[] select = {"\"Hotels\".agreement.agreement_num", "\"Hotels\".rooms.hotel_name", "\"Hotels\".rooms.room_num", "\"Hotels\".rooms.room_type", "\"Hotels\".rooms.room_price", "\"Hotels\".rooms.room_capacity", "\"Hotels\".agreement.ssn"};
			String value[] = {"room_status = 'free'"};
            String where[] = {"\"Hotels\".rooms.room_num = \"Hotels\".agreement.room", "\"Hotels\".rooms.hotel_name = \"Hotels\".agreement.hotel"};
            
			List<String> allBoockings = app.foreignSelect(tablename, select,"Hotels", where);
			List<String> Foundboocking = new ArrayList<String>();
			String query = "\"Hotels\".agreement.agreement_num: " + agreementNum;
			for (String s: allBoockings ) {
				if (s.startsWith(query))
				 System.out.println(s);
				 Foundboocking.add(s);
			}
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
		+"				<th>Agreement Number</th>"
		+"				<th>Name of the hotel</th>"
		+"				<th>Room number</th>"
		+"				<th>Room type</th>"
        +"				<th>Room price</th>"
		+"				<th>Room capacity</th>"
		+"			</tr>"
		+"		</thead>"
		+"		<tbody>";

		String middle = "";
			for (int i = 0; i < Foundboocking.size(); i++) {
				String[] Categori = Foundboocking.get(i).split(",");
				middle += ""
				+"<tr>"
					+"<td>" + Categori[0] + "</td>"
					+"<td>" + Categori[1] + "</td>"
					+"<td>" + Categori[2] + "</td>"
					+"<td>" + Categori[3] + "</td>"
					+"<td>" + Categori[4] + "</td>"
					+"<td>" + Categori[5] + "</td>"  
				+"</tr>"
		+"		</tbody>"
		+"	</table>"
		+"</body>";
			out.print(begin + middle);

			out.close();

	

			}
		}
}