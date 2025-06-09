<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <title>Thêm Video Mới</title>
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
                <h2>Thêm Video Mới</h2>
            </div>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>

            <form action="video/add" method="POST">
                <div class="form-group">
                    <label><i class="fas fa-video"></i></label> 
                    <input type="text" name="id" placeholder="Mã video" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-heading"></i></label> 
                    <input type="text" name="title" placeholder="Tiêu đề video" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-image"></i></label> 
                    <input type="text" name="poster" placeholder="URL ảnh đại diện" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-eye"></i></label> 
                    <input type="number" name="views" placeholder="Lượt xem" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-align-left"></i></label> 
                    <textarea name="description" placeholder="Mô tả video" class="form-control" required></textarea>
                </div>
                <div class="form-check form-check-inline">
                    <label class="ml-2">
                        <input type="radio" name="active" value="1" /> Kích hoạt 
                        <input type="radio" name="active" value="0" checked /> Không kích hoạt
                    </label>
                </div>
                <div class="form-group form-button mt-3">
                    <input type="submit" name="addVideoButton" class="btn btn-primary" value="Thêm Video" />
                </div>
            </form>
            <a href="videoList">Danh sách video</a>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
