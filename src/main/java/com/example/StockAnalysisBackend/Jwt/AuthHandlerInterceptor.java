package com.example.StockAnalysisBackend.Jwt;

/**
 * @author Alex
 * @date 2023/4/7 上午11:20
 */
import org.springframework.http.HttpStatus;
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
        // 跨域访问处理
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "authorization,content-type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }

        // 登录和注册不需要token
        String uri = request.getRequestURI();
        if ("/UserService/login".equals(uri) || "/UserService/register".equals(uri)) {
            return true;
        }


//        Enumeration<String> headNames = request.getHeaderNames();
////        输出header中的东西
//        while(headNames.hasMoreElements()){
//
//            String headName = headNames.nextElement();
//
//            System.out.println(headName+":"+request.getHeader(headName));
//
//        }

        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);
        if (null == authorizationHeader) {
            System.out.println("缺少认证信息");
            return false;  // 这里一般都是抛出自定义异常给全局异常处理，这里为了简化不做扩展说明
        }
        //如何分发路由？
        System.out.println("header token:" + authorizationHeader);
        boolean auth = JwtTokenUtils.verity(authorizationHeader);
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