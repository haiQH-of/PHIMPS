package Controlleruser;

import java.io.IOException;
import java.util.List;

import Entity.Video;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Favorite")
public class FavoriteServlet extends HttpServlet {
    private VideoDAOImpl videoDAO = new VideoDAOImpl(); // Khởi tạo DAO tại đây để sử dụng sau
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");

                if (userIdOrEmail != null) {
                    List<Video> list = videoDAO.findFavoriteVideosByUserId(userIdOrEmail);
                    req.setAttribute("videos", list);
                } else {
                    req.setAttribute("error", "User ID or Email is missing from session.");
                }
            } else {
                req.setAttribute("error", "Session is not available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while fetching favorite videos.");
        }
        
        req.getRequestDispatcher("/views/user/favorite.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // Tái sử dụng phương thức doGet để xử lý POST request
    }
}
