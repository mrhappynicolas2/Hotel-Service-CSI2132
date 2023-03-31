package servlets;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Application {
    private String url;
    private String user;
    private String password;

    public Application(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Application(){}

    /**Will create the connection between driver and the database */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    /**
     * @param name will be the name of the table
     * @param variables variables will be a string array of the variables in 
     * the table in the format (NAME TYPE), examlpes: (name VARCHAR(20)), (age INT)
     * @param schema will be the schema of the table, example: "public","test"
     * @throws SQLException
     **/
    public void createTable(String name ,String[] variables, String schema){
        String sql = "CREATE TABLE IF NOT EXISTS \""+schema+"\"." + name + " (";
        int i;
        for (i = 0; i < variables.length-1; i++) {
            sql = sql+variables[i]+", ";
            
        }
        sql = sql+variables[i]+");";

        try (Connection conn = this.connect();
                Statement tableCreation = conn.createStatement()) {
            tableCreation.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param tableName Name of the table
     * @param tableCollumn A array with the name of the collumns
     * @param values A array with the values to be inserted (make sure they have '' around them if they are strings, example: 'Bob')
     */
    public void insertIntoTable(String tableName, String[] tableCollumn, String[] values, String schema){ 
        String sql = "INSERT INTO " + "\""+schema+"\"." + tableName + " (";
        int i;
        for (i = 0; i < tableCollumn.length-1; i++) {
            sql = sql+tableCollumn[i]+", ";
        }
        sql = sql+tableCollumn[i]+") VALUES (";
        for (i = 0; i < values.length-1; i++) {
            sql = sql+values[i]+", ";
            
        }
        sql = sql+values[i]+");";

        try (Connection conn = this.connect();
                Statement tableCreation = conn.createStatement()) {
            tableCreation.executeUpdate(sql);
            System.out.println("Variables has been inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 
     * @param schemaName Name of the Schema
     */
    public void createSchema(String schemaName){
        String sql = 
            "CREATE SCHEMA IF NOT EXISTS \""+schemaName+"\""
            +"AUTHORIZATION pg_database_owner;"
        
            +"COMMENT ON SCHEMA \""+schemaName+"\""
            +"IS 'SET search_path = \""+schemaName+"\"';"
        
            +"GRANT ALL ON SCHEMA \""+schemaName+"\" TO PUBLIC;"
            
            +"GRANT ALL ON SCHEMA \""+schemaName+"\" TO pg_database_owner;"
            
            +"ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA \""+schemaName+"\""
            +"GRANT ALL ON TABLES TO PUBLIC;"
            
            +"ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA \""+schemaName+"\""
            +"GRANT ALL ON SEQUENCES TO PUBLIC;"
            
            +"ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA \""+schemaName+"\""
            +"GRANT EXECUTE ON FUNCTIONS TO PUBLIC;"
            
            +"ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA \""+schemaName+"\""
            +"GRANT USAGE ON TYPES TO PUBLIC;";
        try (Connection conn = this.connect();
                Statement tableCreation = conn.createStatement()) {
            tableCreation.executeUpdate(sql);
            System.out.println("Schema created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param tableName Name of table
     * @param variable The variables to be selected
     * @param schema The schema of the schema
     */
    public void selectFromTable(String tableName ,String[] variable, String schema){
        try(Connection conn = this.connect();){

            Statement statement = conn.createStatement();
            String sql = "SELECT (";
            //if no variables are given in the command line
            if (variable.length == 0) {
                System.out.println("No variables has been selected");
                return;
            }

            //if only 1 variable is given in the command line
            else if(variable.length == 1){
                // Execute a query
                sql = "SELECT " + variable[0] + " FROM \""+schema+"\"." + tableName + ";";
                
            }

            else{
            // Execute a query
            sql = "SELECT (";
            int i = 0;
            for (i = 0; i < variable.length-1; i++) {
                sql = sql+variable[i]+", ";
            }
            sql = sql+variable[i]+") FROM \""+schema+"\"." + tableName + ";";}
            
            // Process the results
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                
                if (variable.length == 1){
                
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString(variable[i]);
                        System.out.print(variable[i] + ": " + variableName);
                    }
                    System.out.println();
                }

                if (variable.length > 1){
                
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString("row");
                        variableName = variableName.substring(1,variableName.indexOf(")"));
                        String[] variableName2 = variableName.split(",");
                        System.out.print(variable[i] + ": " + variableName2[i]+", ");
                    }
                    System.out.println();
                }


            }} catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            

    }

    /**
     * 
     * @param tableName Table name
     * @param variable Name of column
     * @param schema Name of the schema 
     * @param where specifics of what your serching for (example: name = 'Bob', age = 20")
     */
    public List<String> selectFromTable(String tableName ,String[] variable, String schema, String[] where){
        List<String> searchResult = new ArrayList<String>();
        try(Connection conn = this.connect();){
            
            Statement statement = conn.createStatement();
            String sql = "SELECT (";
            //if no variables are given in the command line
            if (variable.length == 0) {
                System.out.println("No variables has been selected");
                return searchResult;
            }

            //if only 1 variable is given in the command line
            else if(variable.length == 1){
                // Execute a query
                sql = "SELECT " + variable[0] + " FROM \""+schema+"\"." + tableName;
                
            }

            else{
            // Execute a query
                sql = "SELECT (";
                int i = 0;
                for (i = 0; i < variable.length-1; i++) {
                    sql = sql+variable[i]+", ";
                }
                sql = sql+variable[i]+") FROM \""+schema+"\"." + tableName;
            }
            
            if (where.length == 0) {
                System.out.println("No where statement has been given");
                return searchResult;
            }
            else if (where.length == 1) {
                sql = sql + " WHERE " + where[0] + ";";
            }
            else {
                sql = sql + " WHERE ";
                int i;
                for (i =0; i<where.length-1; i++){
                    sql = sql + where[i] + " AND " ;
                }
                sql = sql + where[i] + ";";
            }


            // Process the results
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                
                if (variable.length == 1){
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString(variable[i]);
                        System.out.print(variable[i] + ": " + variableName);
                        searchResult.add(variable[i] + ": " + variableName);
                    }
                    System.out.println();
                }

                if (variable.length > 1){
                    String stringResult = "";
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString("row");
                        variableName = variableName.substring(1,variableName.indexOf(")"));
                        String[] variableName2 = variableName.split(",");
                        System.out.print(variable[i] + ": " + variableName2[i]+", ");
                        stringResult = stringResult+variable[i] + ": " + variableName2[i]+", ";
                        if (variableName2.length == i+1){
                            break;
                        }
                    }
                    System.out.println();
                    searchResult.add(stringResult);
                }


            }} catch (SQLException e) {
                System.out.println(e.getMessage());
            }
//catch arrayoutofbounds exception
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

            return searchResult;
    }
    
    /**
     * 
     * @param tableName Name of the table
     * @param schema Name of the schema (Hotels)
     * @param values values to be changed (ex: room_status = 'free', room_annimities = 'test')
     * @param where specifics of what your serching for (example: agreement_num = '1')
     */
    public void updateRow(String tableName, String schema, String[] values, String[] where){ 
        String sql = "UPDATE " + "\""+schema+"\"." + tableName + " SET ";
        
        //if only 1 item
        if (values.length == 1) {
            sql = sql+values[0]+" WHERE ";
        }

        //if more than 1 item
        else{
            int i;
            for (i = 0; i < values.length-1; i++) {
                sql = sql+values[i]+", ";
            }
            sql = sql+values[i]+" WHERE ";
        }
        
        //if only 1 item
        if (where.length == 1){
            sql = sql+where[0]+";";

        }

        else{
            int i = 0;
            for (i = 0; i < where.length-1; i++) {
                sql = sql+where[i]+" AND ";
                
            }
            sql = sql+where[0]+";";
        }

        

        try (Connection conn = this.connect();
                Statement tableCreation = conn.createStatement()) {
            tableCreation.executeUpdate(sql);
            System.out.println("Variables has been updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param tableName Table names
     * @param variable Name of column
     * @param schema Name of the schema 
     * @param where specifics of what your serching for (example: name = 'Bob', age = 20")
     */
    public List<String> foreignSelect(String[] tableName, String[] variable, String schema, String[] where){
        List<String> searchResult = new ArrayList<String>();
        try(Connection conn = this.connect();){
            
            Statement statement = conn.createStatement();
            String sql = "SELECT (";
            //if no variables are given in the command line
            if (variable.length == 0) {
                System.out.println("No variables has been selected");
                return searchResult;
            }

            //if only 1 variable is given in the command line
            else if(variable.length == 1){
                // Execute a query
                if(tableName.length == 1) {sql = "SELECT " + variable[0] + " FROM \""+schema+"\"." + tableName;}
                else{
                    sql = sql + variable[0] +")" + " FROM \""+schema+"\".";
                    for(int i = 0; i<tableName.length-1; i++){
                         sql = sql + tableName[i] + ", ";
                    }
                    sql = sql +"\""+ schema +"\"."+ tableName[tableName.length-1];
                }
                
            }

            else{
            // Execute a query
                sql = "SELECT (";
                int i = 0;
                for (i = 0; i < variable.length-1; i++) {
                    sql = sql+variable[i]+", ";
                }

                if(tableName.length == 1) {sql = "SELECT " + variable[0] + " FROM \""+schema+"\"." + tableName;}
                
                else{
                    sql = sql+variable[i]+") FROM \""+schema+"\".";
                    for(int j = 0; j<tableName.length-1; j++){
                         sql = sql + tableName[j] + ", \""+schema+"\".";
                    }
                    sql = sql + tableName[tableName.length-1];
                }
               
            }
            
            if (where.length == 0) {
                System.out.println("No where statement has been given");
                return searchResult;
            }
            else if (where.length == 1) {
                sql = sql + " WHERE " + where[0] + ";";
            }
            else {
                sql = sql + " WHERE ";
                int i;
                for (i =0; i<where.length-1; i++){
                    sql = sql + where[i] + " AND " ;
                }
                sql = sql + where[i] + ";";
            }


            // Process the results
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                
                if (variable.length == 1){
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString(variable[i]);
                        System.out.print(variable[i] + ": " + variableName);
                        searchResult.add(variable[i] + ": " + variableName);
                    }
                    System.out.println();
                }

                if (variable.length > 1){
                    String stringResult = "";
                    for (int i = 0; i < variable.length; i++) {
                        String variableName = resultSet.getString("row");
                        variableName = variableName.substring(1,variableName.indexOf(")"));
                        String[] variableName2 = variableName.split(",");
                        System.out.print(variable[i] + ": " + variableName2[i]+", ");
                        stringResult = stringResult+variable[i] + ": " + variableName2[i]+", ";
                        if (variableName2.length == i+1){
                            break;
                        }
                    }
                    System.out.println();
                    searchResult.add(stringResult);
                }


            }} catch (SQLException e) {
                System.out.println(e.getMessage());
            }
//catch arrayoutofbounds exception
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

            return searchResult;

    }
    //* This will reset the database to the project.sql version */
    public void databaseRefresh(Application app){
        
        try{
            ScriptRunner sr = new ScriptRunner(app.connect());
            Reader reader = new BufferedReader(new FileReader("resources/refresh.sql"));
            sr.runScript(reader);
            }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public void deleteFromTable(String tableName, String value){
        String sql = "DELETE FROM " + tableName + " WHERE "+value;

        try (Connection conn = this.connect();
             Statement tableCreation = conn.createStatement()) {
            tableCreation.executeUpdate(sql);
            System.out.println("Row has been deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //main class
    public static void main(String[] args) {
        Application app;
        if (args.length != 3) {
            System.out.println("Usage: <url> <username> <password>");
            System.out.println("We will use the default values");
            String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
            String username = "postgres";
            String password = "mrhappy11"; //FIXME: this will not work unless you change it to your custom password
            app = new Application(url, username, password);
        }
        else{
            String url = args[0];
            String username = args[1];
            String password = args[2];
            app = new Application(url, username, password);
            
        }
        

        //TODO: add more test querys
        /* 
        String[] testQuery5 = {"name VARCHAR(20)","number_hotels INTEGER","adress VARCHAR(20)","email VARCHAR(20)","phone VARCHAR(20)","PRIMARY KEY (name)"};
        String[][] testQuery6 = {{"name", "number_hotels", "adress", "email", "phone"},{"'Hotel1'","'10'","'adress1'","'email1'","'phone1'"}};
        String[][] testQuery7 = {{"name", "number_hotels", "adress", "email", "phone"},{"'Hotel2'","'20'","'adress2'","'email2'","'phone2'"}};
        String[][] testQuery8 = {{"name", "number_hotels", "adress", "email", "phone"},{"'Hotel3'","'30'","'adress3'","'email3'","'phone3'"}};
        String[] testQuery9 = {"name","number_hotels","adress"};
        String[][] testQueryWhere = {{"name","number_hotels","adress","email"},{"number_hotels > 10", "email = \'email2\'"}};

        app.createSchema("Hotels");
        app.createTable("Hotels", testQuery5, "Hotels");
        app.insertIntoTable("Hotels", testQuery6[0],testQuery6[1], "Hotels");
        app.insertIntoTable("Hotels", testQuery7[0],testQuery7[1], "Hotels");
        app.insertIntoTable("Hotels", testQuery8[0],testQuery8[1], "Hotels");
        app.selectFromTable("Hotels", testQuery9, "Hotels");
        app.selectFromTable("Hotels", testQueryWhere[0], "Hotels", testQueryWhere[1]);
        */

        if (false) { //change this value to true if you want to refresh the database
            System.out.println("Database has been refreshed");
            app.databaseRefresh(app);}
        else{
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
            //TODO: Make it so it will not insert into a table if the query already exist
            //https://stackoverflow.com/questions/1361340/how-can-i-do-insert-if-not-exists-in-mysql

            String[][] testQueryNull = {{"room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num"},{"room_status = 'used'"}};
            
            app.selectFromTable("Rooms", testQueryNull[0], "Hotels", testQueryNull[1] );

            String[][] testQuery2 = {{"room_status = 'reserved'", "room_annimities = 'test3'"},{"agreement_num = '1'", "room_type = 'five'"}};
            app.updateRow("rooms", "Hotels", testQuery2[0], testQuery2[1]);
            
            String[][] testQuery3 = {{"room = 1", "hotel = 'hotel101'"},{"agreement_num = '1'"}};
            app.updateRow("agreement", "Hotels", testQuery3[0], testQuery3[1]);

            String[] tablename = {"agreement", "rooms"};
			String[] select = {"\"Hotels\".agreement.agreement_num", "\"Hotels\".rooms.hotel_name", "\"Hotels\".rooms.room_num", "\"Hotels\".rooms.room_type", "\"Hotels\".rooms.room_price", "\"Hotels\".rooms.room_capacity", "\"Hotels\".agreement.ssn"};
			String value[] = {"room_status = 'free'"};
            String where[] = {"\"Hotels\".rooms.room_num = \"Hotels\".agreement.room", "\"Hotels\".rooms.hotel_name = \"Hotels\".agreement.hotel"};
            List<String> test = app.foreignSelect(tablename, select,"Hotels", where);
            
            String query = "\"Hotels\".agreement.agreement_num: 1" ;
			for (String s: test ) {
				if (s.startsWith(query))
				 System.out.println(s);
            }
            System.out.println("end");
            

            


        }
    }

}
