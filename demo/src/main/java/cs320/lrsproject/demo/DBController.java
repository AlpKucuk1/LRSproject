package cs320.lrsproject.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
    public class dbConnection {
        final static String url = "jdbc:mysql://localhost:3306/CS202Project";
        final static String user = "root";
        final static String password = "16Oyts26";

        public static Connection getConnection() {
            Connection myConn = null;

            try {
                myConn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                System.out.println("\n >>> Error program can not connect to database");
            }
            return myConn;
        }
    }
    }
