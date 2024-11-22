package controller;

import dao.Invoice230DAO;
import dao.OrderedInvoiceItem230DAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.item.ImageItem230;
import model.ordering.Invoice230;
import model.ordering.OrderedItem230;
import model.ordering.ShoppingCart230;
import model.user.Customer230;

@WebServlet(name = "SaveInvoiceServlet", urlPatterns = {"/saveInvoiceServlet"})
public class SaveInvoiceServlet230 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        try {
            Invoice230DAO invoice230DAO = new Invoice230DAO();
            OrderedInvoiceItem230DAO orderedInvoiceItem230DAO = new OrderedInvoiceItem230DAO();
            
            Customer230 customer = (Customer230) session.getAttribute("user");
            ShoppingCart230 shoppingCart230 = (ShoppingCart230) session.getAttribute("shoppingCart");

            System.out.println(shoppingCart230);
            
            Map<OrderedItem230, ImageItem230> items = shoppingCart230.getItems();
            Invoice230 invoice = new Invoice230();
            System.out.println(invoice.getId()+"--------------");
            double total = 0.0;
            for (Map.Entry<OrderedItem230, ImageItem230> item : items.entrySet()) {
                OrderedItem230 orderedItem = item.getKey();
                total += orderedItem.getTotalPrice();
                System.out.println(total);
            }
            
            invoice.setCustomer(customer).setTotalPrice((float) total);
            String idInvocie = invoice230DAO.addInvoice(invoice);
            invoice.setId(idInvocie);
            System.out.println(idInvocie+"-----------");

            for (Map.Entry<OrderedItem230, ImageItem230> item : items.entrySet()) {
                OrderedItem230 orderedItem = item.getKey();
                orderedInvoiceItem230DAO.addOrderedInvoiceItem(
                        invoice, 
                        shoppingCart230
                );
            }
            shoppingCart230.resetShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart230);
            session.setAttribute("success", "Lưu đơn hàng thành công");
            
        } catch (SQLException ex) {
            Logger.getLogger(SaveInvoiceServlet230.class.getName()).log(Level.SEVERE, null, ex);
//            session.setAttribute("success", "Lưu đơn hàng thành công");
            session.setAttribute("error", "Lưu đơn chưa thành công");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveInvoiceServlet230.class.getName()).log(Level.SEVERE, null, ex);
//            session.setAttribute("success", "Lưu đơn hàng thành công");
            session.setAttribute("success", "Lưu đơn chưa thành công");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/gdShoppingCart230.jsp");
        dispatcher.forward(request, response);
        
    }


}
