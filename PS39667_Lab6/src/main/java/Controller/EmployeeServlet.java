package Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // Đặt kiểu nội dung trả về là JSON.
        response.setCharacterEncoding("UTF-8"); // Đảm bảo dữ liệu trả về sử dụng UTF-8.

        // Chuỗi JSON chứa thông tin nhân viên.
        String json = """
           {
			  "id": "NV05",
			  "name": "Nhân viên 05",
			  "gender": true,
			  "salary": 3500
			}

        """;

        // Gửi dữ liệu JSON về client.
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush(); // Đẩy dữ liệu ra ngay lập tức.
    }
}
