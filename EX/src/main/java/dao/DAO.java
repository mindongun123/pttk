package dao;
import java.sql.Connection;

public class DAO {
    private Connection230 connection230 = Connection230.getInstance();
    public DAO() {
       
    }

    public Connection getConnection() {
        return connection230.getConnection();
    }
        
}
