package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.Adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> map = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        map.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        map.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        map.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if(handler==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 버전에 맞는걸 가져옴
        ModelView modelView = adapter.handle(req, resp, handler);

        String path = modelView.getViewName();
        MyView view = viewResolver(path);

        view.render(modelView.getModel(), req, resp);
    }

    private MyView viewResolver(String path) { return new MyView("/WEB-INF/views/" + path +".jsp");}

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler를 찾을 수 없습니다." + handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String getUri = req.getRequestURI();

        Object handler = map.get(getUri);
        return handler;
    }
}
