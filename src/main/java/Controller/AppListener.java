package Controller;


	import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

	@WebListener
	public class AppListener implements HttpSessionListener, ServletContextListener {

	    @Override
	    public void contextInitialized(ServletContextEvent e) {
	        // Đọc số đếm từ file vào application scope khi ứng dụng khởi động
	        ServletContext application = e.getServletContext();
	        Integer visitors = 0;

	        try {
	            String path = application.getRealPath("/visitors.txt");
	            List<String> lines = Files.readAllLines(Paths.get(path));
	            visitors = Integer.valueOf(lines.get(0));
	        } catch (Exception e2) {
	            visitors = 9999999; // Giá trị mặc định nếu file không tồn tại
	        }

	        application.setAttribute("visitors", visitors);
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent e) {
	        // Lưu số đếm vào file khi ứng dụng kết thúc
	        ServletContext application = e.getServletContext();
	        Integer visitors = (Integer) application.getAttribute("visitors");

	        try {
	            String path = application.getRealPath("/visitors.txt");
	            byte[] data = String.valueOf(visitors).getBytes();
	            Files.write(Paths.get(path), data, StandardOpenOption.CREATE);
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }

	    @Override
	    public void sessionCreated(HttpSessionEvent e) {
	        // Tăng số đếm khi có session mới được tạo
	        HttpSession session = e.getSession();
	        ServletContext application = session.getServletContext();
	        Integer visitors = (Integer) application.getAttribute("visitors");
	        application.setAttribute("visitors", visitors + 1);
	    }

	    @Override
	    public void sessionDestroyed(HttpSessionEvent e) {
	        // Không cần xử lý gì khi session bị hủy
	    }
	}

