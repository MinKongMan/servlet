package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
    // Map을 넘겨줌으로써 기존에 HttpServletRequest와 같은 HttpServlet에 종속성을 없앰
}
