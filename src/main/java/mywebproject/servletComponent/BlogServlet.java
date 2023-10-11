package mywebproject.servletComponent;

import com.google.gson.Gson;
import mywebproject.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "blogServlet", value = "/blog")
public class BlogServlet extends HttpServlet {
    private String message;
    public void init() {
        UserService.init();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<BlogPost> blogList = new ArrayList<>();
        //模拟博客信息
        for(int i=0;i<20;++i){
            BlogPost blogPost = new BlogPost(
                    "博客标题"+i,
                    "博客内容",
                    "上传时间"
            );
            blogList.add(blogPost);
        }
        //使用Gson转换
        Gson gson = new Gson();
        String json= gson.toJson(blogList);
        //设置响应格式
        response.setContentType("application/json");
        //发送
        PrintWriter writer=response.getWriter();
        writer.write(json);
        writer.close();
    }
    public void destroy () {

    }
}