<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <!-- Thêm Font Awesome cho các icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        /* Thiết lập cho footer */
        footer.footer {
            background-color: #333;
            color: white;
            padding: 40px 0;
            text-align: center;
            font-size: 1rem;
        }

        footer .content p {
            margin: 0;
            font-size: 1.2em;
            margin-bottom: 15px;
        }

        footer .content .block {
            margin-top: 20px;
        }

        footer .content .icon {
            margin: 0 10px;
            font-size: 1.5em;
        }

        footer .content .icon:hover {
            color: #007bff;
            transition: color 0.3s ease-in-out;
        }

        footer .content .social-icons {
            display: flex;
            justify-content: center;
            gap: 15px;
        }

        footer .content .social-icons a {
            color: white;
            font-size: 1.8em;
            transition: color 0.3s ease-in-out;
        }

        footer .content .social-icons a:hover {
            color: #007bff; /* Màu khi hover */
        }
    </style>
</head>
<body>
    <!-- Nội dung footer -->
    <footer class="footer">
        <div class="content">
            <p>
                Xem phim mới miễn phí nhanh chất lượng cao. Xem Phim online Việt Sub, Thuyết minh, lồng tiếng chất lượng HD. Xem phim nhanh online chất lượng cao.
            </p>
            <div class="social-icons">
                <a href="https://twitter.com" target="_blank" class="icon">
                    <i class="fab fa-twitter"></i>
                </a>
                <a href="https://youtube.com" target="_blank" class="icon">
                    <i class="fab fa-youtube"></i>
                </a>
                <a href="https://facebook.com" target="_blank" class="icon">
                    <i class="fab fa-facebook"></i>
                </a>
                <a href="https://github.com" target="_blank" class="icon">
                    <i class="fab fa-github"></i>
                </a>
            </div>
        </div>
    </footer>
</body>
</html>
