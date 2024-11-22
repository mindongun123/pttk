import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection { // Đổi tên lớp thành DatabaseConnection

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy Driver JDBC!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Kết nối đã được đóng.");
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng kết nối!");
                e.printStackTrace();
            }
        }
    }
}
