package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("admin_panel.html").include(request, response);

        HttpSession session = request.getSession();

        String user = String.valueOf(session.getAttribute("name"));

        String username = user.split("_")[0];
        String password = user.split("_")[1];

        String hotelName = String.valueOf(session.getAttribute("Hotels"));

        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";
        Application app = new Application(url, username, password);


        if (request.getParameter("customerButton") != null && request.getParameter("personSSN") != "") {
            String customerSSN = request.getParameter("personSSN");
            customerMenu(app, out, customerSSN,session);
        } else if (request.getParameter("employeeButton") != null && request.getParameter("personSSN") != "") {
            String employeeSSN = request.getParameter("personSSN");
            employeeMenu(app, out, employeeSSN,session);
        } else if ((request.getParameter("customerButton") != null || request.getParameter("employeeButton") != null) && (request.getParameter("personSSN") == "" || request.getParameter("customerSSN") == null)) {
            out.println("Sorry, but you need a SSN to look a person up!");
            request.getRequestDispatcher("admin_panel.html").include(request, response);
        } else if (request.getParameter("hotelButton") != null && request.getParameter("Hotels") != "") {
            hotelMenu(app, out, hotelName,session);
        } else if (request.getParameter("hotelButton") != null && request.getParameter("Hotels") == "") {
            out.println("Sorry, but you need a hotel name to look a hotel up!");
            request.getRequestDispatcher("admin_panel.html").include(request, response);
        } else if(request.getParameter("roomButton") != null && (request.getParameter("Hotels") != "" && request.getParameter("roomNum") != "") ){
            String roomNum = request.getParameter("roomNum");
            roomMenu(app,out,hotelName,roomNum,session);
        }
        else if(request.getParameter("roomButton") != null && ((request.getParameter("Hotels") == "" && request.getParameter("roomNum") != "") || (request.getParameter("Hotels") != "" && request.getParameter("roomNum") == "") || (request.getParameter("Hotels") == "" && request.getParameter("roomNum") == ""))){
            out.println("Sorry, but you need a hotel name and room number to look a room up!");
        }


    }

    public void customerMenu(Application app, PrintWriter out, String customerSSN, HttpSession session) {
        // List customer information with options to insert, delete or update
        String[] select = {"ssnc", "name", "adress", "username", "password", "registrationdate", "phone"};
        String tablename = "customer";
        String[] where = {"ssnc = " + customerSSN};
        List<String> queriedCustomers = app.selectFromTable(tablename, select, "Hotels", where);
        List<String> correctCustomer = new ArrayList<>();
        String query = "ssnc: " + customerSSN;

        for (String s : queriedCustomers) {
            if (s.startsWith(query)) {
                System.out.println(s);
                correctCustomer.add(s);
            }
        }
        String begin = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<br>" + ""
                + "<hr>"
                + "<head>"
                + "	<title>Search Results</title>"
                + "	<style>"
                + "  *{" +
                "     font-family: 'Epilogue', sans-serif;" +
                "     }"
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
                + "				<th>Customer SSN</th>"
                + "				<th>Customer Name</th>"
                + "				<th>Customer Address</th>"
                + "				<th>Username</th>"
                + "				<th>Password</th>"
                + "				<th>Registration Date</th>"
                + "				<th>Phone Number</th>"
                + "              <th>Delete from DB</th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";


        for (int i = 0; i < correctCustomer.size(); i++) {
            String[] customerInfo = correctCustomer.get(i).split(",");
            String[] fixedCustomerInfo = new String[7];
            for (int j = 0; j < customerInfo.length - 2; j++) {
                String[] tempInfo = customerInfo[j].split(":");
                fixedCustomerInfo[j] = tempInfo[1];
            }

            String middle = ""
                    + "<tr>"
                    + "<td>" + fixedCustomerInfo[0] + "</td>"
                    + "<td>" + fixedCustomerInfo[1] + "</td>"
                    + "<td>" + fixedCustomerInfo[2] + "</td>"
                    + "<td>" + fixedCustomerInfo[3] + "</td>"
                    + "<td>" + fixedCustomerInfo[4] + "</td>"
                    + "<td>" + fixedCustomerInfo[5] + "</td>"
                    + "<td>" + fixedCustomerInfo[6] + "</td>"
                    + "<td>Confirm Delete?</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify SSN\" name=\"modifyCustSSN\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Name\" name=\"modifyCustName\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Address\" name=\"modifyCustAddress\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Username\" name=\"modifyCustUser\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Password\" name=\"modifyCustPass\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Registration\" name=\"modifyCustReg\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminModifyServlet\" method=\"post\">"
                    + "<input type=\"submit\" value=\"Modify Phone Number\" name=\"modifyCustPhone\">"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action=\"AdminDeleteServlet\" method=\"post\">\n" +
                    "<input type=\"submit\" value=\"Delete\">\n" +
                    "</form>" +
                    "</td>" +
                    "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</body>"
                    + "</html>";
            out.print(begin + middle);
            out.close();
            session.setAttribute("customerSSN",fixedCustomerInfo[0]);
            session.setAttribute("customerName",fixedCustomerInfo[1]);
            session.setAttribute("customerAddress",fixedCustomerInfo[2]);
            session.setAttribute("customerUser",fixedCustomerInfo[3]);
            session.setAttribute("customerPass",fixedCustomerInfo[4]);
            session.setAttribute("customerRegDate",fixedCustomerInfo[5]);
            session.setAttribute("customerPhone",fixedCustomerInfo[6]);
        }

    }

    public void employeeMenu(Application app, PrintWriter out, String employeeSSN, HttpSession session) {
        // List employee information with options to insert, delete or update
        String[] select = {"ssne", "name", "adress", "username", "password", "hiredate"};
        String tablename = "employee";
        String[] where = {"ssne = " + employeeSSN};
        List<String> queriedEmployees = app.selectFromTable(tablename, select, "Hotels", where);
        List<String> correctEmployees = new ArrayList<>();
        String query = "ssne: " + employeeSSN;

        for (String s : queriedEmployees) {
            if (s.startsWith(query)) {
                System.out.println(s);
                correctEmployees.add(s);
            }
        }


        String begin = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<br>" + ""
                + "<hr>"
                + "<head>"
                + "	<title>Search Results</title>"
                + "	<style>"
                + "  *{" +
                "     font-family: 'Epilogue', sans-serif;" +
                "     }"
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
                + "				<th>Employee SSN</th>"
                + "				<th>Employee Name</th>"
                + "				<th>Employee Address</th>"
                + "				<th>Username</th>"
                + "				<th>Password</th>"
                + "				<th>Employee Hiredate</th>"
                + "              <th>Delete from DB<th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";

        String middle = "";

        for (int i = 0; i < correctEmployees.size(); i++) {
            String[] employeeInfo = correctEmployees.get(i).split(",");
            String[] fixedEmployeeInfo = new String[6];
            for (int j = 0; j < employeeInfo.length - 1; j++) {
                String[] tempInfo = employeeInfo[j].split(":");
                fixedEmployeeInfo[j] = tempInfo[1];
            }

            middle +=
                    "<tr>"
                            + "<td>" + fixedEmployeeInfo[0] + "</td>"
                            + "<td>" + fixedEmployeeInfo[1] + "</td>"
                            + "<td>" + fixedEmployeeInfo[2] + "</td>"
                            + "<td>" + fixedEmployeeInfo[3] + "</td>"
                            + "<td>" + fixedEmployeeInfo[4] + "</td>"
                            + "<td>" + fixedEmployeeInfo[5] + "</td>"
                            + "<td>Confirm Delete?</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify SSN\" name=\"modifyEmployeeSSN\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Name\" name=\"modifyEmployeeName\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Address\" name=\"modifyEmployeeAddress\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Username\" name=\"modifyEmployeeUser\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Password\" name=\"modifyEmployeePass\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Hire Date\" name=\"modifyEmployeeHireDate\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminDeleteServlet\" method=\"post\">\n" +
                            "<input type=\"submit\" value=\"Delete\">\n" +
                            "</form>" +
                            "</td>" +
                            "</tr>"+
            "		</tbody>"
                    + "	</table>"
                    + "</body>";
            out.print(begin + middle);

            out.close();
            session.setAttribute("employeeSSN",fixedEmployeeInfo[0]);
            session.setAttribute("employeeName",fixedEmployeeInfo[1]);
            session.setAttribute("employeeAddress",fixedEmployeeInfo[2]);
            session.setAttribute("employeeUser",fixedEmployeeInfo[3]);
            session.setAttribute("employeePass",fixedEmployeeInfo[4]);
            session.setAttribute("employeeHireDate",fixedEmployeeInfo[5]);
        }

    }

    public void hotelMenu(Application app, PrintWriter out, String hotelName, HttpSession session) {
        // List hotel and room information with options to insert, delete or update
        String[] selectHotel = {"name", "number_rooms", "chain", "stars", "adress", "email", "phone"};
        String tableNameHotel = "hotels";
        String[] whereHotel = {"name = " + hotelName};
        List<String> queriedHotels = app.selectFromTable(tableNameHotel, selectHotel, "Hotels", whereHotel);
        List<String> correctHotel = new ArrayList<>();
        String queryHotels = "\"Hotels\".hotels.name: " + hotelName;

        for (String s : queriedHotels) {
            if (s.startsWith(queryHotels)) {
                System.out.println(s);
                correctHotel.add(s);
            }
        }

        String[] selectRooms = {"room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num"};
        String tableNameRooms = "rooms";
        String[] whereRooms = {"hotel_name = " + hotelName};
        List<String> queriedRooms = app.selectFromTable(tableNameRooms, selectRooms, "Hotels", whereRooms);
        List<String> correctRooms = new ArrayList<>();
        String queryRooms = "\"Hotels\".rooms.hotel_name: " + hotelName;

        for (String s : queriedRooms) {
            if (s.startsWith(queryRooms)) {
                System.out.println(s);
                correctRooms.add(s);
            }
        }


        String beginHotel = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<br>" + ""
                + "<hr>"
                + "<head>"
                + "	<title>Search Results</title>"
                + "	<style>"
                + "  *{" +
                "     font-family: 'Epilogue', sans-serif;" +
                "     }"
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
                + "	<h1>Search Results: </h1>"
                + "	<table>"
                + "		<thead>"
                + "			<tr>"
                + "				<th>Hotel Name</th>"
                + "				<th>Number of Rooms</th>"
                + "				<th>Hotel-chain</th>"
                + "				<th>Num of Stars (Out of 5)</th>"
                + "				<th>Hotel Address</th>"
                + "				<th>Email</th>"
                + "				<th>Phone Number</th>"
                + "              <th>Delete from DB</th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";

        String middleHotel = "";

        for (int i = 0; i < correctHotel.size(); i++) {
            String[] hotelInfo = correctHotel.get(i).split(",");
            String[] fixedHotelInfo = new String[7];
            for (int j = 0; j < hotelInfo.length; j++) {
                String[] tempInfo = hotelInfo[j].split(":");
                fixedHotelInfo[j] = tempInfo[1];
            }

            middleHotel +=
                    "<tr>"
                            + "<td>" + fixedHotelInfo[0] + "</td>"
                            + "<td>" + fixedHotelInfo[1] + "</td>"
                            + "<td>" + fixedHotelInfo[2] + "</td>"
                            + "<td>" + fixedHotelInfo[3] + "</td>"
                            + "<td>" + fixedHotelInfo[4] + "</td>"
                            + "<td>" + fixedHotelInfo[5] + "</td>"
                            + "<td>" + fixedHotelInfo[6] + "</td>"
                            + "<td>Confirm Delete?"
                            + "</tr>"
                            + "<tr>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Name\" name=\"modifyHotelName\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Room Number\" name=\"modifyHotelRoomNum\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Hotel Chain\" name=\"modifyHotelChain\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Stars\" name=\"modifyHotelStars\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Address\" name=\"modifyHotelAddress\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Email\" name=\"modifyHotelEmail\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Phone Number\" name=\"modifyHotelPhone\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminDeleteServlet\" method=\"post\">\n" +
                            "<input type=\"submit\" value=\"Delete\">\n" +
                            "</form>" +
                            "</td>" +
                            "</tr>"+
                            "</tbody>" + "</table>"+"</html>";
            out.print(beginHotel + middleHotel);
            session.setAttribute("hotelName",fixedHotelInfo[0]);
            session.setAttribute("hotelNumRooms",fixedHotelInfo[1]);
            session.setAttribute("hotelChain",fixedHotelInfo[2]);
            session.setAttribute("hotelStars",fixedHotelInfo[3]);
            session.setAttribute("hotelAddress",fixedHotelInfo[4]);
            session.setAttribute("hotelEmail",fixedHotelInfo[5]);
            session.setAttribute("hotelPhoneNum",fixedHotelInfo[6]);
        }
    }
    public void roomMenu(Application app, PrintWriter out, String hotelName, String roomnum, HttpSession session){
        String[] selectRooms = {"room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num"};
        String tableNameRooms = "rooms";
        String[] whereRooms = {"hotel_name = " + hotelName};
        List<String> queriedRooms = app.selectFromTable(tableNameRooms, selectRooms, "Hotels", whereRooms);
        List<String> correctRooms = new ArrayList<>();
        String queryRooms = "\"Hotels\".rooms.hotel_name: " + hotelName;

        for (String s : queriedRooms) {
            if (s.startsWith(queryRooms)) {
                System.out.println(s);
                correctRooms.add(s);
            }
        }

        String beginRooms = "<table>"
                + "		<thead>"
                + "			<tr>"
                + "				<th>Room Number</th>"
                + "				<th>Room Type</th>"
                + "				<th>Room Price</th>"
                + "				<th>Room Capacity</th>"
                + "				<th>Room Status</th>"
                + "				<th>Room Amenities</th>"
                + "				<th>Hotel Name</th>"
                + "				<th>Agreement Number</th>"
                + "              <th>Delete from DB</th>"
                + "			</tr>"
                + "		</thead>"
                + "		<tbody>";

        String middleRooms = "";

        for (int i = 0; i < correctRooms.size(); i++) {
            String[] roomInfo = correctRooms.get(i).split(",");
            String[] fixedRoomInfo = new String[8];
            for (int j = 0; j < roomInfo.length; j++) {
                String[] tempInfo = roomInfo[j].split(":");
                fixedRoomInfo[j] = tempInfo[1];
            }

            middleRooms +=
                    "<tr>"
                            + "<td>" + fixedRoomInfo[0] + "</td>"
                            + "<td>" + fixedRoomInfo[1] + "</td>"
                            + "<td>" + fixedRoomInfo[2] + "</td>"
                            + "<td>" + fixedRoomInfo[3] + "</td>"
                            + "<td>" + fixedRoomInfo[4] + "</td>"
                            + "<td>" + fixedRoomInfo[5] + "</td>"
                            + "<td>" + fixedRoomInfo[6] + "</td>"
                            + "<td>" + fixedRoomInfo[7] + "</td>"
                            + "<td>Confirm Delete?</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Number\" name=\"modifyRoomNum\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Type\" name=\"modifyRoomType\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Price\" name=\"modifyRoomPrice\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Capacity\" name=\"modifyRoomCapacity\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Status\" name=\"modifyRoomStatus\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Amenities\" name=\"modifyRoomAmenities\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify Hotel\" name=\"modifyHotelRoom\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminModifyServlet\" method=\"post\">"
                            + "<input type=\"submit\" value=\"Modify AgreementNum\" name=\"modifyRoomAgreement\">"
                            + "</form>"
                            + "</td>"
                            + "<td>"
                            + "<form action=\"AdminDeleteServlet\" method=\"post\">\n" +
                            "<input type=\"submit\" value=\"Delete\">\n" +
                            "</form>" +
                            "</td>" +
                            "</tr>"+
                            "		</tbody>"
                            + "	</table>"
                            + "</body>";
            out.print(beginRooms+middleRooms);

            session.setAttribute("roomNum",fixedRoomInfo[0]);
            session.setAttribute("roomType",fixedRoomInfo[1]);
            session.setAttribute("roomPrice",fixedRoomInfo[2]);
            session.setAttribute("roomCapacity",fixedRoomInfo[3]);
            session.setAttribute("roomStatus",fixedRoomInfo[4]);
            session.setAttribute("roomAmenities",fixedRoomInfo[5]);
            session.setAttribute("hotelnameRoom",fixedRoomInfo[6]);
            session.setAttribute("roomAgreementNumber",fixedRoomInfo[7]);
        }
    }
}
