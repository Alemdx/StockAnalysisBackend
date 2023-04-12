package com.example.StockAnalysisBackend.POJO;

/**
 * @author Alex
 * @date 2023/4/11 上午11:50
 */
public class LoginRequest {
    private String username;
    private String password;

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

