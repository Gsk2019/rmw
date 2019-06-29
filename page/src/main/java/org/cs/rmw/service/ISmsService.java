package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.SMS;

import java.util.Map;

public interface ISmsService extends IBaseService<SMS> {

	/**
	 * 
	 * @param map{phone:手机号，type:1注册 2忘记密码}
	 */
	public JSONObject getCode(Map<String, String> map);
	/**
	 * 验证码校验
	 * @param phone:手机号
	 * @param type:1注册 2忘记密码
	 * @param code:验证码
	 * @return
	 */
	public JSONObject checkCode(String phone, int type, String code);
}
