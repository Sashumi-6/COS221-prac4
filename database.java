final class link_database {
    //Eventually add all the data needed into the strings.
    @SuppressWarnings("unused")
    private final String dvdrental_DB_PROTO = "placeholder",
                   dvdrental_DB_HOST = "placeholder",
                   dvdrental_DB_PORT = "placeholder",
                   dvdrental_DB_NAME = "placeholder",
                   dvdrental_DB_USERNAME = "placeholder",
                   dvdrental_DB_PASSWORD = "placeholder";

    //private (connection data type) connection;
    link_database() {
        try {
            //Try establish a connection
        } catch (Exception e) { //Eventually make this an speciliazed value
            //Connection failed => we should return a null value to the user.
        }
    }
}

public class database {
    private static database instance;
    //create a private connection object to the database

    database() {
        //Initilise the connection object then
        //Then we will use that object only
        //And then ofc use the singleton in the other
        //Classes to retreive data or whatever :3
    }

    //Use this method to access other methods
    public database instance() {
        if (instance == null) instance = new database();
        return instance;
    }
}