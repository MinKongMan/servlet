package hello.servlet.web.frontcontroller.v5.Adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws IOException, ServletException {
        ControllerV4 handler1 = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        String viewName = handler1.process(paramMap, objectObjectHashMap);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(objectObjectHashMap);

        return modelView;
    }


    private Map<String, String> createParamMap(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(key -> map.put(key, request.getParameter(key)));
        return map;
    }
}
