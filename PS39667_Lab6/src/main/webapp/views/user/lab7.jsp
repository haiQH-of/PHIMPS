<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fetch API Example</title>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
        	fetch('/PS39667_Lab6/employee')
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log("Dữ liệu JSON nhận được:", data);
                })
                .catch(error => {
                    console.error("Lỗi xảy ra:", error);
                });
        });
    </script>
</head>
<body>
    <h1>Fetch API với Servlet</h1>
    <p>Mở console để xem dữ liệu JSON.</p>
</body>
</html>
