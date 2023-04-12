package com.example.StockAnalysisBackend.Jwt;

/**
 * @author Alex
 * @date 2023/4/7 上午11:20
 */
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//jwt拦截器
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器.......");
        Enumeration<String> headNames = request.getHeaderNames();

        while(headNames.hasMoreElements()){

            String headName = headNames.nextElement();

            System.out.println(headName+":"+request.getHeader(headName));

        }

        String token = request.getHeader("token");
        if (null == token) {
            System.out.println("缺少认证信息");
            return false;  // 这里一般都是抛出自定义异常给全局异常处理，这里为了简化不做扩展说明
        }
        //如何分发路由？
        System.out.println("header token:" + token);
        boolean auth = JwtTokenUtils.verity(token);
        System.out.println("认证结果:" + auth);
        return auth;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}