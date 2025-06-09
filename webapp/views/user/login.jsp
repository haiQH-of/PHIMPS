<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <script src="https://kit.fontawesome.com/e136359f35.js" crossorigin="anonymous"></script>
    <title>Đăng Nhập</title>
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

    <section class="hero is-primary is-fullheight-with-navbar">
        <div class="hero-body">
            <div class="container">
                <div class="columns is-centered">
                    <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                        <div class="box">
                            <h1 class="title has-text-centered has-text-dark">Đăng Nhập</h1>

                            <c:if test="${not empty error}">
                                <div class="notification is-danger">
                                    <button class="delete"></button>
                                    ${error}
                                </div>
                            </c:if>

                            <c:if test="${not empty success}">
                                <div class="notification is-success">
                                    <button class="delete"></button>
                                    ${success}
                                </div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/account/login" method="post">
                                <div class="field">
                                    <label class="label">Tên đăng nhập hoặc Email</label>
                                    <div class="control has-icons-left">
                                        <input class="input" type="text" 
                                               name="usernameOrEmail" 
                                               placeholder="Nhập tên đăng nhập hoặc email" 
                                               required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-user"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <label class="label">Mật khẩu</label>
                                    <div class="control has-icons-left has-icons-right">
                                        <input class="input" 
                                               type="password" 
                                               name="password" 
                                               placeholder="Nhập mật khẩu" 
                                               required>
                                        <span class="icon is-small is-left">
                                            <i class="fas fa-lock"></i>
                                        </span>
                                    </div>
                                </div>

                                <div class="field">
                                    <div class="control">
                                        <label class="checkbox">
                                            <input type="checkbox">
                                            Ghi nhớ đăng nhập
                                        </label>
                                    </div>
                                </div>
 
                                <div class="field">
                                    <button class="button is-primary is-fullwidth" type="submit">
                                        <span class="icon"><i class="fas fa-sign-in-alt"></i></span>
                                        <span>Đăng Nhập</span>
                                    </button>
                                </div>

                                <div class="field has-text-centered">
                                    <a href="${pageContext.request.contextPath}/forgot-password" class="is-size-7">Quên mật khẩu?</a>
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
                const username = document.querySelector('input[name="usernameOrEmail"]');
                const password = document.querySelector('input[name="password"]');
                
                if (username.value.trim() === '' || password.value.trim() === '') {
                    e.preventDefault();
                    alert('Vui lòng nhập đầy đủ thông tin');
                }
            });

            // Hiển thị thông báo nếu có lỗi hoặc thành công
            if (${not empty error}) {
                document.querySelector('.notification.is-danger').style.display = 'block';
            }

            if (${not empty success}) {
                document.querySelector('.notification.is-success').style.display = 'block';
            }
        });
    </script>
</body>
</html>
