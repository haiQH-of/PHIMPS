package Controlleruser;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import Entity.Favorite;
import Entity.User;
import Entity.Video;
import Impl.FavoriteDAOImpl;
import Impl.UserDAOImpl;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/HomeList", "/Homedetail/*", "/HomeLike/*", "/Home/share/*"})
public class Hometesst extends HttpServlet {
    private VideoDAOImpl videoDAO;
    private UserDAOImpl userDAO;
    private FavoriteDAOImpl favoriteDAO;

    public Hometesst() {
        videoDAO = new VideoDAOImpl();
        userDAO = new UserDAOImpl();
        favoriteDAO = new FavoriteDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("/HomeList")) {
            doList(request, response);
        } else if (uri.contains("/Homedetail")) {
            doDetail(request, response);
        } else if (uri.contains("/HomeLike")) {
            doLikeVideo(request, response);
        } else if (uri.contains("/Home/share")) {
            doShare(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> listVideos = videoDAO.findAll();
        request.setAttribute("listVideos", listVideos);
        request.getRequestDispatcher("/views/user/home.jsp").forward(request, response);
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");
            if (userIdOrEmail == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String videoId = extractVideoId(request);
            if (videoId == null || videoId.isEmpty()) {
                throw new IllegalArgumentException("Invalid Video ID");
            }

            Video video = videoDAO.findById(videoId);
            List<Video> relatedVideos = videoDAO.findAll();

            request.setAttribute("video", video);
            request.setAttribute("videos", relatedVideos);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading video details: " + e.getMessage());
        }
        request.getRequestDispatcher("/views/user/detail.jsp").forward(request, response);
    }

    private String extractVideoId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int lastSlashIndex = uri.lastIndexOf("/");
        return (lastSlashIndex != -1 && lastSlashIndex < uri.length() - 1) 
               ? uri.substring(lastSlashIndex + 1) 
               : null;
    }

    private void doLikeVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");
            String videoId = extractVideoId(request);

            if (userIdOrEmail == null || videoId == null) {
                throw new IllegalArgumentException("Invalid user or video");
            }

            User user = userDAO.findByIdOrEmail(userIdOrEmail);
            Video video = videoDAO.findById(videoId);

            if (user == null || video == null) {
                throw new IllegalArgumentException("User or Video not found");
            }

            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setVideo(video);
            favorite.setLikedate(new Date());

            favoriteDAO.create(favorite);
            System.out.println("Video liked successfully!");

            request.setAttribute("video", video);
            request.setAttribute("videos", videoDAO.findAll());
            request.setAttribute("message", "Video liked successfully!");
        } catch (Exception e) {
            request.setAttribute("error", "Error liking video: " + e.getMessage());
        }
        request.getRequestDispatcher("/views/user/detail.jsp").forward(request, response);
    }

    private void doShare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String userIdOrEmail = (String) session.getAttribute("userIdOrEmail");
            String videoId = extractVideoId(request);

            if (userIdOrEmail == null || videoId == null) {
                throw new IllegalArgumentException("Invalid user or video");
            }

            User user = userDAO.findByIdOrEmail(userIdOrEmail);
            Video video = videoDAO.findById(videoId);

            if (user == null || video == null) {
                throw new IllegalArgumentException("User or Video not found");
            }

            // Implement sharing functionality
            String shareMessage = "Video shared successfully!";
            System.out.println(shareMessage);

            request.setAttribute("video", video);
            request.setAttribute("videos", videoDAO.findAll());
            request.setAttribute("message", shareMessage);
        } catch (Exception e) {
            request.setAttribute("error", "Error sharing video: " + e.getMessage());
        }
        request.getRequestDispatcher("/views/user/detail.jsp").forward(request, response);
    }
}
