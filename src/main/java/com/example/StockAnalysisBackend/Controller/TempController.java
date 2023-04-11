package com.example.StockAnalysisBackend.Controller;

import com.example.StockAnalysisBackend.Jwt.UserLoginToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @date 2023/4/6 下午1:32
 */
@RestController

public class TempController {
    @UserLoginToken
    @RequestMapping("/hello")
    public String sayhello(){
        return "hello";
    }
}
