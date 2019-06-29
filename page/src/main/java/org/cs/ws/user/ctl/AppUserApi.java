package org.cs.ws.user.ctl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IRmwUserService;
import org.cs.rmw.service.ISmsService;
import org.cs.util.Md5;
import org.cs.util.StringUtil;
import org.cs.ws.AccessTokenUtil;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rest/appuser")
public class AppUserApi extends BaseCtl {

	private Logger log = Logger.getLogger(AppUserApi.class);
	
	@Autowired
	private ISmsService smsService;
	@Autowired
	private IRmwUserService rmwUserService;


	/**
	 * 登录
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/appLogin")
	public JSONObject login(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
			json = rmwUserService.login(map, request);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/checkCode")
	public JSONObject checkCode(String data){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
			if(map != null && StringUtil.isNotBlank(map.get("phone")) && StringUtil.isNotBlank(map.get("type")) && StringUtil.isNotBlank(map.get("code"))){
				json = smsService.checkCode(map.get("phone"), Integer.valueOf(map.get("type")).intValue(), map.get("code"));
			}else{
				json = getJson(RetCode.EC_0500);
			}
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	/**
	 *
	 * 请求参数
	 * 		type：1注册2忘记密码3手机号验证码登录
	 * 		phone：手机号登录
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getCode")
	public JSONObject getCode(String data){
		Map<String, String> map = getMapFromJson(data);
		
		JSONObject json = new JSONObject();
		try {
			json = smsService.getCode(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	/**
	 * 忘记密码
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/forgetPWD")
	public JSONObject forgetPWD(String data){
		Map<String, String> map = getMapFromJson(data);
		
		JSONObject json = new JSONObject();
		
		try {
			json = rmwUserService.editPwd(map);
		} catch (Exception e) {
			log.error("忘记密码修改失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	/**
	 * 修改密码
	 * @param data
	 * @return
	 */
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/modifyPWD")
	public JSONObject modifyPWD(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		String userId = getUserId(request);
		map.put("userId", userId);
		JSONObject json = new JSONObject();

		try {
			json = rmwUserService.modifyPwd(map);
		} catch (Exception e) {
			log.error("修改密码失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	@Valid(tk=false)
    @ResponseBody
    @RequestMapping("/register")
    public JSONObject register(String data, HttpServletRequest request){
        RmwUser user = getBeanFromJson(data, RmwUser.class);

        JSONObject json = new JSONObject();
        try {

            json = rmwUserService.register(user, request);

        } catch (Exception e) {
            log.error("注册失败:"+e.getMessage());
            json = getJson(0, "","注册失败");
        }
        return getJson(RetCode.SUCCESS, json);
    }


	/**
	 * 会员入住
	 * @param data
	 * @return
	 */
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/identity")
	public JSONObject identity(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		String userId = getUserId(request);
		map.put("userId", userId);
		JSONObject json = new JSONObject();

		try {
			json = rmwUserService.identity(map);
		} catch (Exception e) {
			log.error("认证失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

	/**
	 * 获取最新状态
	 * @param data
	 * @return
	 */
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/getStatus")
	public JSONObject getStatus(String data, HttpServletRequest request){
		String userId = getUserId(request);
		JSONObject json = new JSONObject();

		try {
			RmwUser rmwUser = this.rmwUserService.findById(Integer.valueOf(userId));
			json = getJson(RetCode.SUCCESS, rmwUser.getIsIdentity());
		} catch (Exception e) {
			log.error("认证失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}

    /**
     * 获取最新状态
     * @param data
     * @return
     */
    @Valid(tk=false)
    @ResponseBody
    @RequestMapping("/unionwx")
    public JSONObject unionWx(String data, HttpServletRequest request){
        JSONObject json = new JSONObject();
        Map<String, String> map = getMapFromJson(data);
        try {
            RmwUser rmwUser = this.rmwUserService.getRmwUserByPhone(map.get("phone"));
			RmwUser rmwUser1 = this.rmwUserService.getRmwUserByOpenId(request.getParameter("openid"));
			if(rmwUser1 != null){
				json = getJson(RetCode.EC_0600, "已有关联账号");
			}else{
				JSONObject j = smsService.checkCode(map.get("phone"), Integer.valueOf(map.get("type")), map.get("code"));
				if("1".equals(String.valueOf(j.get("ret")))){
					rmwUser.setOpenid(request.getParameter("openid"));
					this.rmwUserService.update(rmwUser);
					json = getJson(RetCode.SUCCESS);
				}else{
					json = getJson(RetCode.EC_0500, "请输入有效的手机验证码");
				}
			}
        } catch (Exception e) {
            log.error("关联失败："+e.getMessage());
            json = getJson(RetCode.EC_0500, "");
        }
        return json;
    }

	/**
	 * 获取最新状态
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/gettk")
	public JSONObject getTk(String data, HttpServletRequest request){
		JSONObject json = new JSONObject();
		Map<String, String> map = getMapFromJson(data);
		Map<String, Object> back = new HashMap<>();
		try {
			//{"session_key":"I66oROiliBD1mt6e9iSMBQ==","openid":"osVBs5TIOx3AW-t2hev_4i5t6HuE"}
			String openid = request.getParameter("openid");
			if(StringUtil.isBlank(openid)){
				String body = doClientGet(map.get("code"));

				if(StringUtil.isNotBlank(body)){
					back.putAll(getMapFromJson(body));
				}
			}else{
				openid = map.get("openid");
				back.put("openid",openid);
				back.put("tk", "");
			}
			if(StringUtil.isBlank(openid)){
				openid = "0";
			}
			RmwUser rmwUser = this.rmwUserService.getRmwUserByOpenId(openid);
			if(rmwUser == null){
				json = getJson(RetCode.EC_1000);
			}else {
				String tk = AccessTokenUtil.genTk(rmwUser.getId().toString(), rmwUser.getOpenid());
				AccessTokenUtil.putTk(rmwUser.getOpenid(), tk);
//				back.put("openid", openid);
				back.put("tk", tk);
				json = getJson(RetCode.SUCCESS, back);
			}
		} catch (Exception e) {
			log.error("关联失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, back);
		}
		return json;
	}

	public String doClientGet(String code){
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx3dec660d3629bc8a&secret=491ab058c89ab7341d062e8e85401462&js_code="+code+"&grant_type=authorization_code";
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实例
		GetMethod postMethod = new GetMethod(url);
//		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
//		if (pairs != null) {
////			postMethod.addParameters(pairs);
////		}
		// 执行postMethod
		try {
			//fiddler调试时使用
			//httpClient.getHostConfiguration().setProxy("127.0.0.1", 8888);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String body = postMethod.getResponseBodyAsString();
				postMethod.releaseConnection();
				return body;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
