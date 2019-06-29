package org.cs.ws.firtpage.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Other;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IOtherService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.rmw.service.ISmsService;
import org.cs.util.StringUtil;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rest/home")
public class HomePageApi extends BaseCtl {

	private Logger log = Logger.getLogger(HomePageApi.class);
	
	@Autowired
	private IOtherService otherService;


	/**
	 * 首页接口
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/page")
	public JSONObject login(String data){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
			Other other = otherService.getByCode(new Other("count1", "1"));
			json.put("count1", other.getNum());
			Other other1 = otherService.getByCode(new Other("count2", "1"));
			json.put("count2", other1.getNum());
			Other other2 = otherService.getByCode(new Other("count3", "1"));
			json.put("count3", other2.getNum());
			return getJson(RetCode.SUCCESS, json);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500);
		}

	}


}
