//package com.shm.miaosha.interceptor;
//
//import com.shm.miaosha.result.CodeMsg;
//import com.shm.miaosha.result.Result;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @Auther: shm
// * @Date: 2019/6/4
// * @Description: com.shm.miaosha.interceptor
// * @version: 1.0
// */
//@Component
//public class UserInterceptor extends HandlerInterceptorAdapter {
//
//    public UserInterceptor() {
//        super();
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Result<Integer> result = isLogin(handler, request);
//        if (request == null){
//            return true;
//        }
//
//        // 如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private  Result<Integer> isLogin(Object handler,HttpServletRequest request){
//        if (handler instanceof HandlerMethod){
//            //方法注解级拦截器
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            // 判断接口是否需要登录
//            NeedLogin methodAnnotation = method.getAnnotation(NeedLogin.class);
//            if (methodAnnotation != null){
//                String user = request.getParameter("user");
//                if (user == null) {
//                    return Result.error(CodeMsg.SESSION_ERROR);
//                }
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        super.afterConcurrentHandlingStarted(request, response, handler);
//    }
//}
