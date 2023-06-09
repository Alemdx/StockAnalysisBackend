package com.example.StockAnalysisBackend.Controller;

import com.example.StockAnalysisBackend.Entity.User;
import com.example.StockAnalysisBackend.Jwt.JwtTokenUtils;
import com.example.StockAnalysisBackend.Service.LoginService;
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
@RequestMapping("/UserService")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    //这种方式只能通过url传参
    public Map<String, String> Login(String username ,String password) {
        User user=new User();
        user=loginService.findUserByUserName(username);
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
    public int Register(String username ,String password,String email){
        return loginService.addUser(username, password,email);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改账户")
    public int UpdateUser(String username,String password){
        return loginService.updateUser(username,password);
    }

//    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTY4MTU0NTAyNiwidXNlcm5hbWUiOiJBbGV4In0.nESIigCVPe6aAP1mraFoMSgQTCZUkA-wQyimDprF5cs
}