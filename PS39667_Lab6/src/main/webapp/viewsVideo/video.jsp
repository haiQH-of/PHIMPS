<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <title>Danh Sách Video</title>
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
                <h2>DANH SÁCH VIDEO</h2>
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
                    <form action="video" method="post">
                        <div class="form-group">
                            <label>Mã Video:</label> 
                            <input type="text" class="form-control" name="id" value="${video.id}" required />
                        </div>
                        <div class="form-group">
                            <label>Tiêu Đề:</label> 
                            <input type="text" class="form-control" name="title" value="${video.title}" required />
                        </div>
                        <div class="form-group">
                            <label>Lượt Xem:</label> 
                            <input type="number" class="form-control" name="views" value="${video.views}" required />
                        </div>
                        <div class="form-group">
                            <label>Mô Tả:</label> 
                            <input type="text" class="form-control" name="description" value="${video.description}" required />
                        </div>
                        <div class="form-check form-check-inline">
                            <label>Trạng Thái Kích Hoạt:&nbsp;&nbsp;&nbsp;</label>
                            <label class="ml-2">
                                <input type="radio" class="form-check-input" name="active" value="true" ${video.active ? "checked" : ""}/> Kích Hoạt
                            </label> 
                            <label class="ml-2">
                                <input type="radio" class="form-check-input" name="active" value="false" ${!video.active ? "checked" : ""}/> Không Kích Hoạt
                            </label>
                        </div>
                        <div class="form-group pt-2">
                            <button class="btn btn-primary" formaction="video/create">Thêm Mới</button>
                            <button class="btn btn-warning" formaction="video/update">Cập Nhật</button>
                            <button class="btn btn-danger" formaction="video/delete" disabled="disabled">Xóa</button>
                            <button class="btn btn-info" formaction="video/reset">Làm Mới</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row pt-0 pl-3 pr-3">
                <table class="table border border-dark">
                    <thead class="thead-dark font-weight-bold bg-dark text-white">
                        <tr>
                            <td>Mã Video</td>
                            <td>Tiêu Đề</td>
                            <td>Lượt Xem</td>
                            <td>Trạng Thái</td>
                            <td>&nbsp;</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${videos}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.title}</td>
                                <td>${item.views}</td>
                                <td>${item.active ? 'Kích Hoạt' : 'Không Kích Hoạt'}</td>
                                <td>
                                    <a href="video/edit?id=${item.id}">Chỉnh Sửa</a> | 
                                    <a href="video/delete?id=${item.id}">Xóa</a>
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
