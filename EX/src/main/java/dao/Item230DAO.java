package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.item.Item230;
import model.item.Type230;

public class Item230DAO extends DAO {
    private Connection connection;

    public Item230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }


    public String addItem(Item230 item) {
        String SET_ID_ITEM = "SET @idItem = ''";
        String ADD_ITEM = "CALL add_item(?, ?, ?, ?, ?, @idItem)";
        String SELECT_ID_ITEM = "SELECT @idItem";
        System.out.println("_____");
        System.out.println(SET_ID_ITEM);
        System.out.println(ADD_ITEM);
        System.out.println(item.getNameItem());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SET_ID_ITEM);
            preparedStatement.executeUpdate();
            System.out.println("Thuc hien lenh set");
            preparedStatement = connection.prepareStatement(ADD_ITEM);
            preparedStatement.setString(1, item.getNameItem());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getType().getId());
            preparedStatement.setString(4, item.getUnit());
            preparedStatement.setFloat(5, item.getExportedPrice());
            preparedStatement.execute();
            System.out.println("Thuc hien lenh chen");
            preparedStatement = connection.prepareStatement(SELECT_ID_ITEM);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Thuc hien lenh select");
            if (resultSet.next()) {
                String idItem = resultSet.getString(1);
                System.out.println("idItem>> " + idItem);
                return idItem;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public ArrayList<Item230> getAllItem() {
        ArrayList<Item230> items = new ArrayList<>();

        String SELECT_ALL_ITEM = "SELECT * FROM tblItem230";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEM);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("id");
                String nameItem = result.getString("nameItem");
                String brand = result.getString("brand");
                String idType = result.getString("idType");
                String unit = result.getString("unit");
                float exportedPrice = result.getFloat("exportedPrice");

                Type230DAO type230DAO = new Type230DAO();
                Type230 type = type230DAO.getType(idType);

                Item230 item = new Item230();
                item.setId(id)
                        .setNameItem(nameItem)
                        .setBrand(brand)
                        .setExportedPrice(exportedPrice)
                        .setUnit(unit)
                        .setType(type);

                items.add(item);

            }
        } catch (Exception e) {
            return null;
        }
        return items;
    }

    public ArrayList<Item230> findItems(String name) {
        ArrayList<Item230> items = new ArrayList<>();

        String SELECT_ITEM = "SELECT * FROM tblItem230 WHERE LOWER(nameItem) LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM);
            preparedStatement.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("id");
                String nameItem = result.getString("nameItem");
                String brand = result.getString("brand");
                String idType = result.getString("idType");
                String unit = result.getString("unit");
                float exportedPrice = result.getFloat("exportedPrice");

                Item230 item = new Item230();
                item.setId(id)
                        .setNameItem(nameItem)
                        .setBrand(brand)
                        .setExportedPrice(exportedPrice)
                        .setUnit(unit);

                items.add(item);
            }
            System.out.println(items.toArray().length+"_________");
        } catch (Exception e) {
            return null;
        }
        return items;
    }

}
