package mywebproject.servletComponent;

import mywebproject.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mywebproject.dataBaseComponent.UserDB;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;
    public void init() {
        UserService.init();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行doPost");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取前端表单的参数
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("name = " + name);
        System.out.println("password = " + password);
        //int loginCode = UserService.login(name, password);
        boolean check=UserDB.isPwdCorrect(name,password);
        // 简单的验证
        if (check) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }
    public void destroy () {

    }
}