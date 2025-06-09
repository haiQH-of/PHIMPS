package Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import Entity.Favorite;
import Entity.User;
import Impl.FavoriteDAOImpl;
import Impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/userList", "/user/delete", "/user/add", "/user/edit"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("userList")) {
            doUserList(request, response);
        } else if (uri.contains("user/delete")) {
            try {
                doUserDelete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("user/add")) {
            doUserAdd(request, response);
        } else if (uri.contains("/user/edit")) {
            try {
                doUserUpdate(request, response);
            } catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doUserUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
        if (request.getMethod().equals("POST")) {
            if (request.getParameter("updateUserButton") != null) {
                User user = new User();
                BeanUtils.populate(user, request.getParameterMap());
                UserDAOImpl userDAO = new UserDAOImpl();
                userDAO.update(user);

                String message = "Edit user " + user.getId() + " successfully!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/userList").forward(request, response);
            } else {
                request.getRequestDispatcher("/userList").forward(request, response);
            }
        } else { //GET METHOD
            String userId = request.getParameter("id");
            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.findById(userId);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/viewsUser/updateUser.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/userList").forward(request, response);
            }
        }
    }

    private void doUserAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            if (request.getParameter("addUserButton") != null) {
                String id = request.getParameter("id");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String fullname = request.getParameter("fullname");
                String isAdminStr = request.getParameter("admin");

                if (id == null || id.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    fullname == null || fullname.trim().isEmpty() ||
                    isAdminStr == null || isAdminStr.trim().isEmpty()) {

                    request.setAttribute("error", "Các ô không được để trống.");
                    request.getRequestDispatcher("/viewsUser/addUser.jsp").forward(request, response);
                    return;
                }

                boolean isAdmin = Boolean.parseBoolean(isAdminStr);
                UserDAOImpl userDAO = new UserDAOImpl();
                User user = new User();

                // Gán các giá trị cho các thuộc tính của User, bao gồm ID
                user.setId(id);
                user.setPassword(password);
                user.setEmail(email);
                user.setFullname(fullname);
                user.setAdmin(isAdmin);

                userDAO.create(user);

                String message = "Thêm người dùng: " + id + " thành công!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/viewsUser/addUser.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/viewsUser/addUser.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/viewsUser/addUser.jsp").forward(request, response);
        }
    }

    private void doUserDelete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String userId = request.getParameter("id");
        if (userId != null) {
            UserDAOImpl userDAO = new UserDAOImpl();
            userDAO.deleteById(userId);
            String message = "Delete user: " + userId + " successfully!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/userList").forward(request, response);
        }
        request.getRequestDispatcher("/userList").forward(request, response);
    }
    private void doUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        UserDAOImpl userDAO = new UserDAOImpl();
//        List<User> listUsers = userDAO.findAll();
//        request.setAttribute("listUsers", listUsers);
        FavoriteDAOImpl faDAO = new FavoriteDAOImpl();
        List<Favorite> listUsers = faDAO.findAll();
        request.setAttribute("listUsers", listUsers);
        request.getRequestDispatcher("/viewsUser/listOfUsers.jsp").forward(request, response);
    }
}
