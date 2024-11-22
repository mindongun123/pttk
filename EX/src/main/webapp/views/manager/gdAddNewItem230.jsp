<%-- 
    Document   : gdAddNewItem230
    Created on : Oct 6, 2024, 9:39:53 AM
    Created by IntelliJ IDEA.
    User: min
--%>

<%@page import="dao.Type230DAO" %>
<%@page import="model.item.Type230" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.user.Manager230" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm mặt hàng mới</title>
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

    <a href="http://localhost:8080/untitled_war_exploded/views/manager/gdItemManagement230.jsp" class="select">Quản lý
        mặt
        hàng</a>
</nav>

<%
    ArrayList<Type230> allType = (new Type230DAO()).getAllType();
%>

<div class="container text-center my-3">
    <h3>Thông tin mặt hàng</h3>
</div>
<div class="container">
    <form action="http://localhost:8080/untitled_war_exploded/doAddNewItem
" method="POST" enctype="multipart/form-data">
        <div class="row">
                <div class="text-center mb-3">
                    <button id="addImg" type="button" class="btn btn-primary btn-lg" onclick="addImageInput()">+ Thêm
                        ảnh
                    </button>

                </div>
                <div id="imageContainer" class="d-flex flex-column align-items-center"></div>
                <div class="mb-3">
                    <label for="nameItem" class="form-label">Tên mặt hàng</label>
                    <input type="text" class="form-control" id="nameItem" name="nameItem" required>

                    <div>
                        <label for="type" class="form-label">Loại mặt hàng</label>
                        <select class="form-select" aria-label="Default select example" id="type" name="type"
                                required>
                            <option selected>Select</option>
                            <%
                                System.out.println(allType.toArray().length);
                                for (Type230 type : allType) {
                            %>
                            <option value="<%=type.getId()%>"><%=type.getName()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div>
                        <label for="brand" class="form-label">Nhãn hiệu</label>
                        <input type="text" class="form-control" id="brand" name="brand" required>
                    </div>

                    <div>
                        <label for="exportedPrice" class="form-label">Giá bán (VND)</label>
                        <input type="number" class="form-control" id="exportedPrice" name="exportedPrice" required>
                    </div>
                    <div >
                        <label for="unit" class="form-label">Đơn vị mặt hàng</label>
                        <select class="form-select" aria-label="Default select example" id="unit" name="unit"
                                required>
                            <option selected>Select</option>
                            <option value="Cái">Cái</option>
                            <option value="Chiếc">Chiếc</option>
                            <option value="Hộp">Hộp</option>
                            <option value="Gói">Gói</option>
                            <option value="Thùng">Thùng</option>
                            <option value="Túi">Túi</option>
                            <option value="Bao">Bao</option>
                            <option value="Cây">Cây</option>
                            <option value="Chai">Chai</option>
                            <option value="Lọ">Lọ</option>
                        </select>
                    </div>


                    <div class="row my-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Các thông tin khác của sản phẩm</h5>
                                <table class="table table-striped table-bordered" id="tableAddInfo">
                                    <thead>
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Tên thông tin</th>
                                        <th scope="col">Mô tả</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-group-divider">

                                    </tbody>
                                </table>
                                <div class="d-grid gap-2">
                                    <button id="addRowInfo" type="button" class="add-row-btn" onclick="addRow()">+
                                    </button>
                                </div>

                            </div>

                        </div>
                    </div>
            </div>

        </div>

        <div class="d-flex justify-content-center py-3">
            <button type="submit" class="btn btn-success btn-lg mx-1">Lưu mặt hàng</button>
            <button type="button" class="btn btn-outline-secondary btn-lg mx-1"
                    onclick="openPage('<%=request.getContextPath()%>/views/manager/gdItemManagement230.jsp')">Hủy
            </button>
        </div>
    </form>
</div>

<script>
    function addRow() {
        // Lấy bảng và tbody
        var table = document.getElementById("tableAddInfo").getElementsByTagName('tbody')[0];

        // Tạo một hàng mới
        var newRow = table.insertRow();

        // Tạo các ô mới cho hàng
        var cell1 = newRow.insertCell(0);
        var cell2 = newRow.insertCell(1);
        var cell3 = newRow.insertCell(2);

        // Lấy số thứ tự hiện tại của hàng mới
        var rowCount = table.rows.length;

        // Gán dữ liệu cho các ô
        cell1.innerHTML = rowCount;
        cell2.innerHTML = "<input type='text' class='form-control' placeholder='Nhập tên thông tin' id='nameInfo" + rowCount + "' name='nameInfo" + rowCount + "'>";
        cell3.innerHTML = "<input type='text' class='form-control' placeholder='Nhập mô tả thông tin' id='describeInfo" + rowCount + "' name='describeInfo" + rowCount + "'>";
    }

    let imageCount = 0;

    function addImageInput() {
        // Tạo một thẻ div để chứa ảnh và input
        var div = document.createElement("div");
        div.classList.add("mb-3");

        imageCount++;
        // Tạo một thẻ input loại file
        var input = document.createElement("input");
        input.type = "file";
        input.accept = "image/*";
        input.name = "image" + imageCount;
        input.classList.add("form-control");

        // Khi người dùng chọn tệp, hiển thị ảnh đã chọn
        input.onchange = function (event) {
            var file = event.target.files[0];
            if (file) {
                // Tạo thẻ img để hiển thị ảnh
                var img = document.createElement("img");
                img.src = URL.createObjectURL(file);
                console.log(URL.createObjectURL(file));
                img.style.maxWidth = "200px";
                img.style.marginTop = "10px";

                // Thêm thẻ img vào div
                div.appendChild(img);
            }
        };

        // Thêm input vào div
        div.appendChild(input);

        // Thêm div vào vùng chứa ảnh
        document.getElementById("imageContainer").appendChild(div);
    }

    function openPage(url) {
        window.location.href = url; // Chuyển hướng đến URL
    }
</script>
</body>
