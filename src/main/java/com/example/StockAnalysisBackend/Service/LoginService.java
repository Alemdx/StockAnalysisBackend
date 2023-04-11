package com.example.StockAnalysisBackend.Service;

import com.example.StockAnalysisBackend.Entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Alex
 * @date 下午4:59
 */
@Service
public interface LoginService {
    User findUserByUserName(String username);
    int  addUser(String username,String password);
}
