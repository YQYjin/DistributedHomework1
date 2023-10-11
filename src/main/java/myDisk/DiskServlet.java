package myDisk;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50) // 50 MB
public class DiskServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY =
            "D:\\CodeWorkSpace\\MyBlog\\src\\main\\webapp\\files";
    // 获取文件列表
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> fileList = getFileList ();
        response.setContentType ("application/json");
        response.getWriter ().write (convertToJson (fileList));
        System.out.println ("doGet");
    }
    // 上传文件
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取文件上传的Part对象
        Part filePart = request.getPart("file");
        // 获取上传文件的文件名
        String fileName = getFileName(filePart);
        // 将文件保存到指定目录
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        saveFile(filePart, filePath);

        response.getWriter().println("文件上传成功！");
    }
    // 拆分 part 中文件名
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') +
                        1).trim().replace("\"", "");
            }
        }
        return null;
    }
    // 保存文件 写入文件中
    private void saveFile(Part part, String filePath) throws IOException {
        try (InputStream inputStream = part.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    // 提取文件列表
    private List<String> getFileList(){
        List<String> fileList = new ArrayList<>();
        File uploadDir = new File (UPLOAD_DIRECTORY);
        if(uploadDir.exists () && uploadDir.isDirectory ()){
            File[] files = uploadDir.listFiles ();
            if(files != null){
                for(File file : files){
                    fileList.add (file.getName ());
                }
            }
        }
        return fileList;
    }
    // 文件名转 JSON格式
    private String convertToJson(List<String> fileList){
        StringBuilder json = new StringBuilder ("[");
        for(int i = 0; i < fileList.size (); i++){
            json.append ("\"").append (fileList.get (i)).append ("\"");
            if(i < fileList.size () - 1){
                json.append (",");
            }
        }
        json.append ("]");
        return json.toString ();
    }
}
