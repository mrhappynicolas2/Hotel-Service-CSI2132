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
public class ReservationAcceptServlet extends HttpServlet {
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
			String value[] = {"room_status = 'used'"};
            String where[] = {"agreement_num = '"+agreementNum+"'"};
            app.updateRow("rooms", "Hotels", value, where);
			out.print("Reservation Accepted");
    }
}