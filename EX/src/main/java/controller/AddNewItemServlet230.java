package controller;

import dao.ImageItem230DAO;
import dao.Item230DAO;
import dao.OtherItemInfo230DAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.item.ImageItem230;
import model.item.Item230;
import model.item.OtherItemInfo230;
import model.item.Type230;


@WebServlet(name = "AddNewItemServlet", urlPatterns = {"/doAddNewItem"})
@MultipartConfig
public class AddNewItemServlet230 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return;
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nameItem = (String) request.getParameter("nameItem");
            String idType = (String) request.getParameter("type");
            String brand = (String) request.getParameter("brand");
            float exportedPrice =  Float.parseFloat(request.getParameter("exportedPrice"));
            String unit = (String) request.getParameter("unit");
            
            Item230DAO item230DAO = new Item230DAO();
            ImageItem230DAO imageItem230DAO = new ImageItem230DAO();
            OtherItemInfo230DAO otherItemInfo230DAO = new OtherItemInfo230DAO();
            
            Item230 item = new Item230();
            item.setNameItem(nameItem)
                    .setBrand(brand)
                    .setExportedPrice(exportedPrice)
                    .setUnit(unit)
                    .setType((new Type230()).setId(idType));
            String idItem = item230DAO.addItem(item);
            System.out.println("idItem: " + idItem);
            
            boolean isAddInfo = true;
            int infoIndex = 1;
            while (idItem != null) {
                String nameInfo = request.getParameter("nameInfo" + Integer.toString(infoIndex));
                String describeInfo = request.getParameter("describeInfo" + Integer.toString(infoIndex));
//                System.out.println("nameInfo: " + nameInfo);
                
                if (nameInfo != null && !nameInfo.isEmpty()) {
                    OtherItemInfo230 otherInfo = new OtherItemInfo230();
                    otherInfo.setIdItem(idItem).setNameInfo(nameInfo).setDetailInfo(describeInfo);
                    otherItemInfo230DAO.addOtherItemInfo230(otherInfo);
                } else {
                    break;
                }
                infoIndex++;
            }
            
            System.out.println("Add info success");
            
            boolean isAddImage = true;
            int imageIndex = 1;
            System.out.println("imageIndex: " + imageIndex);
            String uploadPath = "C:/mindongun/MJSchool/PTTKHTTT/Example/EX/src/main/webapp/images/items/" + idItem;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            while (idItem != null) {
                try {
                    // Xử lý hình ảnh
                    Part filePart = request.getPart("image" + Integer.toString(imageIndex)); // Lấy tệp hình ảnh
                    String filePath = uploadPath + "/image" + Integer.toString(imageIndex) + ".png"; // Đường dẫn đầy đủ để lưu tệp
                    
                    // Ghi tệp hình ảnh vào server
                    try (InputStream fileContent = filePart.getInputStream();
                         FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fileContent.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                    

                    ImageItem230 image = new ImageItem230();
                    image.setIdItem(idItem)
                            .setLink("http://localhost:8080/untitled_war_exploded/images/items/"+
                                    idItem + "/image"+ Integer.toString(imageIndex) +".png");
                    imageItem230DAO.addImageItem230(image);
                } catch (Exception e) {
                    System.out.println(e.toString());
                    break;
                }
                imageIndex++;
            }
            
            System.out.println("Add img success");
            System.out.println("idItem"+idItem);
            if (idItem != null && isAddImage && isAddInfo) {
                HttpSession session = request.getSession();
                session.setAttribute("success", "Thêm mặt hàng mới thành công");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("error", "Thêm mặt hàng mới thất bại");
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/manager/gdItemManagement230.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddNewItemServlet230.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNewItemServlet230.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
