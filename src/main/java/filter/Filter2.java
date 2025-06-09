package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/filter2") // Có thể tùy chỉnh URL pattern
public class Filter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {

        // In giá trị của thuộc tính "hello" đã được thiết lập trong Filter1
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println(req.getAttribute("hello"));

        // Tiếp tục chuỗi filter (gọi filter tiếp theo hoặc servlet)
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Làm sạch nếu cần
    }
}
