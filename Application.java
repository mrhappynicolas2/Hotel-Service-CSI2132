import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
    private String url;
    private String user;
    private String password;

    public Application(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**Will create the connection between driver and the database */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    /**
     * @param name
     * name will be the name of the table \n
     * variables will be the variables in the table
     * @throws SQLException
     *
     * @param variables
     * variables will be a string array of the variables in the table in the format (NAME TYPE), examlpes: (name VARCHAR(20)), (age INT)
     **/
    public void createTable(String name ,String[] variables){
        String sql = "CREATE TABLE " + name + " (";
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
 * 
 * @param tableName Name of the table
 * @param tableCollumn A array with the name of the collumns
 * @param values A array with the values to be inserted (make sure they have '' around them if they are strings, example: 'Bob')
 */
    public void insertIntoTable(String tableName, String[] tableCollumn, String[] values){
        String sql = "INSERT INTO " + tableName + " (";
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

    public void deleteFromTable(String tableName, String[] values){
        //TODO:
    }

    //TODO: Currently will only find all the values in the table, change to make it custom
    public void selectFromTable() {
        try(Connection conn = this.connect();){

        Statement statement = conn.createStatement();

        // Execute a query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist");

        // Process the results
        while (resultSet.next()) {
            
            String aname = resultSet.getString("aname");
            String birthplace = resultSet.getString("birthplace");
            String style = resultSet.getString("style");
            System.out.println("aname: " + aname + ", birth: " + birthplace + ", style: " + style);
        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void setpath(String path){ //FIXME: This does not seem to work
        String sql = "SET search_path TO " + path + ";";
        try (Connection conn = this.connect();
                Statement tableCreation = conn.createStatement()) {
            
            tableCreation.execute(sql);
            System.out.println("New path has been set");
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
            String password = "password"; //FIXME: this will not work unless you change it to your password
            app = new Application(url, username, password);
        }
        else{
            String url = args[0];
            String username = args[1];
            String password = args[2];
            app = new Application(url, username, password);
            
        }
        
        String[] testQuery1 = {"Aname VARCHAR(20)","Birthplace VARCHAR(20)","Style VARCHAR(20)","PRIMARY KEY (Aname)"};
        String[][] testQuery2 = {{"aname", "birthplace", "style"},{"'Joe'","'Milan'","'Baroque'"}};
        app.setpath("Lab"); //this does not work?
        app.createTable("TestTable2", testQuery1);
        app.insertIntoTable("artist", testQuery2[0],testQuery2[1]);
        app.selectFromTable();
        //TODO: change these test casses to be related to the hotel database
        

        

        
        

    }

}
