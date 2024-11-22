<%-- 
    Document   : doLogout
    Created on : Oct 17, 2024, 11:47:11 PM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("user", null);
    session.setAttribute("error", null);
    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
%>