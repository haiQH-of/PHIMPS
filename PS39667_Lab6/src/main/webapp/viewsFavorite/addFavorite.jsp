<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <title>Thêm Video Yêu Thích</title>
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
                <h2>Thêm Video Yêu Thích</h2>
            </div>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>

            <form action="favorite/add" method="POST">
                <div class="form-group">
                    <label><i class="fas fa-user"></i> Người dùng</label>
                    <select name="userid" class="form-control" required>
                        <c:forEach var="user" items="${users}">
                            <option value="${user.id}">${user.fullname}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-video"></i> Video</label>
                    <select name="videoid" class="form-control" required>
                        <c:forEach var="video" items="${videos}">
                            <option value="${video.id}">${video.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label><i class="fas fa-calendar-alt"></i> Ngày Thích</label>
                    <input type="date" name="likedate" class="form-control" required />
                </div>

                <div class="form-group form-button mt-3">
                    <input type="submit" name="addFavoriteButton" class="btn btn-primary" value="Thêm Video Yêu Thích" />
                </div>
            </form>
            <a href="favoriteList">Danh sách video yêu thích</a>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
