package Controlleruser;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import Dao.UserDAO;
import Entity.User;
import Impl.UserDAOImpl;
import Impl.VideoDAOImpl;

@WebServlet({"/account/login", "/account/changepassword", "/account/editprofile", "/account/ForgotPasswordServlet", "/account/RegistrationServlet", "/account/Logoff"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDao = new UserDAOImpl();
    private VideoDAOImpl videoDao = new VideoDAOImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("/login")) {
            dologin(request, response);
        } else if (uri.contains("/changepassword")) {
            dochangepassword(request, response);
        } else if (uri.contains("/editprofile")) {
            doeditprofile(request, response);
        } else if (uri.contains("/ForgotPasswordServlet")) {
            doForgotPassword(request, response);
        } else if (uri.contains("/RegistrationServlet")) {
            doRegister(request, response);
        } else if (uri.contains("/Logoff")) { 
        	doLogoff(request, response); }
    }

    private void dologin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userinfo = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");
        try {
            User user = userDao.findByIdOrEmail(userinfo);
            if (user != null && user.getPassword().equals(password) && user.isAdmin()) {
                HttpSession session = request.getSession();
                session.setAttribute("userIdOrEmail", userinfo);
                session.setAttribute("password", password);
                session.setAttribute("fullname", user.getFullname());
                session.setAttribute("dn", user.getAdmin());
                response.sendRedirect(request.getContextPath() + "/admin/Homeadmin");
                return;
            }
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                ServletContext application = getServletContext();
                session.setAttribute("userIdOrEmail", userinfo);
                session.setAttribute("password", password);
                session.setAttribute("fullname", user.getFullname());
                int visitCount = 1;
                if (application.getAttribute("visitCount") != null) {
                    visitCount = (int) application.getAttribute("visitCount");
                }
                visitCount++;
                application.setAttribute("visitCount", visitCount);
                request.setAttribute("visitCount", visitCount);
                request.getRequestDispatcher("/HomeList").forward(request, response);
            } else {
                request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        }
    }

    private void dochangepassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/account/login");
                return;
            }

            String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            try {
                User user = userDao.findByIdOrEmail(userIdOrEmail);
                if (user != null && user.getPassword().equals(currentPassword)) {
                    if (newPassword.equals(confirmPassword)) {
                        user.setPassword(newPassword);
                        userDao.update(user);
                        request.setAttribute("message", "Mật khẩu đã được thay đổi thành công.");
                    } else {
                        request.setAttribute("error", "Mật khẩu mới không khớp.");
                    }
                } else {
                    request.setAttribute("error", "Mật khẩu hiện tại không đúng.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("/views/user/changepassword.jsp").forward(request, response);
    }

    private void doeditprofile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/account/login");
            return;
        }

        String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");
        try {
            User user = userDao.findByIdOrEmail(userIdOrEmail);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/account/login");
                return;
            }
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");

                user.setFullname(fullname);
                user.setEmail(email);
                userDao.update(user);
                session.setAttribute("fullname", fullname);
                request.setAttribute("message", "Thông tin cá nhân đã được cập nhật.");
            }
            request.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        request.getRequestDispatcher("/views/user/editprofile.jsp").forward(request, response);
    }

    private void doForgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        try {
            User user = userDao.findByIdOrEmail(email);
            if (user != null) {
                // Gửi email đặt lại mật khẩu cho người dùng
                request.setAttribute("message", "Một email đặt lại mật khẩu đã được gửi đến địa chỉ email của bạn.");
            } else {
                request.setAttribute("error", "Không tìm thấy người dùng với địa chỉ email này.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        request.getRequestDispatcher("/views/user/forgotpassword.jsp").forward(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Tạo người dùng mới
            User newUser = new User();
            newUser.setId(username); // Sử dụng username làm ID
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFullname(request.getParameter("fullname"));
            newUser.setAdmin(false);
            userDao.create(newUser);

            // Gửi email thông báo đăng ký thành công
            String host = "smtp.gmail.com";
            final String user = "haingclone@gmail.com"; // Email gửi
            final String pass = "wmjqyprqanvweeob"; // Mật khẩu email gửi

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Xác nhận đăng ký");
                message.setText("Xin chào " + username + ",\n\nCảm ơn bạn đã đăng ký tài khoản trên hệ thống của chúng tôi.\n\nTrân trọng,\nNhóm Hỗ trợ");

                Transport.send(message);

                request.setAttribute("message", "Đăng ký thành công! Vui lòng kiểm tra email để xác nhận.");
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("error", "Đăng ký thành công nhưng không thể gửi email xác nhận.");
            }

            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/views/user/registration.jsp").forward(request, response);
        }
    }


    private void doLogoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session != null) { session.invalidate(); 
    	} response.sendRedirect(request.getContextPath() + "/account/login"); 
    	
    }
}
