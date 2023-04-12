package com.example.StockAnalysisBackend.Controller;

import com.example.StockAnalysisBackend.Impl.UserServiceImpl;
import com.example.StockAnalysisBackend.Jwt.JwtAuthenticationResponse;
import com.example.StockAnalysisBackend.Jwt.TokenProvider;
import com.example.StockAnalysisBackend.POJO.LoginRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex
 * @date 2023/4/11 上午11:39
 */
@RestController

@RequestMapping("/api")
public class AuthenticController {
    @Autowired
    private UserServiceImpl userServiceimpl;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        //前端的对象关系映射
        Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
       //在上下文中存储Authentic对象 里面的内容就是用户名和密码
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TokenProvider.generateToken(authentication);
        System.out.println(ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    private Authentication authenticate(String username, String password) {
        return userServiceimpl.authenticate(username, password);
    }
    @ApiOperation(value = "查询用户")
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

}
