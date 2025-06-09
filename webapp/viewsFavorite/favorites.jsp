<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <title>Danh Sách Yêu Thích</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href=/PS39667_Lab3_NguyenQuocHai/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body class="bg-info">
    <div class="container col-8 pt-2">
        <div class="border rounded bg-light pl-4 pr-4 p-3">
            <div class="row p-2 pl-3 pt-3 bg-danger text-white rounded">
                <h2>DANH SÁCH YÊU THÍCH</h2>
            </div>
            <div class="row p-2">
                <div class="col">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <form action="favorite" method="post">
                        <div class="form-group">
                            <label>Mã Yêu Thích:</label> 
                            <input type="text" class="form-control" name="id" value="${favorite.id}" required />
                        </div>
                        <div class="form-group">
                            <label>Người Dùng:</label> 
                            <input type="text" class="form-control" name="username" value="${favorite.user.fullname}" readonly />
                        </div>
                        <div class="form-group">
                            <label>Tiêu Đề Video:</label> 
                            <input type="text" class="form-control" name="videoTitle" value="${favorite.video.title}" readonly />
                        </div>
                        <div class="form-group">
                            <label>Ngày Yêu Thích:</label> 
                            <input type="text" class="form-control" name="likeDate" value="${favorite.likedate}" readonly />
                        </div>
                        <div class="form-group pt-2">
                            <button class="btn btn-primary" formaction="favorite/create">Thêm Mới</button>
                            <button class="btn btn-warning" formaction="favorite/update">Cập Nhật</button>
                            <button class="btn btn-danger" formaction="favorite/delete" disabled="disabled">Xóa</button>
                            <button class="btn btn-info" formaction="favorite/reset">Làm Mới</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row pt-0 pl-3 pr-3">
                <table class="table border border-dark">
                    <thead class="thead-dark font-weight-bold bg-dark text-white">
                        <tr>
                            <td>Mã Yêu Thích</td>
                            <td>Người Dùng</td>
                            <td>Tiêu Đề Video</td>
                            <td>Ngày Yêu Thích</td>
                            <td>&nbsp;</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${favorites}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.user.fullname}</td>
                                <td>${item.video.title}</td>
                                <td>${item.likedate}</td>
                                <td>
                                    <a href="favorite/edit?id=${item.id}">Chỉnh Sửa</a> | 
                                    <a href="favorite/delete?id=${item.id}">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
