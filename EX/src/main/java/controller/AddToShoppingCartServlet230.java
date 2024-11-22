package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;
import model.item.ImageItem230;
import model.item.Item230;
import model.ordering.OrderedItem230;
import model.ordering.ShoppingCart230;


@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCartServlet/*"})
public class AddToShoppingCartServlet230 extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo(); // Ví dụ: "/1"
        
        // Kiểm tra và xử lý tham số từ đường dẫn
        if (pathInfo != null) {
            String[] parts = pathInfo.split("/");
            String itemSTT = parts[1]; // lấy "1"
            
            String quanity = request.getParameter("quanity" + itemSTT);
            
            int index = Integer.parseInt(itemSTT);
            ArrayList<Item230> items = (ArrayList<Item230>) session.getAttribute("items");
            ArrayList<ImageItem230> images = (ArrayList<ImageItem230>) session.getAttribute("images");
            
            ShoppingCart230 shoppingCart = (ShoppingCart230) session.getAttribute("shoppingCart");
            
            // kiểm tra xem có trùng hàng với trước đó
            boolean isDiff = true;
            for (Map.Entry<OrderedItem230, ImageItem230> item : shoppingCart.getItems().entrySet()) {
                OrderedItem230 orderedItem = item.getKey();
                if (orderedItem.getNameItem().equals(items.get(index).getNameItem())) {
                    isDiff = false;
                    orderedItem.setOrderedQuantity(orderedItem.getOrderedQuantity() + Integer.parseInt(quanity));
                    shoppingCart.getItems().remove(item.getKey());
                    shoppingCart.addItem(orderedItem, images.get(index));
                    break;
                }
                
            }
            
            if (isDiff) {
                shoppingCart.addItem(
                    new OrderedItem230(items.get(index), Integer.parseInt(quanity)),
                    images.get(index)
                    );
            }
            session.setAttribute("shoppingCart", shoppingCart);
            session.setAttribute("success", "Thêm vào giỏ hàng thành công");
        } else {
            session.setAttribute("error", "Thêm vào giỏ hàng thất bại");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/gdOrdering230.jsp");
        dispatcher.forward(request, response);
    }

}
