package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //모델의 정보까지 받아야 하므로 다형성 생성
        modelToRequestAttribute(model, request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ViewPath);
        requestDispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
        // model에 있는 값(키가 member)을 꺼내서 HttpServletRequest에 다 넣음
        // JSP 는 HttpServletRequest에 값을 넣어야 꺼내 쓸 수 있음.
    }
}
