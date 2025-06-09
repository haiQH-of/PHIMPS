	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of Users</title>
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
                <h2>LIST USERS</h2>
            </div>
            
            <div>
                <h2>${message}</h2>
            </div>
            
            <div>
                <a href="user/add" class="btn btn-primary mb-3">
                    <i class="fas fa-plus"></i> Add New User
                </a>
            </div>

            <table class="table table-striped border border-dark">
                <thead class="thead-dark font-weight-bold bg-dark text-white">
                    <tr>
                        <td>No.</td>
                        <td>User ID</td>
                        <td>Full Name</td>
                        <td>Email</td>
                        <td>Admin</td>
                        <td>Actions</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUsers}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${user.id}</td>
                            <td>${user.fullname}</td>
                            <td>${user.email}</td>
                            <td>
                                <span class="badge ${user.admin ? 'badge-success' : 'badge-danger'}">
                                    ${user.admin ? 'Admin' : 'User'}
                                </span>
                            </td>
                            <td>
                                <a href="user/edit?id=${user.id}" class="btn btn-sm btn-warning">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <a href="user/delete?id=${user.id}" class="btn btn-sm btn-danger ml-2"
                                   onclick="return confirm('Are you sure you want to delete this user?')">
                                    <i class="fas fa-trash"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
