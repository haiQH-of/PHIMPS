package Controlleruser;

import java.io.IOException;

//import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import model.User;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/registration.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		User user = new User();
//		try {
//			BeanUtils.populate(user, req.getParameterMap());
//			UserDAO userDAO = new UserDAO();
//			userDAO.insert(user);
//			req.setAttribute("message", "Insert successfully!");
//			req.getRequestDispatcher("/Login").forward(req, resp);
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//			req.setAttribute("error", "Failed! " + e.getMessage());
//		}
//		req.setAttribute("user", user);
		req.getRequestDispatcher("/views/user/registration.jsp").forward(req, resp);
	}

}
