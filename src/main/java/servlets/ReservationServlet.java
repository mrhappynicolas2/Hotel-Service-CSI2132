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
public class ReservationServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Reservation.html").include(request, response);
	
        HttpSession session = request.getSession();
        String user = String.valueOf(session.getAttribute("name"));
		
        String agreementNum = request.getParameter("reservationID");

		    String username = user.split("_")[0];
			String password = user.split("_")[1];
		
			String[] Column = {"agreement_num","startdate","enddate", "status"};
			String[] where = {"agreement_num = '"+agreementNum+"'"};

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
			Application app = new Application(url, username, password);
			List<String> result = app.selectFromTable("agreement", Column, "Hotels", where);
			
			//room status
			String[] tablenameForeign = {"agreement", "rooms"};
			String[] selectForeign = {"\"Hotels\".rooms.room_status","\"Hotels\".agreement.agreement_num", "\"Hotels\".rooms.hotel_name", "\"Hotels\".rooms.room_num", "\"Hotels\".rooms.room_type", "\"Hotels\".rooms.room_price", "\"Hotels\".rooms.room_capacity", "\"Hotels\".agreement.ssn"};
			String valueForeign[] = {"agreement_num = '"+agreementNum+"'"};
            String whereForeign[] = {"\"Hotels\".rooms.room_num = \"Hotels\".agreement.room", "\"Hotels\".rooms.hotel_name = \"Hotels\".agreement.hotel"};
            
			List<String> allBoockings = app.foreignSelect(tablenameForeign, selectForeign,"Hotels", whereForeign);
			List<String> Foundboocking = new ArrayList<String>();
			String query = "\"Hotels\".agreement.agreement_num: " + agreementNum;
			for (String s: allBoockings ) {
				if (s.contains("\"Hotels\".agreement.agreement_num: " + agreementNum)){
					
				 System.out.println(s);
				 Foundboocking.add(s);}
			}

			String[] Categorie = Foundboocking.get(0).split(",");
				String[] fixedReservationInfo = new String[10];
				for (int j = 0; j < Categorie.length-1; j++) {
					String[] tempInfo = Categorie[j].split(":");
					fixedReservationInfo[j] = tempInfo[1];
            }
			//end
			session.setAttribute("num", agreementNum);
			
			String test = (String)request.getAttribute("num");
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
		+"				<th>Start Date</th>"
		+"				<th>End Date</th>"
		+"				<th>Room Status</th>"
		+"				<th>Accept Reservation</th>"
        +"				<th>Deny Reservation</th>"
		+"				<th>View Reservation</th>"
		+"				<th>Left Room</th>"
		+"			</tr>"
		+"		</thead>"
		+"		<tbody>";

		String middle = "";
			for (int i = 0; i < result.size(); i++) {
				String[] Categori = result.get(i).split(",");
				middle += ""
				+"<tr>"
					+"<td>" + Categori[0] + "</td>"
					+"<td>" + Categori[1] + "</td>"
					+"<td>" + Categori[2] + "</td>"
					+"<td>" + fixedReservationInfo[0] + "</td>"
                    +"<td>" //TODO: Add all these servlet (maybe change the way they work, ex: instead of a button to view, it will automaticly give more info)
                        +"<form action=\"ReservationAcceptServlet\" method=\"post\">"
                        +   "<input type=\"submit\" value=\"Accept Reservation\">"
                        +"</form>"
                    +"</td>"
                    +"<td>"
                        +"<form action=\"ReservationDenyServlet\" method=\"post\">"
                        +   "<input type=\"submit\" value=\"Deny Reservation\">"
                        +"</form>"
                    +"</td>"
                    +"<td>"
                        +"<form action=\"ReservationViewServlet\" method=\"post\">"
                        +   "<input type=\"submit\" value=\"View Reservation\">"
                        +"</form>"
                	+"</td>"
					+"<td>"
						+"<form action=\"ReservationLeftServlet\" method=\"post\">"
						+	"<input type=\"submit\" value=\"Left Room\">"
						+"</form>"
					+"</td>"
				+"</tr>"
		+"		</tbody>"
		+"	</table>"
		+"</body>";
			out.print(begin + middle);

			out.close();

			
		}
		
        
        
		
	}
}
