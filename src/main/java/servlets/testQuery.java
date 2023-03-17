package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.jdbc.ScriptRunner;

public class testQuery {
    //this class will be used to create the test querys to put into the project.sql file

    public static void main(String[] args) {
        
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
        String username = "postgres";
        String password = "mrhappy11"; //FIXME: this will not work unless you change it to your custom password
        Application app = new Application(url, username, password);

        try{
            ScriptRunner sr = new ScriptRunner(app.connect());
            Reader reader = new BufferedReader(new FileReader("resources/project.sql"));
            sr.runScript(reader);
            }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
       

        String[] variable = {"room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num"};
        String location = "Hotels";
        String[] where = {"hotel_name = 'hotel1'"};
        String[] where2 = {"hotel_name = 'hotel2'"};
        String[] where4 = {"hotel_name = 'hotel4'"};

        app.selectFromTable("rooms", variable, location, where);
        System.out.println("\n");
        app.selectFromTable("rooms", variable, location, where2);
        System.out.println("\n");
        System.out.println("test");
        List<String> test = app.selectFromTable("rooms", variable, location, where4);
        System.out.println("test");


        
    
    }
}
