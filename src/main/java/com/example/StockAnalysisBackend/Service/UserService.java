package com.example.StockAnalysisBackend.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Alex
 * @date 2023/4/11 下午2:32
 */
public interface UserService {
    public Authentication authenticate(String username, String password);
    public UserDetails loadUserByUsername(String username);
}
