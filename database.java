public class database {
    public static database use; // There is 100% a better name for this
    //create a private connection object to the database

    database() {
        if (use == null) {
            use = new database();
        }

        //Initilise the connection object then
        //Then we will use that object only
        //And then ofc use the singleton in the other
        //Classes to retreive data or whatever :3
    }
}