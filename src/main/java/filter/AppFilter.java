package filter;

import java.io.IOException;

import Dao.LogDAO;
import Entity.Log;
import Impl.LogDAOImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AppFilter implements Filter {
    private LogDAO logDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logDAO = new LogDAOImpl(); // Khởi tạo DAO một lần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // Thiết lập mã hóa
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession(false);

            // Lấy đường dẫn hiện tại
            String path = httpRequest.getRequestURI();

            // Kiểm tra nếu người dùng đã đăng nhập
            if (session != null && session.getAttribute("userIdOrEmail") != null) {
                String fullname = (String) session.getAttribute("userIdOrEmail");
                System.out.println(session.getAttribute("userIdOrEmail"));
                // Bỏ qua các request tĩnh
                if (!path.startsWith("/resources/") && !path.startsWith("/assets/")) {
                    LogDAOImpl logDAO = new LogDAOImpl();
                    Log newLog = new Log();
                    newLog.setFullname(fullname);
                    logDAO.create(newLog);
                    System.out.println("Log created for user: " + fullname + " - Path: " + path);
                }
            }

            // Chuyển tiếp request
            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Ghi log lỗi và chuyển hướng đến trang lỗi
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());

            // Tránh gọi sendRedirect nếu response đã bị cam kết
            if (!((HttpServletResponse) response).isCommitted()) {
                ((HttpServletResponse) response).sendRedirect("/error.jsp");
            } else {
                // Nếu phản hồi đã bị cam kết, có thể forward tới trang lỗi
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }



    @Override
    public void destroy() {
        logDAO = null; // Giải phóng tài nguyên
    }
}
