package mywebproject;

import java.util.Objects;

public class User {
    String userName;
    String password;
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String toString(){
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals (userName, user.userName) && Objects.equals (password,
                user.password);
    }
    @Override
    public int hashCode(){
        return Objects.hash (userName, password);
    }
}