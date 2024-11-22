package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.item.Type230;

public class Type230DAO extends DAO{
    private Connection connection;
    
    public Type230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }
    
    
    public ArrayList<Type230> getAllType() {
        ArrayList<Type230> allType = new ArrayList<>();
        String SELECT_ALL_TYPE = "SELECT * FROM tblType230";

        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String idParentType = result.getString("idParentType");
                if (idParentType != null) {
                    Type230 type = new Type230();
                    type.setId(id).setName(name);

                    allType.add(type);

                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return allType;
    }
    
    public Type230 getType(String idIype) {
        String SELECT_ALL_TYPE = "SELECT * FROM tblType230 WHERE id=?";
        
        try {
           
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            preparedStatement.setString(1, idIype);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String idParentType = result.getString("idParentType");
                Type230 type = new Type230();
                type.setId(id).setName(name);
                return type;
                
            }
        } catch (SQLException ex) {
            return null;
        }
        System.out.println("Type");
        return null;
    }
   
}
