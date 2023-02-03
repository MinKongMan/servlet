package hello.servlet.web.servletmvc;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // WEB-INF는 외부에서(브라우저에서) 호출해도 호출되지 않음
                        // 즉 항상 컨트롤러를 거쳐야만 View로 보여짐
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // 컨트롤러에서 뷰로 이동할 때 사용하는 거
        dispatcher.forward(req, resp); // Servlet에서 JSP 호출
    }
}
