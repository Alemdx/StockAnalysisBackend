package com.example.StockAnalysisBackend.Jwt;

/**
 * @author Alex
 * @date 2023/4/11 下午2:24
 */
public class JwtAuthenticationResponse {

    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
