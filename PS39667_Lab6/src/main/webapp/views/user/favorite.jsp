<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
<title>Favorite</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  
</head>

<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:url var="url" value="/detail"></c:url>
    <jsp:include page="header.jsp"></jsp:include>


    <main class="d-flex flex-column min-vh-100">
        <section class="py-5">
            <div class="container mt-5">
                <div class="row g-4">
                    <c:forEach var="item" items="${videos}">
                        <div class="col-6 col-md-3">
                            <div class="card h-100">
                                <a href="${url}/${item.id}" class="text-decoration-none">
                                    <img src="${empty item.poster ? 'https://previews.123rf.com/images/kaymosk/kaymosk1804/kaymosk180400006/100130939-error-404-p%C3%A1gina-no-encontrada-error-con-efecto-de-falla-en-la-pantalla-ilustraci%C3%B3n-vectorial-para-s.jpg':item.poster}"
                                        class="card-img-top" alt="">
                                    <div class="card-body">
                                        <h5 class="card-title">${item.title}</h5>
                                    </div>
                                </a>
                                <div class="card-footer">
                                    <div class="d-grid gap-2">
                                        <a href="RemoveFromFavorites?videoId=${item.id}" class="btn btn-primary">
										    <i class="far fa-thumbs-down"></i> 
										    <span>DISLIKE</span>
										</a>

                                        <a href="ShareVideo?videoId=${item.id}" class="btn btn-secondary">
                                            <i class="fas fa-share-alt"></i>
                                            <span>SHARE</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

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
    <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
