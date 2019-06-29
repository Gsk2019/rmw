package org.cs.ws.user.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.rmw.service.IRmwUserInfoService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/rest/userinfo")
public class RmwUserInfoApi extends BaseCtl {

	private Logger log = Logger.getLogger(RmwUserInfoApi.class);
	
	@Autowired
	private IRmwUserInfoService rmwUserInfoService;

	@Valid(tk=true)
	@RequestMapping(value="addUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addUserInfo(String data, HttpServletRequest request){
		if(StringUtil.isNotBlank(data)){
			Map<String, String> map = getMapFromJson(data);
			map.put("userId", request.getAttribute("userId").toString());
			return this.rmwUserInfoService.addRmwUserInfo(map);
		}
		return getJson(RetCode.SUCCESS);
	}
	

	
		
}

