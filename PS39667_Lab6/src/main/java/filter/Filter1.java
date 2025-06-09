package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/filter1") // Có thể tùy chỉnh URL pattern
public class Filter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {

        // Đặt thuộc tính "hello" vào request
        HttpServletRequest req = (HttpServletRequest) request;
        req.setAttribute("hello", "Tôi là filter 1");

        // Tiếp tục chuỗi filter (gọi Filter tiếp theo)
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Làm sạch nếu cần
    }
}
