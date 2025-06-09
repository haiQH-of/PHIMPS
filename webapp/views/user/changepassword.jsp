<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <script src="https://kit.fontawesome.com/e136359f35.js" crossorigin="anonymous"></script>
    <title>Đổi Mật Khẩu</title>
    <link rel="icon" href="path/to/local/favicon.png" type="image/x-icon">
    <style>
        .notification {
            display: none; /* Ẩn thông báo mặc định */
        }
    </style>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <jsp:include page="header.jsp"></jsp:include>

    <section class="hero is-warning is-fullheight-with-navbar">
        <div class="hero-body">
            <div class="container">
                <div class="columns is-centered">
                    <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                        <div class="box">
                            <h1 class="title has-text-centered has-text-dark">Đổi Mật Khẩu</h1>
                            
                            <c:if test="${not empty error}">
                                <div class="notification is-danger">
                                    <button class="delete"></button>
                                    ${error}
                                </div>
                            </c:if>
                            
                            <c:if test="${not empty message}">
                                <div class="notification is-success">
                                    <button class="delete"></button>
                                    ${message}
                                </div>
                            </c:if>

                            <form action="account/changepassword" method="post">
                                <div class="field">
                                    <label class="label">Mật khẩu hiện tại</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="password" name="currentPassword" placeholder="Nhập mật khẩu hiện tại" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-lock"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Mật khẩu mới</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="password" name="newPassword" placeholder="Nhập mật khẩu mới" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-lock"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Xác nhận mật khẩu mới</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu mới" required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-lock"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <button class="button is-warning is-fullwidth" type="submit">
                                        <span class="icon"><i class="fas fa-key"></i></span>
                                        <span>Đổi Mật Khẩu</span>
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
            const form = document.querySelector('form');
            form.addEventListener('submit', function(e) {
                const currentPassword = document.querySelector('input[name="currentPassword"]');
                const newPassword = document.querySelector('input[name="newPassword"]');
                const confirmPassword = document.querySelector('input[name="confirmPassword"]');
                
                if (currentPassword.value.trim() === '' || newPassword.value.trim() === '' || confirmPassword.value.trim() === '') {
                    e.preventDefault();
                    alert('Vui lòng nhập đầy đủ thông tin');
                }
            });

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
