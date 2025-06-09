<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Videos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 10px;
            width: 60%;
            margin-right: 10px;
            font-size: 16px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .no-results {
            text-align: center;
            color: red;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Videos</h1>
        <form action="searchVideos" method="get">
            <input type="text" name="keyword" placeholder="Từ khóa" required />
            <button type="submit">Search</button>
        </form>

        <c:if test="${not empty videos}">
            <h2>Kết quả:</h2>
            <table>
                <thead>
                    <tr>
                        <th>Tiêu đề video</th>
                        <th>Số lượt thích</th>
                        <th>Còn hiệu lực</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="video" items="${videos}">
                        <tr>
                            <td>${video.title}</td>
                            <td>${video.views}</td>
                            <td>${video.active ? 'Yes' : 'No'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty videos}">
            <p class="no-results">No results found.</p>
        </c:if>
    </div>
</body>
</html>
