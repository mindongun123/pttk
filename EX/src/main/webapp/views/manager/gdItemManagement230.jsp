<%-- 
    Document   : gdItemManagement230
    Created on : Oct 6, 2024, 9:39:34 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="model.item.Item230" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dao.Item230DAO" %>
<%@page import="model.user.Manager230" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản lý mặt hàng</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
<%
    String error = (String) session.getAttribute("error");
    if (error != null) {
%>
<div id="myNotification" class="notification-error">
    <%= error %>
</div>
<%
        session.setAttribute("error", null);
    }
%>
<%
    String success = (String) session.getAttribute("success");
    if (success != null) {
%>
<div id="myNotification" class="notification-success">
    <%= success %>
</div>
<%
        session.setAttribute("success", null);
    }
%>
<%
    Manager230 user = (Manager230) session.getAttribute("user");
%>

<nav style="background-color: #f1b62d">
    <a id="name" style="background-color: #f1b62d; font-weight: bold;"><%=user.getFullName()%>
    </a>
    <a href="" class="select">Quản lý mặt hàng</a>
</nav>

<div class="container text-right my-3">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">

        <button type="button" class="btn btn-success btn-lg"
                onclick="openPage('<%=request.getContextPath()%>/views/manager/gdAddNewItem230.jsp')">+ Thêm mặt hàng
        </button>
    </div>
</div>

<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Mã mặt hàng</th>
            <th scope="col" class="text-start">Tên mặt hàng</th>
            <th scope="col">Loại</th>
            <th scope="col">Nhãn hiệu</th>
            <th scope="col">Giá bán</th>
        </tr>
        </thead>
        <tbody>
        <%
            Item230DAO item230DAO = new Item230DAO();
            ArrayList<Item230> items = item230DAO.getAllItem();
            int stt = 1;
            for (Item230 item : items) {
        %>
        <tr>
            <th scope="row"><%=stt%>
            </th>
            <td><%=item.getId() %>
            </td>
            <td class="text-start"><%=item.getNameItem()%>
            </td>
            <td><%=item.getType().getName()%>
            </td>
            <td><%=item.getBrand()%>
            </td>
            <td><%=item.getExportedPrice() %> VND</td>
        </tr>
        <%
                stt++;
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>

<script>
    function openPage(url) {
        window.location.href = url; // Chuyển hướng đến URL
    }
</script>
<script>
    // Hiển thị thông báo khi tải trang
    window.onload = function () {
        var notification = document.getElementById('myNotification');
        notification.style.display = 'block';

        // Tắt thông báo sau 3 giây (3000 milliseconds)
        setTimeout(function () {
            notification.style.display = 'none';
        }, 3000);
    };
</script>
