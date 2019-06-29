package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import jd.qq.sms.SmsUtil;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IRmwUserDao;
import org.cs.rmw.dao.ISmsDao;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.model.SMS;
import org.cs.rmw.service.ISmsService;
import org.cs.util.AliMsgUtils;
import org.cs.util.RandomUtil;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("smsService")
public class SmsService extends BaseService<SMS> implements ISmsService {
	private Logger log = Logger.getLogger(SmsService.class);

	@Autowired 
	private ISmsDao smsDao;
	@Autowired 
	private IRmwUserDao rmwUserDao;

	@Override
	@Transactional(readOnly=false)
	public JSONObject getCode(Map<String, String> map) {
		if(StringUtil.isBlank(map.get("type").trim())){
			return getJson(RetCode.EC_0502, "");
		}
		int type = Integer.parseInt(map.get("type")+"");
		String phone = map.get("phone")+"";
		JSONObject json = new JSONObject();
		String code = RandomUtil.getSixNum().toLowerCase();
		RmwUser appUser = rmwUserDao.getByPhone(phone,null);

    	if (type==1) {//注册,check is unique
    		if (null != appUser) {
    			return getJson(RetCode.EC_0501, "");
    		}
			SMS sms = new SMS();
			sms.setCode(code);
			sms.setPhone(phone);
	        sms.setValidTime(60*1000*5);
	        sms.setType(type);
	        add(sms);
	        json = getJson(RetCode.SUCCESS, code);
    	} else if (type==2 || type==3 || type==4) {//忘记密码｜手机号登录验证码
    		if(appUser==null){
    			return getJson(RetCode.EC_0400, "");
    		}
			SMS sms = new SMS();
			sms.setCode(code);
			sms.setPhone(phone);
	        sms.setValidTime(60*1000*5);
	        sms.setType(type);
	        add(sms);
	        json = getJson(RetCode.SUCCESS, "");
    	}else{
			return getJson(RetCode.EC_0502, "");
		}
    	
    	if("1".equals(json.get("ret")+"")){
    		try{
    			Boolean sendCode = AliMsgUtils.sendSms(code,phone,"SMS_140670066");
				if(!sendCode){
					log.info("验证码获取失败,失败码:"+code+"。发送目标"+phone);
					json = getJson(RetCode.EC_0500 ,"");
				}
    		}catch(Exception e){
				log.info("验证码获取失败,失败码:"+code+"。发送目标"+phone);
				json = getJson(RetCode.EC_0500 ,"");
			}
    	}
    	return json;
	}

	@Override
	public JSONObject checkCode(String phone,int type,String code) {
		
		JSONObject json = new JSONObject();
		
		SMS sms = smsDao.getSMSByPhone(phone,code.toLowerCase() , type);
    	
    	if (sms==null || !code.toLowerCase().equals(sms.getCode().toLowerCase())) {
    		json = getJson(RetCode.EC_0503, "");
    	} else {
    		long nowTime = System.currentTimeMillis();
    		if ((sms.getCreateDate().getTime() + sms.getValidTime()) < nowTime) {
    			json = getJson(RetCode.EC_0504, "");
    		} else {
    			json = getJson(RetCode.SUCCESS, "");
    		}
    	}
		return json;
	}

	
	
}
