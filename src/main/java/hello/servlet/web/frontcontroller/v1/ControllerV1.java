package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    // Servlet이랑 모양이 똑같은 인터페이스를 만듦
    // 각 컨트롤러들이 이 인터페이스를 구현하도록 할 것임
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
