<%-- 
    Document   : gdShoppingCart230
    Created on : Oct 6, 2024, 9:41:36 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="java.util.Map"%>
<%@page import="model.ordering.OrderedItem230"%>
<%@page import="model.item.ImageItem230"%>
<%@page import="model.ordering.ShoppingCart230"%>
<%@page import="model.user.Customer230"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đặt hàng</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
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
    %>

    <nav style="background-color: #f1b62d">
        <a id="name" style="background-color: #f1b62d; font-weight: bold;"><%=user.getFullName()%></a>
        <a href="http://localhost:8080/untitled_war_exploded/views/client/gdOrdering230.jsp">Đặt hàng</a>
        <a href="" class="select">Giỏ hàng</a>
    </nav>
        <%
            ShoppingCart230 shoppingCart230 = (ShoppingCart230) session.getAttribute("shoppingCart");
            Map<OrderedItem230, ImageItem230> items = shoppingCart230.getItems();
            
            if (items.isEmpty()) {
        %>
        <div class="position-relative" style="width: 100%; height: 100%; padding: 10%;">
            <div class="position-absolute top-50 start-50 translate-middle">
                <h5>Chưa có mặt hàng nào được thêm vào giỏ hàng</h5>
            </div>
        </div>
        <%
            } else {
        %>
    <div class="container my-3" style="max-width: 100%; max-height: 5%; padding-inline: 10%; padding-top: 2rem;">
        <%
            for (Map.Entry<OrderedItem230, ImageItem230> item : items.entrySet()) {
                    OrderedItem230 orderedItem = item.getKey();
                    ImageItem230 imageItem = item.getValue();
                    
                
        %>
        <div class="row">
            <div class="card mb-3" style="padding: 0;">
              <div class="row g-0 ">
                <div class="col-md-2">
                    <img src="<%=imageItem.getLink()%>" class="img-fluid rounded-start" alt="...">
                </div>
                <div class="col-md-4">
                  <div class="card-body">
                    <h5 class="card-title"><%=orderedItem.getNameItem()%></h5>
                  </div>
                </div>
                <div class="col-md-2">
                  <div class="card-body">
                    <h5 class="card-title">Đơn giá</h5>
                    <h6 class="card-title text-body-secondary"><%=orderedItem.getExportedPrice()%> VND</h6>
                  </div>
                </div>
                <div class="col-md-2">
                  <div class="card-body">
                    <h5 class="card-title">Số lượng</h5>
                    <h6 class="card-title text-body-secondary"><%=orderedItem.getOrderedQuantity()%></h6>
                  </div>
                </div>
                <div class="col-md-2">
                  <div class="card-body">
                    <h5 class="card-title">Thành tiền</h5>
                    <h6 class="card-title text-body-secondary"><%=orderedItem.getTotalPrice()%> VND</h6>
                  </div>
                </div>
              </div>
            </div>
        </div>
        <%
            }
        %>
        <div class="d-flex justify-content-center py-3">
            <button type="submit"  class="btn btn-success btn-lg" onclick="openPage('<%=request.getContextPath()%>/views/client/gdInvoice230.jsp')">Lưu đơn</button>
        </div>
    </div>
        <%
            }
        %>
</body>
</html>
<script>
function openPage(url) {
    window.location.href = url; // Chuyển hướng đến URL
}
</script>

<script>
        // Hiển thị thông báo khi tải trang
        window.onload = function() {
            var notification = document.getElementById('myNotification');
            notification.style.display = 'block';

            // Tắt thông báo sau 3 giây (3000 milliseconds)
            setTimeout(function() {
                notification.style.display = 'none';
            }, 3000);
        };
    </script>