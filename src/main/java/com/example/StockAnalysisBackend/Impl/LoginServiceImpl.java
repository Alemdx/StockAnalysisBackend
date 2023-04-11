package com.example.StockAnalysisBackend.Impl;

import com.example.StockAnalysisBackend.Entity.User;
import com.example.StockAnalysisBackend.Mapper.UserMapper;
import com.example.StockAnalysisBackend.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex
 * @date 下午5:05
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUserName(String username) {
        User user;
        user=userMapper.findByUserName(username);
        return user;
    }

    @Override
    public int addUser(String username, String password) {
         int code;
         User user=new User();
         String id=UUID.randomUUID().toString().replace("-", "").toLowerCase();
         user.setPassword(password);
         user.setUsername(username);
//         System.out.println(id);
         user.setId(id);
         code=userMapper.addUser(user);
         return code;
    }
}
