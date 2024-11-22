package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connection230 {
    private static final Connection230 connection230 = new Connection230();
    private Connection connection;

    public Connection230() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "12345678");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error connection 230");
        }
        
    }
    
    public static Connection230 getInstance() {
        return connection230;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
