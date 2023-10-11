package mywebproject.servletComponent;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", "zhangsan");
        userMap.put("avatarUrl", "login.png");
        userMap.put("userInfo", "已从服务器获取用户信息");

        System.out.println("发送用户信息");

        Gson gson = new Gson();
        String json = gson.toJson(userMap);
        //设置响应格式
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
