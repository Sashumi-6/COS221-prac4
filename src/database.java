package src;
import java.sql.*;
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

            //TODO remove in prod
            System.out.println("Connected!");
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
}
