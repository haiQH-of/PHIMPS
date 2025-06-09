<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details</title>
</head>
<body>
    <h1>Employee Details</h1>
    <div id="employee-data"></div>

    <script>
        // Gửi yêu cầu GET tới servlet
        fetch('/PS39667_Lab6//employee')
        .then(response => {
            console.log("API Response Status:", response.status); // Kiểm tra mã trạng thái
            return response.json();
        })
        .then(data => {
            console.log("API Data:", data); // In dữ liệu ra console
            // Sau đó render giao diện
            renderData(data);
        })
        .catch(error => {
            console.error("Error fetching data:", error); // Ghi log lỗi nếu xảy ra
        });
    </script>
</body>
</html>
