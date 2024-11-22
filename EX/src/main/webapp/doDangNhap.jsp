<%@page import="dao.Login230DAO"%>
<%@page import="model.user.User230"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String email = (String) request.getParameter("email");
    String password = (String) request.getParameter("password");
    
    Login230DAO login230DAO = new Login230DAO();
    User230 user = login230DAO.login(email, password);
    
if (user != null) {
        session.setAttribute("user", user);
        if (user.getId().startsWith("M")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/manager/gdMainManagement230.jsp");
                dispatcher.forward(request, response);
            }
        
        if (user.getId().startsWith("C")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/gdMainCustomer230.jsp");
                dispatcher.forward(request, response);
            }
    }
    else {
    session.setAttribute("error", "Email or password is not correct.");
    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
    }
%>