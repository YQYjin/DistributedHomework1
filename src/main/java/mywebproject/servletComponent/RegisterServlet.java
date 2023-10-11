package mywebproject.servletComponent;

import mywebproject.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mywebproject.dataBaseComponent.UserDB;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private String message;
    private UserService userService;

    public void init() {
        UserService.init();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行doPost");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取前端表单的参数
        String name = request.getParameter("name");
        String password = request.getParameter("pwd");
        System.out.println("register name = " + name);
        System.out.println("register password = " + password);
        //int registerCode=UserService.userService.register(name, password);
        boolean check= UserDB.isUserExist(name);
        // 简单的验证
        if (check) {
            UserDB.addUser("",name,password,"false");
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }
    public void destroy () {
    }
}