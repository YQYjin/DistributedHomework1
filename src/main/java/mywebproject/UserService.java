package mywebproject;

import java.util.HashMap;

public class UserService {
    static HashMap<String, User> usermap;
    public static UserService userService;
    static boolean isInited=false;
    private UserService(){
        usermap = new HashMap<> ();
    }
    public static void init(){
        if(isInited){
            return;
        }
        userService= new UserService ();
        usermap.put ("admin", new User ("admin", "123456"));
    }
    public static int login(String userName,String pwd){
        boolean isContains = usermap.containsKey (userName);
        if(isContains){
            User user = usermap.get (userName);
            if(user.getPassword ().equals (pwd)){
                return 1;// 登录成功
            } else{
                return 0;// 密码错误
            }
        } else{
            return -1;// 用户不存在
        }
    }
    public static int register(String userName,String pwd){
        boolean isContains = usermap.containsKey (userName);
        if(isContains){
            System.out.println ("用户已存在");
            return 0;
        }
        System.out.println("注册用户"+userName+" 密码:"+"pwd");
        usermap.put (userName, new User (userName, pwd));
        return 1;
    }
}
