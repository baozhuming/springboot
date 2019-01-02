package com.thymeleaf.thymeleaf.bean;

public class User {
    private String userId;
    private String phone;
    private String userName;
    private int sex;
    private String password;
    private String eMail;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public User(String userId, String phone, String userName, int sex, String password, String eMail) {
        this.userId = userId;
        this.phone = phone;
        this.userName = userName;
        this.sex = sex;
        this.password = password;
        this.eMail = eMail;
    }
}
