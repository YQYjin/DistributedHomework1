package mywebproject.dataBaseComponent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServer {

    private static Connection conn = null;

    private DBServer() {
        String url = "jdbc:mysql://localhost:3306/";//这里没写数据库名
        String user = "root";//替换成你自已的数据库用户名
        String password = "123456";//这里替换成你自已的数据库用户密码
        String sqlStr = "";
        Statement st;
        try {   //异常处理语句是必需的.否则不能通过编译!
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            // st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException e) {
            System.out.println("ErrorCode:" + e.getErrorCode());
            System.out.println("SQLState:" + e.getSQLState());
            System.out.println("reason:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DBSrver初始化完成");
    }

    private static DBServer dbServer = new DBServer();

    public static DBServer getInstance() {
        return dbServer;
    }

    public static Connection getConnection() {
        return conn;
    }

//    public static void main(String[] args) {
//        DBServer dbServer1=new DBServer();
//        if(UserDB.isPwdCorrect("admin","123456")){
//            System.out.println("密码正确");
//        }else {
//            System.out.println("密码错误");
//        }
//    }
}
