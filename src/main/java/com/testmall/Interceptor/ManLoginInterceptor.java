package com.testmall.Interceptor;

import com.testmall.Tools.CharsetTool;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    此為管理員登入攔截器，用於用戶的請求處理，有三種方法
    preHandle:在執行Handler方法前執行，即用戶透過api網址傳送請求進入Controller前會先執行此方法，可以對請求進行判斷，
              決定程式是否要繼續執行，或進行一些前置初始化操作及對請求進行預處理
    postHandle:在執行Handler之後，返回ModelAndView之前執行，此方法多被用於處理共同畫面，例如添加導航欄到頁面
    afterCompletion:在執行完Handler之後執行，既Controller執行完畢後執行此方法，適合進行統一的異常或日誌處理
    要在指定網頁使用此攔截器需在WebConfig.java內的addInterceptors方法進行設定
*/
@Component
public class ManLoginInterceptor implements HandlerInterceptor {
    CharsetTool cstool = new CharsetTool();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("manAccount") == null) {
            //cstool.pLogln("session中的manAccount為null");
            response.setStatus(403);
            return false;
        }
        //cstool.pLogln("session中的manAccount為= " + request.getSession().getAttribute("manAccount"));
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        //todo
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
//        //todo
//    }
}
