<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="/PS39667_Lab6/">
<!-- Thêm các link để sử dụng Bootstrap và Bootstrap Icons cho giao diện -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">

<style>
    /* Đảm bảo rằng header có đường gạch ngang màu #007a7e */
		header {
		    border-bottom: 3px solid #007a7e; /* Đặt đường gạch ngang màu #007a7e */
		}
		
		/* Đảm bảo các liên kết trong navbar có màu chữ #007a7e */
		.navbar-nav .nav-link {
		    color: #007a7e !important; /* Màu chữ của các liên kết */
		}
		
		.navbar-nav .nav-link:hover {
		    color: #ffffff !important; /* Màu chữ khi hover (để không bị trùng với nền) */
		}
		
		.navbar-brand h4 {
		    color: #007a7e !important; /* Màu chữ cho phần tiêu đề trong navbar */
		}
		
		.navbar-toggler-icon {
		    background-color: #007a7e !important; /* Thay đổi màu của icon burger nếu cần */
		}
		
		.navbar-dark .navbar-nav .nav-link {
		    color: #007a7e !important; /* Nếu navbar có nền tối, thay đổi màu liên kết */
		}
		
		.navbar-dark .navbar-nav .nav-link:hover {
		    color: #ffc400 !important; /* Thay đổi màu khi hover */
		}


</style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                
                   <h2>NJQI BLOG</h2>
              
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/Favorite" class="nav-link">Trang Yêu Thích</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="Home" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Tài Khoản
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:if test="${!isLogin}">
                                <li><a href="${pageContext.request.contextPath}/account/login" class="dropdown-item">Đăng nhập</a></li>
                                <li><a href="${pageContext.request.contextPath}/ForgotPassword" class="dropdown-item">Quên mật khẩu</a></li>
                                <li><a href="${pageContext.request.contextPath}/account/RegistrationServlet" class="dropdown-item">Đăng kí</a></li>
                               <li><a href="${pageContext.request.contextPath}/HomeList" class="dropdown-item">Trang Chủ</a></li>
                                
                            </c:if>
                            <c:if test="${isLogin}">
                                <li><a href="${pageContext.request.contextPath}/account/Logoff" class="dropdown-item">Đăng xuất</a></li>
                                <li><a href="${pageContext.request.contextPath}/account/changepassword" class="dropdown-item">Đỗi Mật Khẩu</a></li>
                                <li><a href="${pageContext.request.contextPath}/account/editprofile" class="dropdown-item">Chỉnh sửa hồ sơ</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Thêm các script của Bootstrap để navbar hoạt động -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
