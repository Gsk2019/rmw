package org.cs.ws.inteceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd.util.PropUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cs.core.util.SpringUtil;
import org.cs.mgr.util.SystemUtils;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.AES;
import org.cs.util.StringUtil;
import org.cs.ws.AccessTokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

public class AuthCheckInteceptor extends HandlerInterceptorAdapter {

	Logger log = Logger.getLogger(AuthCheckInteceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		request.getRequestURI();
		HandlerMethod methodHandler=(HandlerMethod) handler;
		Valid valid= methodHandler.getMethodAnnotation(Valid.class);
		String data = request.getParameter("data");
		String errorMsg = null;
		if(valid != null) {
			String plat = request.getParameter("plat");
			String tk = request.getParameter("tk");
			if("web".equals(plat)){
				String userAgment = SystemUtils.getUserAgment(request);
                request.setAttribute("userAgent", userAgment);

                request.setAttribute("isvalid", valid.tk());

                if(valid.tk()){

					request.setAttribute("tk", tk);

                    String[] user = checkTkBackUserIdByWeb(tk, userAgment);

                    if(user == null){
                        log.info("token非法。data:" + data);
                        errorMsg = "登录已过期或未登录,请先登录";
                    }else{
                    	if(userAgment.equals(user[1])){
                    		request.setAttribute("userId", user[0]);
						}else{
							log.info("用户访问来源。data:" + data);
							errorMsg = "登录已过期或未登录,请先登录";
						}
                    }
                }

			}else if("wx".equals(plat)){
				String userAgment = SystemUtils.getUserAgment(request);
				request.setAttribute("userAgent", userAgment);

				request.setAttribute("isvalid", valid.tk());

				if(valid.tk()){

					String openid = request.getParameter("openid");
                    if(StringUtil.isBlank(openid)){openid = "0";}
					request.setAttribute("tk", tk);

					String user = checkTkBackUserIdByWx(tk, openid);

					if(StringUtil.isBlank(user)){
						IRmwUserService userService =  (IRmwUserService) SpringUtil.getBean("rmwUserService");
						RmwUser rmwUser = userService.getRmwUserByOpenId(openid);
						if(rmwUser == null){
							log.info("签名缺失或请求参数与签名不符");
							errorMsg = "非法请求";
						}else{
							tk = AccessTokenUtil.genTk(rmwUser.getId().toString(), openid);
							AccessTokenUtil.putTk(openid, tk);
							request.setAttribute("userId", rmwUser.getId());
						}
					}else{
						request.setAttribute("userId", user);
					}
				}

			}else if("androids".equals(plat) || "ioss".equals(plat)){

				String method = ((HttpServletRequest)request).getMethod();
				String url = ((HttpServletRequest)request).getRequestURL().toString();

				String timestamp = request.getParameter("timestamp");
				String v = request.getParameter("v");
	//			String data = request.getParameter("data");
				String sign = request.getParameter("sign");
				tk = tk == null ? "":tk;

				if(StringUtils.isNumeric(timestamp)){
					int out = 5;
					if(System.currentTimeMillis()<(Long.valueOf(timestamp) + (out * 60 * 1000))){
						if(!validateSign(timestamp, plat, v, data, sign, tk)){
							log.info("签名缺失或请求参数与签名不符");
							errorMsg = "非法请求";
						}
						else{
							if(valid.tk()){
								String userId = checkTkBackUserId(tk);
								if(StringUtil.isBlank(userId)){
									log.info("token非法。data:" + data);
									errorMsg = "非法请求";
								}else{
									request.setAttribute("userId", userId);
								}
							}
						}
					}else{
						errorMsg  = "请求已过期";
						log.info("请求已过期");
					}
				}else{
					errorMsg = "请求已过期";
					log.info("参数timestamp不是有效的数字");
				}
			}else{
                errorMsg = "未知请求";
                log.info("未知请求");
            }

		}
				
		if (errorMsg != null) {
			JSONObject json = new JSONObject();
			json.put("msg", errorMsg);
			json.put("ret", 1000);//1000代表过期和非法,从新登陆
			writeToJson((HttpServletResponse) response, json);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * 
	 * @param timestamp
	 * @param plat
	 * @param v
	 * @param data
	 * @param sign
	 * @param tk
	 * @return
	 */
	private boolean validateSign(String timestamp, String plat, String v,String data, String sign, String tk){
		
		String key = PropUtil.getString("ws.request.sign.key");
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append("timestamp").append(timestamp);
		builder.append("plat").append(plat);
		builder.append("v").append(v);
		builder.append("data").append(data);
//		builder.append("tk").append(tk);
		builder.append(key);
		if(sign != null && sign.equals(AES.getMd5(builder.toString()))){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 验证token是否可用
	 * @param tk
	 * @return
	 */
	private boolean checkTk(String tk){
//		ISignService signService =  (ISignService) SpringUtil.getBean("signService");
		String userId = AccessTokenUtil.getUserIdByTk(tk);
		if(userId != null){
			if(AccessTokenUtil.getTk(userId).equals(tk)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证token是否可用
	 * @param tk
	 * @return
	 */
	private String checkTkBackUserId(String tk){
//		ISignService signService =  (ISignService) SpringUtil.getBean("signService");
		String userId = AccessTokenUtil.getUserIdByTk(tk);
		if(userId != null){
			if(AccessTokenUtil.getTk(userId).equals(tk)){
				return userId;
			}
		}
		return "";
	}

	/**
	 * web验证token是否可用
	 * @param tk
	 * @return
	 */
	private String[] checkTkBackUserIdByWeb(String tk, String userAgent){
//		ISignService signService =  (ISignService) SpringUtil.getBean("signService");
		String[] user = AccessTokenUtil.getUserIdByTkByWeb(tk);
		if(user != null && user.length == 2){
			if(AccessTokenUtil.getTk(user[0]).equals(tk) && userAgent.equals(user[1])){
				return user;
			}
		}
		return null;
	}

	/**
	 * wx验证token是否可用
	 * @param tk
	 * @return
	 */
	private String checkTkBackUserIdByWx(String tk, String openid){
//		ISignService signService =  (ISignService) SpringUtil.getBean("signService");
		String[] user = AccessTokenUtil.getUserIdByTkByWx(tk);

		if(user != null && user.length == 2){
			if(AccessTokenUtil.getTk(user[0]).equals(tk)){
				return user[1];
			}
		}
		return null;
	}

	private void writeToJson(HttpServletResponse response, JSONObject json) throws IOException{
		response.setContentType("application/x-json");  
        PrintWriter out= response.getWriter();
        out.print(json.toJSONString());  
        out.close();
	}

}
