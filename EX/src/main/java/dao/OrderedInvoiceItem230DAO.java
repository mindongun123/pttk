package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import model.item.ImageItem230;
import model.ordering.Invoice230;
import model.ordering.OrderedItem230;
import model.ordering.ShoppingCart230;



public class OrderedInvoiceItem230DAO extends DAO{
    private Connection connection;

    public OrderedInvoiceItem230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }
    
    
    
    public boolean addOrderedInvoiceItem(Invoice230 invoice, ShoppingCart230 shoppingCart) {
        String ADD_ORDER_INVOICE_ITEM = "CALL add_ordered_invoice_item(?, ?, ?)";

        for (Map.Entry<OrderedItem230, ImageItem230> item: shoppingCart.getItems().entrySet()) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_INVOICE_ITEM);
                preparedStatement.setString(1, item.getKey().getId());
                preparedStatement.setString(2, invoice.getId());
                preparedStatement.setInt(3, item.getKey().getOrderedQuantity());
                preparedStatement.execute();
                System.out.println("Ordered Invoice Item Added Successfully");
            } catch (SQLException e) {

                System.out.println("Ordered Invoice Item Not Added Successfully"+ e.getMessage());
                return false;
            }
        }
        return true;

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
