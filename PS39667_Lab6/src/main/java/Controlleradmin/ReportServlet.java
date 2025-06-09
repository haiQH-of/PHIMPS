package Controlleradmin;

import java.io.IOException;
import java.util.List;
import Dao.FavoriteDAO;
import Dao.VideoDAO;
import Entity.FavoriteReport;
import Entity.FavoriteUserReport;
import Entity.ShareReport;
import Entity.Video;
import Impl.FavoriteDAOImpl;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Report")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideoDAO videoDAO;
    private FavoriteDAO favoriteDAO;

    @Override
    public void init() throws ServletException {
        videoDAO = new VideoDAOImpl();
        favoriteDAO = new FavoriteDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reportSharedFriend(req, resp);
        reportFavoriteUserByVideos(req, resp);
        reportFavoritesByVideos(req, resp);
        req.getRequestDispatcher("/views/admin/reports.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void reportSharedFriend(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String videoShareId = req.getParameter("videoShareId");
            List<Video> videoShareList = videoDAO.findAll();

            if (videoShareId == null && videoShareList.size() > 0) {
                videoShareId = videoShareList.get(0).getId();
            }
            List<ShareReport> shareReportsList = favoriteDAO.reportShare(videoShareId);

            req.setAttribute("videoShareId", videoShareId);
            req.setAttribute("videoShareList", videoShareList);
            req.setAttribute("shareReportsList", shareReportsList);
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    protected void reportFavoriteUserByVideos(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String videoUserId = req.getParameter("videoUserId");
            List<Video> videoList = videoDAO.findAll();

            if (videoUserId == null && videoList.size() > 0) {
                videoUserId = videoList.get(0).getId();
            }
            List<FavoriteUserReport> favUserList = favoriteDAO.reportFavoriteUsersByVideos(videoUserId);

            req.setAttribute("videoUserId", videoUserId);
            req.setAttribute("videoList", videoList);
            req.setAttribute("favUserList", favUserList);
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    protected void reportFavoritesByVideos(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<FavoriteReport> list = favoriteDAO.reportFavoritesByVideos();
            req.setAttribute("favList", list);
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }
}
