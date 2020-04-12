package com.esiea.tp4A;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDB {
    // JDBC driver name and database URL
    //todo : put it in a properties file
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    final static Logger LOGGER = Logger.getLogger(MainDB.class);


    public static void main(String[] args) {
        BasicConfigurator.configure();

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            LOGGER.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            LOGGER.info("Creating table in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE PLAYERS " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255) not NULL, " +
                " roverState BOOLEAN not NULL, " +
                " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            LOGGER.info("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        LOGGER.info("Goodbye!");
    }
}
