package src;
import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class database {
    //Config the database
    private final String dvdrental_DB_PROTO = "jdbc:mysql:"; // Protocol
    private final String dvdrental_DB_HOST = "//localhost:";
    private final String dvdrental_DB_PORT = "3307/"; // => change port as neccessary || may be 3306
    private final String dvdrental_DB_NAME = "u24772756_u24658198_northwind";
    private final String dvdrental_DB_USERNAME = "root"; //change to local username || keep as root
    private final String dvdrental_DB_PASSWORD = "placeholder"; // change to your local password || if root, use root password

    private static database instance;
    private static Connection conn = null;

    database() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = dvdrental_DB_PROTO + dvdrental_DB_HOST + dvdrental_DB_PORT + dvdrental_DB_NAME;
            conn = DriverManager.getConnection(url, dvdrental_DB_USERNAME, dvdrental_DB_PASSWORD);

            System.out.println("Connected to: " + dvdrental_DB_NAME + " database\nUSERNAME: " + dvdrental_DB_USERNAME + "\tPASSWORD: " + dvdrental_DB_PASSWORD);
        } catch (Exception e) {
            String errorMsg = "Connection failed:\n";
            errorMsg += "\tCURRENT PASSWORD: " + dvdrental_DB_PASSWORD; //Commonly we forget to change the db password lol
            errorMsg += "\n" + e;
            System.out.println(errorMsg);
            System.exit(0); //Death to the program which fails to connect
        }

    }

    //Use this method to access other methods
    public static database instance() {
        if (instance == null) instance = new database();
        return instance;
    }

    public static Connection getConnection(){
        return conn;
    }
 

    //Acts as the sort and init :3
    // "You cooked with this - Pavan 2025"
    public void addToDataModel(DefaultTableModel table_model, String db_table, String[] params, String ...columns) {
        if (table_model.getRowCount() > 0) table_model.setRowCount(0);

        String query = "SELECT ";
        for (int i = 0 ; i < columns.length ; i++) {
            query += columns[i] + ((i < columns.length - 1) ? ", " : "");
        }
        query += " FROM " + db_table + " WHERE 1=1";

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
                String[] row = new String[columns.length];
                for (int idx = 0 ; idx < columns.length ; idx++) {
                    row[idx] = res.getString(idx+1);
                }

                table_model.addRow(row);
            }

            System.out.println("Successfully Added table rows");
        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        }
    }

    // THIS WORKS DO NOT RESOLVE TO A VERSION OF THIS FUNC WHICH IS NOT _THIS_ FUNCTION
    public void InsertUwU(DefaultTableModel table_model, String db_table, String[] params, String ...columns) {
        String query = "INSERT INTO " + db_table + " (";
        for (int i = 0 ; i < columns.length ; i++) {
            query += columns[i] + ((i < columns.length - 1) ? ", " : "");
        }
        query += ") VALUES(";
      
        for (int i = 0 ; i < params.length ; i++) {
            if (params[i].isEmpty()) query += "NULL" + ((i < params.length - 1) ? ", " : "");
            else query += params[i] + ((i < params.length - 1) ? ", " : "");
        }
        if (query.charAt(query.length() - 1) == ',') {
            query = query.substring(0 ,query.length() - 1);
        }
        query += ")";
        System.out.println(query);
        try (Statement stmt = conn.createStatement()) {
            int changes = stmt.executeUpdate(query);
            System.out.println("Successfull Query Execution(INSERT)\nRows Effected: " + changes);
        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        } 
    }

    public void UpdateUwU(DefaultTableModel table_model, String db_table, String[] params, String ...columns) {
        String WHERE = "WHERE " + columns[0] + "=" + params[0];
        String query = "UPDATE " + db_table + " SET ";
        for (int i = 1 ; i < params.length ; i++) {
            if (!params[i].isEmpty()) query += columns[i] + "="+params[i] + ((i < params.length - 1) ? ", " : "");
        }
        if (query.endsWith(", ")) {
            System.out.println("true");
            query = query.substring(0 ,query.length() - 2);
        }
        query += " " + WHERE;

        System.out.println(query);
        try (Statement stmt = conn.createStatement()) {
            int changes = stmt.executeUpdate(query);
            System.out.println("Successfull Query Execution(UPDATE)\nRows Effected: " + changes);
        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        }
    }
    public void DeletetUwU(DefaultTableModel table_model, String db_table, String[] params, String ...columns){ //MUST HAVE PARAMS
        String query = "DELETE FROM " + db_table + " WHERE 1=1 ";

        if (params.length > 0) query += "AND ";
        for(int j = 0; j < params.length ; j++){ 
            if (!params[j].isEmpty()) {
                if (columns[j].equals("id")) {
                    query += columns[j] + "="+params[j] + ((j < params.length - 1) ? " AND " : "");
                } else query += columns[j] + "='"+params[j] + ((j < params.length - 1) ? "' AND " : "'");
            }
        }

        System.out.println(query);
        try (Statement stmt = conn.createStatement()) {
            int changes = stmt.executeUpdate(query);
            System.out.println("Successfull Query Execution(DELETE)\nRows Effected: " + changes);
        } catch (SQLException e) {
            System.out.println("SQL ERROR:\n" + e);
            System.exit(0);
        } 
    }
    

    public void populateComboBox(JComboBox<String> combo, String tableName, String nameCol) {
        combo.removeAllItems();
        try (Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery("SELECT DISTINCT " + nameCol + " FROM " + tableName);
            while (res.next()) {
                combo.addItem(res.getString(nameCol));
            }
        } catch (SQLException e) {
            System.out.println("Failed to populate combo box: " + e);
        }
    }


}
