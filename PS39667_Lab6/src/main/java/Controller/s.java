package Controller;



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

@WebServlet("/searchVideos")
public class s extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s() {
        super();
        videoDAO = new VideoDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Video> videos = videoDAO.findByKeyword(keyword);
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/views/user/timkiem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
