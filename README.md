# PHIMPS – Ứng dụng quản lý và chia sẻ video

## 📌 Giới thiệu
**PHIMPS** là một ứng dụng web được xây dựng bằng Java Servlet theo mô hình MVC. Ứng dụng cho phép người dùng xem, yêu thích, chia sẻ video và quản lý hồ sơ cá nhân. Admin có thể theo dõi hành vi người dùng, thống kê lượt yêu thích và chia sẻ video.

## 🚀 Tính năng chính
### Người dùng:
- Đăng ký, đăng nhập, đăng xuất
- Xem danh sách và chi tiết video
- Yêu thích và chia sẻ video
- Đổi mật khẩu, cập nhật thông tin cá nhân
- Quên mật khẩu (gửi email)

### Quản trị viên:
- Đăng nhập trang quản trị
- Xem báo cáo thống kê yêu thích, chia sẻ video
- Đăng xuất

## 🛠️ Công nghệ sử dụng
- **Backend**: Java Servlet, JSP, Hibernate (JPA), Jakarta EE, JavaMail
- **Frontend**: HTML, CSS, Bootstrap, JavaScript, JSTL
- **Cơ sở dữ liệu**: SQL Server
- **Khác**: Maven, Lombok, Jackson, Apache BeanUtils

## 📁 Cấu trúc dự án
src/
├── controller/ # Xử lý logic chung
├── controlleradmin/ # Chức năng dành cho admin
├── controlleruser/ # Chức năng dành cho người dùng
├── dao/ # Giao diện DAO
├── impl/ # Cài đặt DAO bằng Hibernate
├── entity/ # Entity ánh xạ cơ sở dữ liệu
├── filter/ # Các bộ lọc xác thực & xử lý
└── META-INF/persistence.xml # Cấu hình JPA
