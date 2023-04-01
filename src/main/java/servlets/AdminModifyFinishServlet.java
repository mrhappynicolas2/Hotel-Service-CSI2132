package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminModifyFinishServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                request.getRequestDispatcher("AdminModifyServlet.java").include(request, response);

                HttpSession session = request.getSession();
                String user = String.valueOf(session.getAttribute("name"));

                String username = user.split("_")[0];
                String password = user.split("_")[1];

                String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public";
                Application app = new Application(url, username, password);


                if(request.getParameter("submitCustSSN") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitCustName") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitCustUsername") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitCustPassword") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitCustRegistration") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitCustPhone") != null){
                        String customerSSN = "ssnc = "+request.getParameter("customerSSN");
                        String[] pKey = {customerSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "customer";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitEmployeeSSN") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitEmployeeName") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);
                }
                else if(request.getParameter("submitEmployeeAddress") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitEmployeeUsername") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitEmployeePassword") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitEmployeeHire") != null){
                        String employeeSSN = "ssne = "+request.getParameter("employeeSSN");
                        String[] pKey = {employeeSSN};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "employee";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelName") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelRoomNum") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelChain") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelStars") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelAddress") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelEmail") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelPhone") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String[] pKey = {hotelName};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "hotels";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomNum") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomType") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomPrice") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomCapacity") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomStatus") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomAmenities") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitHotelRoom") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }
                else if(request.getParameter("submitRoomAgreement") != null){
                        String hotelName = "name = "+request.getParameter("hotelName");
                        String roomNum = "room_num = "+request.getParameter("roomNum");
                        String[] pKey = {hotelName,roomNum};
                        String modifiedValue = request.getParameter("modifyText");
                        String[] modifiedValueIntoUpdate = {modifiedValue};
                        String tablename = "rooms";
                        String schema = "Hotels";

                        app.updateRow(tablename,schema,modifiedValueIntoUpdate,pKey);

                }

                out.print("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<style>\n" +
                        "*{\n" +
                        "font-family: 'Epilogue', sans-serif;\n" +
                        "}\n" +
                        "table, th, td {\n" +
                        "border: 1px solid black;\n" +
                        "border-collapse: collapse;\n" +
                        "padding: 5px;\n" +
                        "}\n" +
                        "th {\n" +
                        "background-color: #ddd;\n" +
                        "}\n" +
                        ".button{\n" +
                        "    margin-top: 12px;\n" +
                        "    cursor: pointer;\n" +
                        "    text-decoration: none;\n" +
                        "    margin-right: 15px;\n" +
                        "    padding: 10px 15px;\n" +
                        "    border-radius: 30px;\n" +
                        "    background-color: #000;\n" +
                        "    color: #fff;\n" +
                        "    line-height: inherit;\n" +
                        "    font-size: 1.1rem;\n" +
                        "    font-weight: 600;\n" +
                        "    border: 2px solid #000;\n" +
                        "    letter-spacing: -0.01em;\n" +
                        "    transition: background-color 500ms ease;\n" +
                        "    }\n" +
                        "\n" +
                        "    .button:hover{\n" +
                        "    border: 2px solid #000;\n" +
                        "    background-color: transparent;\n" +
                        "    color: #000;\n" +
                        "    }\n" +
                        "\n" +
                        "    .modifyPage{\n" +
                        "        display: flex;\n" +
                        "        padding-left: 10px;\n" +
                        "        justify-content: center;\n" +
                        "        align-items: center;\n" +
                        "    }\n" +
                        "\n" +
                        "    .returnButton{\n" +
                        "        display: flex;\n" +
                        "        justify-content: center;\n" +
                        "        align-items: center;\n" +
                        "    }\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"modifyPage\">\n" +
                        "<h1>Item Modified!</h1>\n" +
                        "</div>\n" +
                        "<div class=\"returnButton\">\n" +
                        "<a href=\"admin_panel.html\" class=\"button\">Return to Admin Panel</a>\n" +
                        "</div>\n" +
                        "    \n" +
                        "</body>\n" +
                        "</html>");
                out.close();
        }

}
