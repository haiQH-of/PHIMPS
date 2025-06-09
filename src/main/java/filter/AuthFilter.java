package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
    "/admin/*",
    "/account/change-password",
    "/account/edit-profile",
    "/video/like/*",
    "/video/share/*"
})
public class AuthFilter implements Filter {
	  public static final String SECURITY_URI = "securityUri";

	@SuppressWarnings("null")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    // Lấy người dùng hiện tại từ session
	    HttpSession session = httpRequest.getSession(false);

	    if (session == null) {

	        httpResponse.sendRedirect(httpRequest.getContextPath() + "/account/login");
	        return;
	    }
	    Boolean currentUser =  (Boolean) session.getAttribute("dn");

	    System.out.println("8888888888888888 " + currentUser);


	    // Kiểm tra trạng thái đăng nhập


	    // Kiểm tra quyền truy cập admin
	    String requestURI = httpRequest.getRequestURI();
	    String contextPath = httpRequest.getContextPath();

	    if (isAdminUrl(requestURI, contextPath) && !currentUser) {
	        // Nếu truy cập vào khu vực admin mà không có quyền
	        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Yêu cầu quyền quản trị");
	        return;
	    }

	    // Nếu được phép, tiếp tục chuỗi bộ lọc
	    chain.doFilter(request, response);
	}


    // Phương thức kiểm tra URL admin
    private boolean isAdminUrl(String requestURI, String contextPath) {
        return requestURI.startsWith(contextPath + "/admin/");
    }
}