	package Controlleruser;

import java.io.IOException;
import java.util.List;

import Dao.VideoDAO;
import Entity.Video;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/Detail", "/detail/*" })
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get the URI to extract videoId
            String URI = req.getRequestURI(); // Ví dụ: "/PS39667_Lab3/detail/video01"
            String videoId = URI.substring(URI.lastIndexOf("/") + 1); // Lấy ID video từ URL

            // Tạo đối tượng VideoDAO và lấy video theo videoId
            VideoDAO videoDAO = new VideoDAOImpl();
            Video video = videoDAO.findById(videoId);

            // Lấy danh sách video để hiển thị các video liên quan
            List<Video> list = videoDAO.findAll();

            // Gửi video và danh sách video tới view (JSP)
            req.setAttribute("video", video);

            req.setAttribute("videos", list);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        req.getRequestDispatcher("/views/user/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/detail.jsp").forward(req, resp);
    }
}