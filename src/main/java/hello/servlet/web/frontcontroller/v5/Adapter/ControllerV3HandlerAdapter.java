package hello.servlet.web.frontcontroller.v5.Adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Shift F6 으로 이름 수정 가능
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // ControllerV3 인터페이스로 구현한게 넘어오게 되면 True 반환
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler)
            throws IOException, ServletException {
        ControllerV3 controller = (ControllerV3) handler; // supports에서 V3가 아닌 것을 걸렀기 때문에 사용 가능

        Map<String, String> paramMap = createParamMap(request);
        ModelView modelview = controller.process(paramMap);
        return modelview;
    }

    private Map<String, String> createParamMap(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(key -> map.put(key, request.getParameter(key)));
        return map;
    }
}
