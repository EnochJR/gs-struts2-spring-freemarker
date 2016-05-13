package com.tom.model;

/**
 * Created by tom on 2016/5/12.
 */

public class Account extends BaseObject{
    String username;
    String password;
    String role;//  SA  supper_admin

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
