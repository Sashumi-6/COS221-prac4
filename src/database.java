package src;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class database {
    //Config the database
    private final String dvdrental_DB_PROTO = "jdbc:mysql:"; // Protocol
    private final String dvdrental_DB_HOST = "//localhost:";
    private final String dvdrental_DB_PORT = "3307/"; // => change port as neccessary || may be 3306
    private final String dvdrental_DB_NAME = "northwind";
    private final String dvdrental_DB_USERNAME = "root"; //change to local username || keep as root
    private final String dvdrental_DB_PASSWORD = "@cce554me"; // change to your local password || if root, use root password

    private static database instance;
    private Connection conn = null;

    database() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = dvdrental_DB_PROTO + dvdrental_DB_HOST + dvdrental_DB_PORT + dvdrental_DB_NAME;
            conn = DriverManager.getConnection(url, dvdrental_DB_USERNAME, dvdrental_DB_PASSWORD);

            System.out.println("Connected to: " + dvdrental_DB_NAME + " database\nUSERNAME: " + dvdrental_DB_USERNAME + "\tPASSWORD: " + dvdrental_DB_PASSWORD);
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

    //Acts as the sort and init :3
    public void addEmplToDataModel(DefaultTableModel table, String[] params, String ...columns) {
        if (table.getRowCount() > 0) table.setRowCount(0);

        String query = "SELECT ";
        for (int i = 0 ; i < columns.length ; i++) {
            query += columns[i] + ((i < columns.length - 1) ? ", " : "");
        }
        query += " FROM employees WHERE 1=1";

        if (params != null && params.length > 0) {
            System.out.println("Sorting...");
            query += " AND ";
            int itt = 0;
            for (String p : params) {
                String[] col_val_set = p.split("="); //idx[0] => col ; idx[1] => val
                query += col_val_set[0] + "='" + col_val_set[1] + "'";
                if (itt++ < params.length - 1) query += " AND ";
            }
        }
        System.out.println(query);

        try (Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                String[] row = {
                    res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6),
                    res.getString(7), res.getString(8),
                    res.getString(9), res.getString(10)
                };
                table.addRow(row);
            }

            System.out.println("Successfully Added table rows");
        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        }
    }
}
