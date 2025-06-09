<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <script src="https://kit.fontawesome.com/e136359f35.js" crossorigin="anonymous"></script>
    <title>Đăng Ký</title>
    <link rel="icon" href="https://tinyzonetv.to/images/group_2/theme_1/logo.png?v=0.1" type="image/x-icon">
    <style>
        .notification {
            display: none; /* Ẩn thông báo mặc định */
        }
    </style>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="header.jsp"></jsp:include>

    <section class="hero is-primary is-fullheight-with-navbar">
        <div class="hero-body">
            <div class="container">
                <div class="columns is-centered">
                    <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                        <div class="box">
                            <h1 class="title has-text-centered has-text-dark">Đăng Ký</h1>
                            
                            <c:if test="${not empty message}">
                                <div class="notification is-success">
                                    <button class="delete"></button>
                                    ${message}
                                </div>
                            </c:if>
                            <c:if test="${not empty error}">
                                <div class="notification is-danger">
                                    <button class="delete"></button>
                                    ${error}
                                </div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/account/RegistrationServlet" method="post">
                                <div class="field">
                                    <label class="label">Tên Đăng Nhập</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="text" name="username" placeholder="Nhập tên đăng nhập" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-user"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Email</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="email" name="email" placeholder="Nhập email" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-envelope"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Mật Khẩu</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="password" name="password" placeholder="Nhập mật khẩu" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-lock"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Họ và Tên</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="text" name="fullname" placeholder="Nhập họ và tên" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-user-tag"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <button class="button is-primary is-fullwidth" type="submit">
                                        <span class="icon"><i class="fas fa-user-plus"></i></span>
                                        <span>Đăng Ký</span>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"></jsp:include>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Hiển thị thông báo nếu có lỗi hoặc thành công
            const errorNotification = document.querySelector('.notification.is-danger');
            if (errorNotification && errorNotification.innerText.trim() !== '') {
                errorNotification.style.display = 'block';
            }

            const successNotification = document.querySelector('.notification.is-success');
            if (successNotification && successNotification.innerText.trim() !== '') {
                successNotification.style.display = 'block';
            }
        });
    </script>
</body>
</html>
