package Controller;

import java.io.IOException;
import java.util.List;

import Entity.Video;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/videoList", "/video/delete", "/video/add", "/video/edit"})
public class VideoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VideoServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("videoList")) {
            doVideoList(request, response);
        } else if (uri.contains("video/delete")) {
            doVideoDelete(request, response);
        } else if (uri.contains("video/add")) {
            doVideoAdd(request, response);
        } else if (uri.contains("video/edit")) {
            doVideoEdit(request, response);
        }
    }

    private void doVideoList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VideoDAOImpl videoDAO = new VideoDAOImpl();
        List<Video> listVideos = videoDAO.findAll();
        request.setAttribute("listVideos", listVideos);
        request.getRequestDispatcher("/viewsVideo/listOfVideos.jsp").forward(request, response);
    }

    private void doVideoAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            String title = request.getParameter("title");
            String poster = request.getParameter("poster");
            String description = request.getParameter("description");
            String active = request.getParameter("active");

            if (title == null || title.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {

                request.setAttribute("error", "Các ô không được để trống.");
                request.getRequestDispatcher("/viewsVideo/addVideo.jsp").forward(request, response);
                return;
            }

            // Convert active status to Boolean
            Boolean isActive = active != null && active.equals("1");

            VideoDAOImpl videoDAO = new VideoDAOImpl();
            Video video = new Video();
            video.setTitle(title);
            video.setPoster(poster);
            video.setDescription(description);
            video.setActive(isActive);

            videoDAO.create(video);

            String message = "Thêm video thành công!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/viewsVideo/addVideo.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/viewsVideo/addVideo.jsp").forward(request, response);
        }
    }

    private void doVideoEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            if (request.getParameter("editVideoButton") != null) {
                String videoId = request.getParameter("videoId");
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String active = request.getParameter("active");

                try {
                    // Convert active status to Boolean
                    Boolean isActive = active != null && active.equals("1");

                    VideoDAOImpl videoDAO = new VideoDAOImpl();
                    Video video = videoDAO.findById(videoId);
                    if (video != null) {
                        video.setTitle(title);
                        video.setDescription(description);
                        video.setActive(isActive);
                        videoDAO.update(video);

                        String message = "Sửa video thành công!";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/videoList").forward(request, response);
                    } else {
                        request.setAttribute("error", "Không tìm thấy video!");
                        request.getRequestDispatcher("/viewsVideo/editVideo.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("error", "Lỗi khi cập nhật video.");
                    request.getRequestDispatcher("/viewsVideo/editVideo.jsp").forward(request, response);
                }
            }
        } else {
            String videoId = request.getParameter("id");
            VideoDAOImpl videoDAO = new VideoDAOImpl();
            Video video = videoDAO.findById(videoId);
            if (video != null) {
                request.setAttribute("video", video);
                request.getRequestDispatcher("/viewsVideo/editVideo.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/videoList").forward(request, response);
            }
        }
    }

    private void doVideoDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String videoId = request.getParameter("id");
        if (videoId != null) {
            VideoDAOImpl videoDAO = new VideoDAOImpl();
            videoDAO.deleteById(videoId);

            String message = "Xóa video thành công!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/videoList").forward(request, response);
        }
    }
}
