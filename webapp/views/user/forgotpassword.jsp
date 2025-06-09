<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
<script src="https://kit.fontawesome.com/e136359f35.js" crossorigin="anonymous"></script>
<title>Phimkhongmoi</title>
<link rel="icon" href="https://tinyzonetv.to/images/group_2/theme_1/logo.png?v=0.1" type="image/x-icon">
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:include page="header.jsp"></jsp:include>

	
    <main class="d-flex min-vh-100 py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-6 col-lg-5">
                    <h1 class="text-center page-title">Quên Mật Khẩu</h1>
                    <div class="form-container">
                        <form action="ForgotPassword" method="post" id="form-login">
                            <!-- Username Field -->
                            <div class="form-floating mb-4">
                           
                                <input type="text" class="form-control" id="userId" name="userId" 
                                    placeholder="Tên đăng nhập">
                                <label for="userId" class="mx-4">Tên đăng nhập</label>
                            </div>

                            <!-- Email Field -->
                            <div class="form-floating mb-4">
                              
                                <input type="email" class="form-control" id="email" name="email" 
                                    placeholder="Email" required>
                                <label for="email" class="mx-4">Email</label>
                            </div>

                            <div class="divider"></div>

                            <!-- Submit Button -->
                            <button class="btn btn-primary w-100 btn-retrieve" type="submit" name="btnRetrieve">
                                <i class="fas fa-key me-2"></i>Khôi phục mật khẩu
                            </button>

                            <!-- Messages -->
                            <c:if test="${not empty message}">
                                <div class="error-message">
                                    <i class="fas fa-info-circle me-2"></i>${message}
                                </div>
                            </c:if>
                            <c:if test="${not empty error}">
                                <div class="error-message">
                                    <i class="fas fa-exclamation-circle me-2"></i>${error}
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>

	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>