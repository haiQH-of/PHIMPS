package Controlleruser;

import java.io.IOException;
import java.util.List;

import Entity.Video;
import Impl.VideoDAOImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ServletContext application = req.getServletContext();
		System.out.println("Request: " + (String)session.getAttribute("fullname"));
		System.out.println("Request: " + (int)application.getAttribute("visitCount"));
		   VideoDAOImpl videoDAO = new VideoDAOImpl();
	        List<Video> listVideos = videoDAO.findAll();
	        req.setAttribute("listVideos", listVideos);
		req.getRequestDispatcher("/views/user/homet.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/homet.jsp").forward(req, resp);
	}
}
