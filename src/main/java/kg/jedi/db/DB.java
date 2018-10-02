package kg.jedi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "adb";
    private static final String USER_NAME = "usrna";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
    }

}
