package Controlleruser;

import java.io.IOException;

import Entity.User;
import Impl.UserDAOImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/scope1")
public class ScopeAttributeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ScopeAttributeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thay vì lấy tham số từ request, sử dụng giá trị cứng "user03"
        String userId = request.getParameter("user");

        // Tìm user trong cơ sở dữ liệu
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findByIdOrEmail(userId);

        if (user == null) {
            // Nếu không tìm thấy user, xử lý lỗi
            response.sendRedirect("userNotFound.jsp");
            return;
        }

        // Lấy session và đặt thông tin user vào session
        HttpSession session = request.getSession();
        session.setAttribute("scopeSession", user.getFullname());

        // Xử lý số lượt truy cập
        ServletContext application = request.getServletContext();
        int visitCount = 1;
        if (application.getAttribute("visitCount") != null) {
            visitCount = (int) application.getAttribute("visitCount");
        }
        visitCount++;
        application.setAttribute("visitCount", visitCount);

        // Chuyển tiếp request đến một servlet hoặc JSP khác
        request.getRequestDispatcher("/scope2").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
