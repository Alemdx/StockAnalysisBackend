package com.example.StockAnalysisBackend.Entity;

import java.io.Serializable;

/**
 * @author Alex
 * @date 下午4:43
 */
//@Entity是使用jpa的实现映射
public class User implements Serializable {
    private String id;
    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
