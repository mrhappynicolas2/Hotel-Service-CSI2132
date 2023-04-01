package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("admin_panel.html").include(request,response);

        HttpSession session = request.getSession();

        String user = String.valueOf(session.getAttribute("name"));

        String username = user.split("_")[0];
        String password = user.split("_")[1];

        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";
        Application app = new Application(url, username, password);

        if(request.getParameter("customerSSN") != ""){
            String ssnToDelete = "ssnc = "+request.getParameter("customerSSN");
            app.deleteFromTable("Hotels.customer",ssnToDelete);
        }
        else if(request.getParameter("employeeSSN") != "") {
            String ssnToDelete = "ssne = "+request.getParameter("employeeSSN");
            app.deleteFromTable("Hotels.customer",ssnToDelete);
        }
        else if(request.getParameter("hotelName") != "" && request.getParameter("roomNum") == ""){
            String hotelToRemove = "name = "+request.getParameter("hotelName");
            app.deleteFromTable("Hotels.hotels",hotelToRemove);
        }
        else if(request.getParameter("hotelName") != "" && request.getParameter("roomNum") != ""){
            String roomToRemove = "room_num = "+request.getParameter("roomNum");
            app.deleteFromTable("Hotels.rooms",roomToRemove);
        }

        out.print("<!DOCTYPE html>" +
                "<html>"
                + "<head>"
                + "	<title>Search Results</title>"
                + "	<style>"
                + "  *{" +
                "     font-family: 'Epilogue', sans-serif;" +
                "     }"
                + "	</style>"
                + "</head>"
                + "<body>"
                +"<h1>Deletion Successful!</h1>"
                +"</body>"
                +"</html>");

    }
}
