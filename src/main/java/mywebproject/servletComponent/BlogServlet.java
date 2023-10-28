package mywebproject.servletComponent;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mywebproject.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static mywebproject.dataBaseComponent.UserDB.statement;

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
        String query = "SELECT title, content, post_time FROM blogs";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Iterate through the result set and create BlogPost objects
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String title = null;
            try {
                title = resultSet.getString("title");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String content = null;
            try {
                content = resultSet.getString("content");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String date = null;
            try {
                date = resultSet.getString("post_time");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            BlogPost blogPost = new BlogPost(title, content, date);
            blogList.add(blogPost);
        }
        for (BlogPost blogPost : blogList) {
            System.out.println(blogPost.title);
        }
        /*for(int i=0;i<20;++i){
            BlogPost blogPost = new BlogPost(
                    "博客标题"+i,
                    "博客内容",
                    "上传时间"
            );
            blogList.add(blogPost);
        }*/
        //使用Gson转换
        Gson gson = new Gson();
        String json = gson.toJson(blogList);
        //设置响应格式
        response.setContentType("application/json");
        //发送
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
    }

    public void destroy() {

    }
}