package com.example.huongthutran.btvn_buoi5;

/**
 * Created by Huong Thu Tran on 3/17/2018.
 */

public class User {
    public static String USER_TABLENAME="USERS";
    public static String USER_ID="ID_USER";
    public static String USER_USERNAME="USERNAME";
    public static String USER_PASSWORD="PASSWORD";

    private  int id_user;
    private  String userName;
    private String userPassword;

    public User(){

    }
    public User(int id_user, String userName, String userPassword) {
        this.id_user = id_user;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
