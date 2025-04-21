# COS221-prac4
University Practical. Please ignore

# GUI
The main files, which are not in a folder, are the GUI files which consist of:

    => Main
        The main will be the main GUI ie. the Launch screen.
        Will have just a simple welcome or whatever and then tabs for each
        category below. Or maybe buttons for each? We will see.

    => database
        This class will have the singleton to connect to the database and do things like
            UPDATE, DELETE, FETCH data either all or some or more?
            but definitely will use a singleton for all connections and database manip

    => employees
        This will have all relevant data to do with employees

    => products
        This would be relevant data with products

    => report
        This will create a report
            ->  number of prods in each warehouse for each category
                Should return the warehouse name, category name, and num of prods in the category

    => notifications
        What the spec says.. will add all details later to previous and future tabs

# HOW TO BUILD AND RUN

    1. Make sure you have make installed
        You can verify this with, in cmd, `make --version`

    2. In the makefile, change the JAR_PATH to the path to your Jar executable

    3. In cmd/powershell run:
        `make build`
        -- to clean the build run `make clean`

# HOW TO USE
    TODO