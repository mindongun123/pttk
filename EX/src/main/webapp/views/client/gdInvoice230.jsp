<%-- 
    Document   : gdInvoice230
    Created on : Oct 6, 2024, 9:41:52 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="java.util.Map" %>
<%@page import="model.ordering.OrderedItem230" %>
<%@page import="model.ordering.OrderedItem230" %>
<%@page import="model.item.ImageItem230" %>
<%@page import="model.ordering.ShoppingCart230" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="model.user.Customer230" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hóa đơn</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>

<%
    Customer230 user = (Customer230) session.getAttribute("user");
%>

<nav style="background-color: #f1b62d">
    <a id="name" style="background-color: #f1b62d; font-weight: bold;"><%=user.getFullName()%>
    </a>
    <a href="http://localhost:8080/untitled_war_exploded/views/client/gdOrdering230.jsp">Đặt hàng</a>
    <a href="http://localhost:8080/untitled_war_exploded/views/client/gdShoppingCart230.jsp">Giỏ hàng</a>
</nav>

<div class="container my-3" style="max-width: 100%; max-height: 5%; padding-inline: 10%; padding-top: 2rem;">
    <div class="justify-content-center d-flex">
        <h3>Hóa đơn</h3>
    </div>
    <table class="table text-start">
        <tbody>
        <tr>
            <th scope="row">Tên khách hàng</th>
            <td><%=user.getFullName()%>
            </td>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Số điện thoại</th>
            <td><%=user.getPhoneNumber() %>
            </td>

        </tr>
        <tr>
            <th scope="row">Email</th>
            <td><%=user.getEmail() %>
            </td>

        </tr>
        <tr>
            <th scope="row">Địa chỉ</th>
            <td><%=user.getAddress()%>
            </td>
        </tr>

        </tbody>
    </table>
    <table class="table text-start">
        <h4>Danh sách mặt hàng</h4>
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Mã mặt hàng</th>
            <th scope="col">Tên mặt hàng</th>
            <th scope="col" class="text-center">Số lượng</th>
            <th scope="col" class="text-center">Đơn giá</th>
            <th scope="col" class="text-center">Thành tiền</th>
        </tr>
        </thead>
        <tbody>
        <%
            ShoppingCart230 shoppingCart230 = (ShoppingCart230) session.getAttribute("shoppingCart");
            double total = 0.0;
            int stt = 1;

            for (Map.Entry<OrderedItem230, ImageItem230> item : shoppingCart230.getItems().entrySet()) {
                OrderedItem230 orderedItem = item.getKey();

        %>
        <tr>
            <th scope="row"><%=stt%>
            </th>
            <td><%=orderedItem.getId() %>
            </td>
            <td><%=orderedItem.getNameItem()%>
            </td>
            <td class="text-center"><%=orderedItem.getOrderedQuantity()%>
            </td>
            <td class="text-center"><%=orderedItem.getExportedPrice()%> VND</td>
            <td class="text-center"><%=orderedItem.getTotalPrice()%> VND</td>
        </tr>
        <%
                total += orderedItem.getTotalPrice();
                stt++;
            }
        %>
        <tr>
            <th scope="row">Tổng tiền</th>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td class="text-center"><%=total%> VND</td>
        </tr>
        </tbody>
    </table>

    <%--        <form action="${pageContext.request.contextPath}/saveInvoiceServlet" method="POST">--%>
    <form action="http://localhost:8080/untitled_war_exploded/saveInvoiceServlet" method="POST">
        <div class="d-flex justify-content-center py-3">
            <button type="submit" class="btn btn-success btn-lg mx-1">Lưu</button>
            <button type="button" class="btn btn-outline-secondary btn-lg mx-1"
                    onclick="openPage('<%=request.getContextPath()%>/views/client/gdShoppingCart230.jsp')">Hủy
            </button>
        </div>
    </form>
</div>

</body>
</html>
<script>
    function openPage(url) {
        window.location.href = url; // Chuyển hướng đến URL
    }
</script>
    