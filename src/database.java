package src;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class database {
    //Config the database
    private final String dvdrental_DB_PROTO = "jdbc:mysql:"; // Protocol
    private final String dvdrental_DB_HOST = "//localhost:";
    private final String dvdrental_DB_PORT = "3307"; // => change port as neccessary || may be 3306
    private final String dvdrental_DB_NAME = "/northwind";
    private final String dvdrental_DB_USERNAME = "root"; //change to local username || keep as root
    private final String dvdrental_DB_PASSWORD = "@cce554me"; // change to your local password || if root, use root password

    private static database instance;
    private Connection conn = null;

    database() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = dvdrental_DB_PROTO + dvdrental_DB_HOST + dvdrental_DB_PORT + dvdrental_DB_NAME;
            conn = DriverManager.getConnection(url, dvdrental_DB_USERNAME, dvdrental_DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Connection failed:\n" + e);
            System.exit(0); //Death to the program which fails to connect
        }

    }

    //Use this method to access other methods
    public static database instance() {
        if (instance == null) instance = new database();
        return instance;
    }

    public void addEmplToDataModel(DefaultTableModel table, String ...columns) {
        String query = "SELECT ";
        for (int i = 0 ; i < columns.length ; i++) {
            query += columns[i] + ((i < columns.length - 1) ? ", " : "");
        }
        query += " FROM employees";

        try (Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        }
    }
}
