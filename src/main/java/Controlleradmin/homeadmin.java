package Controlleradmin;



import java.io.IOException;
import java.util.Date;
import java.util.List;

import Dao.VideoDAO;
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

@WebServlet({"/admin/Homeadmin","/admin/video","/admin/user","/admin/logoff","/admin/Report"})
public class homeadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 @Override
	    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	        String uri = request.getRequestURI();
	        if (uri.contains("/admin/Homeadmin")) {
	            doHome(request, response);
	        } else if (uri.contains("admin/video")) {
	            dovideo(request, response);
	        } else if (uri.contains("/admin/user")) {
	        	douser(request, response);
	        } else if (uri.contains("/admin/logoff")) {
	            dologoff(request, response);
	        }

	        request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
	    }


	    private void doHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
	    }




	    private void dovideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {

	            VideoDAOImpl videoDAO = new VideoDAOImpl();
	            List<Video> listVideos = videoDAO.findAll();
	            request.setAttribute("listVideos", listVideos);
	            request.getRequestDispatcher("/viewsVideo/listOfVideos.jsp").forward(request, response);


	        } catch (Exception e) {
	        	request.setAttribute("error", e.getMessage());
	        }
	        request.getRequestDispatcher("/views/user/detail.jsp").forward(request, response);
	    }



	    private void douser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	   try {

		        	  UserDAOImpl userDAO = new UserDAOImpl();
		              List<User> listUsers = userDAO.findAll();
		              request.setAttribute("listUsers", listUsers);
		              request.getRequestDispatcher("/views/admin/listOfUsers.jsp").forward(request, response);

		        } catch (Exception e) {
		        	request.setAttribute("error", e.getMessage());
		        }
	    }
	    private void dologoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    }


}
