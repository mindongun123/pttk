package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;

import model.user.Customer230;
import model.user.Manager230;
import model.user.Seller230;
import model.user.User230;

public class Login230DAO extends DAO {

    private Connection connection;

    public Login230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }

    public User230 login(String email, String password) throws ParseException {
        String SELECT_USER = "SELECT * FROM tblUser230 "
                + "WHERE (email=? AND password=?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(">>>> " + preparedStatement.toString());

            while (rs.next()) {
                String id = rs.getString("id");
                System.out.println("id: " + id);
                String fullName = rs.getString("fullName");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                boolean gender = rs.getBoolean("gender");

                if (id.startsWith("C")) {
                    boolean isMember = isMember(id);
                    Customer230 client = new Customer230();
                    client.setId(id)
                            .setFullName(fullName)
                            .setEmail(email)
                            .setAddress(address)
                            .setGender(gender)
                            .setPhoneNumber(phoneNumber);
                    client.setMember(isMember);
                    return client;
                } else if (id.startsWith("M")) {
                    Manager230 manager = new Manager230();
                    manager.setId(id)
                            .setFullName(fullName)
                            .setEmail(email)
                            .setAddress(address)
                            .setPassword(password)
                            .setGender(gender)
                            .setPhoneNumber(phoneNumber);
                    return manager;
                } else if (id.startsWith("N")) {
                    Seller230 seller = new Seller230(id, fullName, null, email, password, address, gender);
                    return seller;
                }

            }
        } catch (SQLException ex) {
            printSQLException(ex);
            System.out.println(">>>> try catch");
            return null;
        }
        return null;

    }

    private String getPosition(String id) {
        String SELECT_EMPLOYEE = "SELECT * FROM tblEmployee230 WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String position = rs.getString("position");
                return position;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return null;
    }

    private boolean isMember(String id) {
        String SELECT_CLIENT = "SELECT * FROM tblCustomer230 WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                boolean isMember = rs.getBoolean("isMember");
                return isMember;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
