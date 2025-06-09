<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <title>Thêm Người Dùng Mới</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="/PS39667_Lab3_NguyenQuocHai/">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body class="bg-info">
    <div class="container col-8 pt-2">
        <div class="border rounded bg-light pl-4 pr-4 p-3">
            <div class="row p-2 pl-3 pt-3 bg-danger text-white rounded">
                <h2>Thêm Người Dùng Mới</h2>
            </div>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>

            <form action="user/add" method="POST">
                <div class="form-group">
                    <label><i class="fas fa-id-card"></i></label> 
                    <input type="text" name="id" placeholder="Mã người dùng" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-user"></i></label> 
                    <input type="text" name="fullname" placeholder="Tên người dùng" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-envelope"></i></label> 
                    <input type="email" name="email" placeholder="Email người dùng" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-lock"></i></label> 
                    <input type="password" name="password" placeholder="Mật khẩu" class="form-control" required />
                </div>
                <div class="form-check form-check-inline">
                    <label class="ml-2">
                        <input type="radio" name="admin" value="1" /> Quản trị viên 
                        <input type="radio" name="admin" value="0" checked /> Người dùng bình thường
                    </label>
                </div>
                <div class="form-group form-button mt-3">
                    <input type="submit" name="addUserButton" class="btn btn-primary" value="Thêm Người Dùng" />
                </div>
            </form>
            <a href="userList">Danh sách người dùng</a>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
