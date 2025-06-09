package Controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class RestIO {
    static private ObjectMapper mapper = new ObjectMapper();

    // Đọc chuỗi JSON từ request
    public static String readJson(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        return buffer.toString();
    }

    // Gửi chuỗi JSON về client
    public static void writeJson(HttpServletResponse resp, String json) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.flushBuffer();
    }

    // Đọc JSON và chuyển sang Java Object
    public static <T> T readObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        String json = readJson(req);
        return mapper.readValue(json, clazz);
    }

    // Chuyển Java Object sang JSON và gửi về client
    public static void writeObject(HttpServletResponse resp, Object data) throws IOException {
        String json = mapper.writeValueAsString(data);
        writeJson(resp, json);
    }

    // Gửi đối tượng rỗng về client
    public static void writeEmptyObject(HttpServletResponse resp) throws IOException {
        writeObject(resp, Map.of());
    }
}
