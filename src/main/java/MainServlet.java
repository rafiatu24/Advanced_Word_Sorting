import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/books.view/")
public class MainServlet extends HttpServlet {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();
        String bookId = (pathInfo != null && pathInfo.length() > 1) ? pathInfo.substring(1) : null;

        if (bookId == null) {
            sendErrorResponse(response, "Book ID is required", HttpServletResponse.SC_BAD_REQUEST);
            return;


        }


    }
      private void sendErrorResponse (HttpServletResponse response, String message,int statusCode) throws
            IOException {
                Map<String, String> error = new HashMap<>();
                error.put("error", message);
                response.setContentType("application/json");
                response.setStatus(statusCode);
                objectMapper.writeValue(response.getWriter(), error);
            }
}