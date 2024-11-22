<%-- 
    Document   : gdOrdering230
    Created on : Oct 6, 2024, 9:41:14 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="model.item.ImageItem230" %>
<%@page import="model.item.Item230" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.user.Customer230" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đặt hàng</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>

<style>

    .css-item {
        display: flex;
        flex-direction: column;
    }
</style>


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
    Customer230 user = (Customer230) session.getAttribute("user");
    String searchQuery = (String) session.getAttribute("searchQuery");
%>

<nav style="background-color: #f1b62d">
    <a id="name" style="background-color: #f1b62d; font-weight: bold;"><%=user.getFullName()%>
    </a>
    <a href="" class="select">Đặt hàng</a>
    <a href="http://localhost:8080/untitled_war_exploded/views/client/gdShoppingCart230.jsp">Giỏ hàng</a>
</nav>

<div class="container text-right my-3">
    <form action="http://localhost:8080/untitled_war_exploded/searchItems" method="GET">
        <div class="row">
            <div class="col-10">
                <input type="search" class="form-control" id="search" name="search" placeholder="<%=searchQuery%>">
            </div>
            <div class="col-2">
                <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
            </div>
        </div>
    </form>

</div>

<%
    try {
        ArrayList<Item230> items = (ArrayList<Item230>) request.getAttribute("items");
        ArrayList<ImageItem230> images = (ArrayList<ImageItem230>) request.getAttribute("images");
        session.setAttribute("items", items);
        session.setAttribute("images", images);
        if (items.isEmpty()) {
%>
<div class="container">Không có kết quả nào cho từ khóa trên</div>
<%
} else {
    System.out.println(items.size());
    int numItem = items.size();
%>

<div class="container">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <%
            for (int i = 0; i < numItem; i++) {
                Item230 item = items.get(i);
                ImageItem230 image = images.get(i);
        %>
        <div class="col">
            <div class="card shadow-sm">
                <img class="card-img-top" src="<%=image.getLink()%>" style="width: 100%; height: 225px">
                <div class="card-body">
                    <h5 class="card-title"><%=item.getNameItem()%>
                    </h5>
                    <h6>Giá: <%=item.getExportedPrice()%> VND</h6>

                    <form class="row"
                          action="http://localhost:8080/untitled_war_exploded/addToShoppingCartServlet/<%=i%>"
                          method="post">
                        <div class="row-1 mb-3">
                            <label class="form-label" for="quanity${i}">Số lượng:</label>
                            <input class="form-control" type="number" id="quanity${i}" name="quanity<%=i%>" required>
                        </div>
                        <div class="row-1 text-center">
                            <button type="submit" class="btn btn-success btn-lg">Thêm vào giỏ hàng</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<%
        }
    } catch (Exception e) {
        System.out.println(e);
    }
%>
</body>
</html>
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