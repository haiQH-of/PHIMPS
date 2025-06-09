<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload File with AJAX</title>
    <script>
        function uploadFile() {
            const fileInput = document.getElementById("fileInput");
            const formData = new FormData();
            formData.append("file", fileInput.files[0]);

            fetch('/PS39667_Lab6/upload', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("Thông tin file nhận được:", data);
            })
            .catch(error => {
                console.error("Lỗi xảy ra:", error);
            });
        }
    </script>
</head>
<body>
    <h1>Upload File với AJAX</h1>
    <input type="file" id="fileInput">
    <button onclick="uploadFile()">Upload</button>

    <p>Mở console để xem thông tin file.</p>
</body>
</html>
