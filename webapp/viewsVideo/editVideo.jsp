<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <title>Update Video</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="/PS39667_Lab6/">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body class="bg-info">
    <div class="container col-8 pt-2">
        <div class="border rounded bg-light pl-4 pr-4 p-3">
            <div class="row p-2 pl-3 pt-3 bg-danger text-white rounded">
                <h2>Cập nhật video</h2>
            </div>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>

            <form action="video/edit" method="POST">
                <div class="form-group">
                    <label><i class="fas fa-video"></i></label>
                    <input type="hidden" name="id" value="${video.id}" /> 
                    <p>Update Video ID: ${video.id}</p>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-heading"></i></label> 
                    <input type="text" name="title" placeholder="Tiêu đề video" value="${video.title}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-eye"></i></label> 
                    <input type="number" name="views" placeholder="Lượt xem" value="${video.views}" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><i class="fas fa-pen"></i></label>
                    <input type="text" name="description" placeholder="Mô tả" value="${video.description}" class="form-control" />
                </div>
                <div class="form-check form-check-inline">
                    <label class="ml-2">
                        <input type="radio" name="active" value="true" ${video.active ? 'checked' : ''} /> Kích hoạt
                        <input type="radio" name="active" value="false" ${!video.active ? 'checked' : ''} /> Không kích hoạt
                    </label>
                </div>
                <div class="form-group form-button mt-3">
                    <input type="submit" name="updateVideoButton" class="btn btn-primary" value="Cập nhật video" />
                </div>
            </form>

            <a href="videoList" class="btn btn-info">Danh sách video</a>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
