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
		a {
			  text-decoration: none !important;
			  color: #00ac81 !important;

			}
        main {
            background-color: #f8f9fa; /* Thay đổi màu nền */
            min-height: 100vh;
        }

        .video-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }

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
        .card{
        width: 1140px;
        }
        .list-group-item{
        right: 100px;
        width: 320px;
        }
    </style>
</head>

<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:url var="url" value="/HomeLike"></c:url>
    <c:set value="page" var="home"></c:set>
    <jsp:include page="header.jsp"></jsp:include>

    <main>
        <div class="container-fluid min-vh-100">
            <section class="py-5 mt-5">
                <div class="row justify-content-center">
                    <div class="col-10">
                        <form action="" method="post">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <!-- Thay đổi code để nhúng video từ YouTube -->
                                    <iframe width="100%" height="700" 
									        src="https://www.youtube.com/embed/${video.code}" 
									        frameborder="0" 
									        allow="autoplay; picture-in-picture" 
									        allowfullscreen>
									</iframe>


                                    <h4 class="h5 mt-3">${video.title}</h4>

                                    <div class="row mt-3">
                                        <div class="col">
                                            <p class="mb-0"><i class="bi bi-eye"></i>
												 ${video.views}</p>
                                        </div>

                                        <div class="video-actions d-flex justify-content-between p-3">
                                            <a href="${pageContext.request.contextPath}/HomeLike/${video.id}"
                                               class="btn btn-info btn-custom">
                                                <span class="icon">
                                                    <i class="far fa-thumbs-up"></i>
                                                </span>
                                                <span>LIKE</span>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/Home/share/${video.id}"
                                               class="btn btn-outline-secondary btn-custom">
                                                <span class="icon">
                                                    <i class="fas fa-share-alt"></i>
                                                </span>
                                                <span>SHARE</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </form>
                    </div>

                    <div class="col-md-2">
                        <h5>Related Videos</h5>
                        <div class="list-group">
                            <c:forEach var="item" items="${videos}">
                                <div class="list-group-item">
                                    <a href="${pageContext.request.contextPath}/Homedetail/${item.id}" class="d-flex align-items-center">
                                        <img src="${empty item.poster ? 'https://previews.123rf.com/images/kaymosk/kaymosk1804/kaymosk180400006/100130939-error-404-p%C3%A1gina-no-encontrada-error-con-efecto-de-falla-en-la-pantalla-ilustraci%C3%B3n-vectorial-para-s.jpg':item.poster}" 
                                             alt="${item.title}" class="img-thumbnail" style="width: 80px; height: 80px; margin-right: 10px;">
                                        <div>
                                            <h6 class="mb-1">${item.title}</h6>
                                            <p class="mb-0"><i class="bi bi-eye"></i> ${item.views}</p>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>

    <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
