<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }
       .containerbd{
          background-color: #d8d8d8; /* Thay đổi màu nền */
       }
    
        main {

            min-height: 100vh;
        }

        /* Cải thiện layout cho các video */
        .video-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        /* Cải tiến hình ảnh */
        .video-item img {
            width: 100%;
            height: auto;
            border-radius: 8px;
            margin-bottom: 10px;
        }

        .video-title {
            font-size: 1.2em;
            font-weight: bold;
            text-align: center;
        }

        .video-actions {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }

        /* Thay đổi nút cho các hành động */
        .btn-custom {
            width: 48%;
            text-align: center;
        }

        .pagination {
            justify-content: center;
            font-size: 1em;
        }

        .pagination .page-item {
            margin: 0 5px;
        }

        .pagination .page-link {
            padding: 10px 20px;
            border: 1px solid #007bff;
            color: #007bff;
        }

        .pagination .page-link:hover {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>

<body>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:url var="url" value="/Home/detail"></c:url>
    <c:set value="page" var="homet"></c:set>
    <jsp:include page="header.jsp"></jsp:include>
<div class="containerbd">
    <main class="container py-5 bg-light">
        <!-- Video section -->
        <section class="section">
            <div class="video-container">
                <c:forEach var="video" items="${listVideos}" varStatus="status">
                    <div class="video-item card">
                        <a href="${url}/${video.id}">
                            <img src="${video.poster}"
                                alt="" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title video-title">${video.title}</h5>
                                <br>
                                <a class="text-muted"><i class="bi bi-eye"></i> 
								${video.views}</a>
                            </div>
                        </a>
                            <div class="video-actions d-flex justify-content-between p-3">
                                <a href="${pageContext.request.contextPath}/Home/Like?videoId=${video.id}" class="btn btn-info btn-custom">
                                    <span class="icon">
                                        <i class="far fa-thumbs-up"></i>
                                    </span> 
                                    <span>LIKE</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/ShareVideo?videoId=${item.videoId}" class="btn btn-outline-secondary btn-custom">
                                    <span class="icon">
                                        <i class="fas fa-share-alt"></i>
                                    </span> 
                                    <span>SHARE</span>
                                </a>
                            </div>
                       </div>
                </c:forEach>
            </div>
        </section>

        <!-- Pagination -->
        <section class="section">
            <div class="container">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-left"></i></a></li>
                        <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-chevron-left"></i></a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-chevron-right"></i></a></li>
                        <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-right"></i></a></li>
                    </ul>
                </nav>
            </div>
        </section>
    </main>
</div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
