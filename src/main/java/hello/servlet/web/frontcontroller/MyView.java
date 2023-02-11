package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {
    private String ViewPath;

    public MyView(String ViewPath){
        this.ViewPath = ViewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // View를 만드는 행위 자체를 렌더링한다라고 함
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ViewPath);
        requestDispatcher.forward(request, response);
    }
}
