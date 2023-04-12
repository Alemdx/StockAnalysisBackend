package com.example.StockAnalysisBackend.Controller;

import com.example.StockAnalysisBackend.Entity.User;
import com.example.StockAnalysisBackend.Impl.LoginServiceImpl;
import com.example.StockAnalysisBackend.Jwt.JwtTokenUtils;
import com.example.StockAnalysisBackend.Jwt.PassToken;
import com.example.StockAnalysisBackend.Jwt.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @date 下午4:40
 */
@RestController
public class UserController {
    @Autowired
    private LoginServiceImpl loginServiceimpl;
    @PassToken
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    //这种方式只能通过url传参
    public Map<String, String> Login(String username ,String password) {
        User user=new User();
        user=loginServiceimpl.findUserByUserName(username);
        if (user.getPassword().equalsIgnoreCase(password)) {
            String token= JwtTokenUtils.sign(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        }else{
            return null;
        }


    }
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public int Register(String username ,String password){
        return loginServiceimpl.addUser(username, password);
    }


}