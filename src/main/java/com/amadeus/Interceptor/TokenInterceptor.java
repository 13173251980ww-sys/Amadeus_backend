package com.amadeus.Interceptor;

import com.amadeus.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.Map;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    //获取请求url
   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{

       if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
           log.info("OPTIONS 预检请求，直接放行");
           return true;
       }

        //1.获取请求url
       String url=request.getRequestURI().toString();

       //2.判断请求url中是否包含login，如果包含，则说明是登录操作，放行
       if(url.contains("login")){
           log.info("登录请求,直接放行");
           return true;
       }

       //3.获取请求头中的令牌(token)
       String jwt=request.getHeader("token");

       log.info("令牌为{}",jwt);
       //4.验证令牌是否为空
       if(jwt==null||jwt.isEmpty()){
           log.info("获取到的令牌为空，返回错误结果");
           response.setStatus(401);
           System.out.println(response.getStatus());
           return false;
       }

       // ⭐ 去除前后的引号
       if (jwt.startsWith("\"") && jwt.endsWith("\"")) {
           jwt = jwt.substring(1, jwt.length() - 1);
           log.info("去除引号后的令牌: {}", jwt);
       }

       //5.解析token，如果解析失败，返回错误结果
       try{
           JwtUtil.parseJWT(jwt);
       }catch(Exception e){
           e.printStackTrace();
           log.info("令牌解析失败，返回错误结果");
           response.setStatus(401);
          return false;
       }

       //6.放行
       log.info("令牌合法，放行");
       log.info(String.valueOf(response.getStatus()));
       return true;
   }
}
