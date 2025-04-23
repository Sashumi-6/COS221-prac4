# COS221-prac4
University Practical. Please ignore

# TODO

    - DELETE in notifications.java
    - report.java

# HOW TO BUILD AND RUN

    1. Make sure you have make installed
        You can verify this with, in cmd, `make --version`

    2. In the makefile, change the JAR_PATH to the path to your Jar executable

    3. In cmd/powershell run:
        `make build`
        -- to clean the build run `make clean`

# HOW TO CONNECT TO DATABASE
    
    - Using Mysql workbench or any mysql DB manager,
        import the data in the northwind file into your database
            => northwind/... files are found in this directory in the repo

        dvdrental_DB_HOST       =   The server where your database is hosted ("default is localhost")
        dvdrental_DB_PORT       =   The port number you use to host your database
        dvdrental_DB_NAME       =   The name of your database
        dvdrental_DB_USERNAME   =   Your username you use to access the database instance
        dvdrental_DB_PASSWORD   =   Your password you use to access the database instance

        You will need to have a mysql compatible DB such as using a mysql client => MariaDB

# HOW TO USE
    TODO