package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.item.ImageItem230;
import java.sql.ResultSet;


public class ImageItem230DAO extends DAO {
    private Connection connection;

    public ImageItem230DAO() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }
    
    
    public boolean addImageItem230(ImageItem230 imageItem) {
        String ADD_IMAGE_ITEM = "CALL add_image_item(?, ?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_IMAGE_ITEM);
            preparedStatement.setString(1, imageItem.getIdItem());
            preparedStatement.setString(2, imageItem.getLink());
            System.out.println(preparedStatement);
            preparedStatement.execute();
            System.out.println("After execute add image: " + preparedStatement);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public ImageItem230 getAnImage(String idItem) {
        String SELECT_AN_IMAGE = "SELECT * FROM tblImageItem230 WHERE idItem = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AN_IMAGE);
            preparedStatement.setString(1, idItem);
            ResultSet result = preparedStatement.executeQuery();
            
            while (result.next()) {
                String id = result.getString("id");
                String idI = result.getString("idItem");
                String link = result.getString("link");
                
                ImageItem230 image = new ImageItem230(id, idItem, link);
                return image;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
