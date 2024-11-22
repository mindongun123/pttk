package controller;

import dao.ImageItem230DAO;
import dao.Item230DAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import model.item.ImageItem230;
import model.item.Item230;


@WebServlet(name = "SearchItemsServlet", urlPatterns = {"/searchItems"})
public class SearchItemsServlet230 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String searchQuery = request.getParameter("search");
            HttpSession session = request.getSession();
            session.setAttribute("searchQuery", searchQuery);
            
            Item230DAO item230DAO = new Item230DAO();
            ImageItem230DAO imageItem230DAO = new ImageItem230DAO();
            ArrayList<Item230> items = item230DAO.findItems(searchQuery);
            
            ArrayList<ImageItem230> images = new ArrayList<>();
            for (Item230 item: items) {
                ImageItem230 image = imageItem230DAO.getAnImage(item.getId());
                images.add(image);
            }
            System.out.println(items.size());
            System.out.println(images.toArray().length);
            request.setAttribute("items", items);
            request.setAttribute("images", images);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/gdOrdering230.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            System.out.println("SearchItemsServlet ---> " + ex.toString());
        }
    }
    
}
