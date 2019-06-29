package org.cs.ws.inteceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cs.mgr.util.SystemUtils;
import org.cs.ws.AccessTokenUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PutTokenToResponseBody implements ResponseBodyAdvice<Object> {

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType){
        return true;
    }


    public Object beforeBodyWrite(Object body1, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response){


        JSONObject body = null;

        //只有接口返回JSONObject
        if(body1 instanceof JSONObject){
            body = (JSONObject)body1;
            ServletServerHttpRequest req = (ServletServerHttpRequest) request;
            HttpServletRequest requ = req.getServletRequest();
            String plat = requ.getParameter("plat");

            if("web".equals(plat)){
                String tk = requ.getParameter("tk");

                boolean boo =  (boolean)requ.getAttribute("isvalid");

                if(boo){
                    String userId = requ.getAttribute("userId").toString();
                    tk = AccessTokenUtil.genTk(Integer.valueOf(userId), SystemUtils.getUserAgment(requ));
                    AccessTokenUtil.putTk(Integer.valueOf(userId), tk);
                    body.put("tk", tk);
                }
            }
            return body;
        }else{
            return body1;
        }

    }
}
