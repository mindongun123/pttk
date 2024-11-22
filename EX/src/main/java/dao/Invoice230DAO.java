package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ordering.Invoice230;


public class Invoice230DAO extends DAO{
    private Connection connection;

    public Invoice230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }
    
    
    public String addInvoice(Invoice230 invoice) {
        String SET_ID_INVOICE = "SET @idInvoice = ''";
        String ADD_INVOICE = "CALL add_invoice(?, ?, @idInvoice)";
        String SELECT_ID_INVOICE = "SELECT @idInvoice";
        
        try {
            // 1. Khởi tạo biến session @idInvoice
            PreparedStatement preparedStatement = connection.prepareStatement(SET_ID_INVOICE);
            preparedStatement.executeUpdate();  // Sử dụng executeUpdate() cho SET

            // 2. Gọi thủ tục lưu trữ
            preparedStatement = connection.prepareStatement(ADD_INVOICE);
            preparedStatement.setString(1, invoice.getCustomer().getId());  // Tham số IN idClient
            preparedStatement.setFloat(2, invoice.getTotalPrice());       // Tham số IN totalPrice
            preparedStatement.executeUpdate();  // Thực thi thủ tục

            // 3. Lấy giá trị từ biến @idInvoice
            preparedStatement = connection.prepareStatement(SELECT_ID_INVOICE);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Lấy giá trị của @idInvoice từ kết quả ResultSet
                String idInvoice = resultSet.getString(1);
                return idInvoice;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
