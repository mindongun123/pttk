<%-- 
    Document   : index
    Created on : Oct 5, 2024, 10:47:29 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<body style="background-color: #fffffa;">
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

<div class="position-absolute top-50 start-50 translate-middle px-5 py-4"
     style="background-color: #6df879; border-radius: 20px; min-width: 600px; min-height: 250px">
    <form action="<%= request.getContextPath() %>/doDangNhap.jsp" method="POST">
        <label class="form-label" for="email">Email: </label>
        <input class="form-control" id="email" type="text" name="email"><br>
        <label class="form-label" for="password">Password: </label>
        <input class="form-control" id="password" type="password" name="password">

        <div class="d-flex justify-content-center py-3">
            <button type="submit" class="btn btn-dark btn-lg">Login</button>
        </div>
    </form>
</div>

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

</body>

