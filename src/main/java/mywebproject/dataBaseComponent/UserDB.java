package mywebproject.dataBaseComponent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB{
    // 负责用户的 增删查改
    static Connection conn = DBServer.getConnection ();
    static Statement statement = null;

    static {
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
            String sqlStr = "use bloginfos";
            statement.executeUpdate(sqlStr);
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    public static void addUser(String id, String userName, String pwd, String isOnline){
        String sql = "insert into user values('" + id + "','" + userName + "','" + pwd + "','" + isOnline + "')";
        try {
            statement.executeUpdate (sql);
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    // 查询用户是否存在
    public static boolean isUserExist(String userName){
        String sql = "select * from user where userName = '" + userName + "'";
        try {
            //若有结果则next返回true
            return statement.executeQuery (sql).next ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    // 查询用户密码是否正确
    public static boolean isPwdCorrect(String userName,String pwd){
        String sql = "select * from user where userName = '" + userName + "' and password = '" + pwd + "'";
        try {
            return statement.executeQuery (sql).next ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    // 查询用户是否在线
    public static boolean isUserOnline(String userName){
        String sql = "select * from user where userName = '" + userName + "' and isOnline = 'true'";
        try {
            return statement.executeQuery (sql).next ();
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }

    // 修改用户在线状态
    public static void changeUserOnline(String userName,String isOnline){
        String sql = "update user set isOnline = '" + isOnline + "' where userName = '" + userName + "'";
        try {
            statement.executeUpdate (sql);
        } catch (SQLException e) {
            throw new RuntimeException (e);
        }
    }



}

