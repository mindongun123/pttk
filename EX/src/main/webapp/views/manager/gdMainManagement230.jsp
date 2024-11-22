<%-- 
    Document   : gdMainManagement230
    Created on : Oct 6, 2024, 9:38:51 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="model.user.Manager230"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    
    <%
        Manager230 user = (Manager230) session.getAttribute("user");
    %>

    <nav style="background-color: #f1b62d">
        <a id="name" style="background-color: #f1b62d; font-weight: bold;"><%=user.getFullName()%></a>
        <a href="http://localhost:8080/untitled_war_exploded/views/manager/gdItemManagement230.jsp">Quản lý mặt hàng</a>
    </nav>

</body>
</html>
