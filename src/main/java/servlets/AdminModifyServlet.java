package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("admin_panel.html").include(request, response);

        HttpSession session = request.getSession();

        if ((String) session.getAttribute("customerSSN") != "") {
            String customerSSN = (String) session.getAttribute("customerSSN");
            customerModify(customerSSN, session,out, request);
        } else if ((String) session.getAttribute("employeeSSN") != "") {
            String employeeSSN = (String) session.getAttribute("employeeSSN");
            employeeModify(employeeSSN, session, out, request);
        } else if((String) session.getAttribute("hotelName") != ""){
            String hotelName = (String) session.getAttribute("hotelName");
            hotelModify(hotelName, session, out, request);
        } else if((String) session.getAttribute("roomNum") != ""){
            String roomNum = (String) session.getAttribute("roomNum");
            String roomHotel = (String) session.getAttribute("hotelnameRoom");
            roomModify(roomNum, roomHotel, session, out, request);
        }

    }

    public void customerModify(String customerSSN,HttpSession session, PrintWriter out, HttpServletRequest request){
        session.setAttribute("customerSSN",customerSSN);

        String begin = "<!DOCTYPE html>\n" +
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
                "        flex-direction: column;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"modifyPage\">\n" +
                "<h1>Modify here:</h1>\n" +
                "<form action=\"AdminModifyFinishServlet\" method=\"post\">\n";

        if(request.getParameter("modifyCustSSN") != null){
           String middle =
                    "    <label>\n" +
                    "        Modified Customer SSN: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustSSN\">" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
           out.println(begin+middle);
           out.close();
        }
        else if(request.getParameter("modifyCustName") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Customer Name: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustName\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyCustAddress") != null){
            String middle =
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustAddress\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyCustUser") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Customer Username: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustUsername\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyCustPass") != null){
            String middle=
                    "    <label>\n" +
                    "        Modified Customer Password: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustPassword\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyCustReg") != null){
           String middle=
                    "    <label>\n" +
                    "        Modified Customer Registration Date: <input type=\"date\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustRegistration\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyCustPhone") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Customer Phone Number: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitCustPhone\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }


    }
    public void employeeModify(String employeeSSN, HttpSession session, PrintWriter out, HttpServletRequest request){
        session.setAttribute("employeeSSN",employeeSSN);
        String begin = "<!DOCTYPE html>\n" +
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
                "        flex-direction: column;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"modifyPage\">\n" +
                "<h1>Modify here:</h1>\n" +
                "<form action=\"AdminModifyFinishServlet\" method=\"post\">\n";
        if(request.getParameter("modifyemployeeSSN") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Employee SSN: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeeSSN\">" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyEmployeeName") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Customer Name: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeeName\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyEmployeeAddress") != null){
            String middle=
                    "    <label>\n" +
                    "        Modified Employee Address: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeeAddress\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyEmployeeUser") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Employee Username: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeeUsername\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyEmployeePass") != null){
           String middle =
                    "    <label>\n" +
                    "        Modified Employee Password: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeePassword\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyEmployeeHireDate") != null){
            String middle =
                    "    <label>\n" +
                    "        Modified Employee Hire Date: <input type=\"text\" name=\"modifyText\">\n" +
                    "    </label>\n" +
                    "    <label>\n" +
                    "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitEmployeeHire\">\n" +
                    "    </label>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            out.println(begin+middle);
            out.close();
        }
    }
    public void hotelModify(String hotelName, HttpSession session, PrintWriter out, HttpServletRequest request){
        session.setAttribute("hotelName", hotelName);
        String begin = "<!DOCTYPE html>\n" +
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
                "        flex-direction: column;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"modifyPage\">\n" +
                "<h1>Modify here:</h1>\n" +
                "<form action=\"AdminModifyFinishServlet\" method=\"post\">\n";
        if(request.getParameter("modifyHotelName") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Name: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelName\">" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelRoomNum") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Num of Rooms: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelRoomNum\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelChain") != null){
            String middle=
                    "    <label>\n" +
                            "        Modified Hotel Chain: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelChain\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelStars") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Stars (Out of 5): <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelStars\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelAddress") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Address: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelAddress\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelEmail") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Email: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelEmail\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelPhone") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel Phone: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelPhone\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
    }
    public void roomModify(String roomNum, String roomHotel, HttpSession session, PrintWriter out, HttpServletRequest request){
        session.setAttribute("roomNum",roomNum);
        session.setAttribute("roomHotel",roomHotel);

        String begin = "<!DOCTYPE html>\n" +
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
                "        flex-direction: column;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"modifyPage\">\n" +
                "<h1>Modify here:</h1>\n" +
                "<form action=\"AdminModifyFinishServlet\" method=\"post\">\n";
        if(request.getParameter("modifyRoomNum") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Number: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomNum\">" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomType") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Type: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomType\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomPrice") != null){
            String middle=
                    "    <label>\n" +
                            "        Modified Room Price: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomPrice\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomCapacity") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Capacity: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomCapacity\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomStatus") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Status: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomStatus\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomAmenities") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Amenities: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomAmenities\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyHotelRoom") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Hotel of Room: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitHotelRoom\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
        else if(request.getParameter("modifyRoomAgreement") != null){
            String middle =
                    "    <label>\n" +
                            "        Modified Room Agreement Number: <input type=\"text\" name=\"modifyText\">\n" +
                            "    </label>\n" +
                            "    <label>\n" +
                            "        <input type=\"submit\" value=\"Modify\" class=\"button\" name=\"submitRoomAgreement\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";
            out.println(begin+middle);
            out.close();
        }
    }
    }



