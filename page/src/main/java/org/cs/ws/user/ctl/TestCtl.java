package org.cs.ws.user.ctl;

import java.util.UUID;

import org.cs.core.ctl.BaseCtl;
import org.cs.ws.AccessTokenUtil;
import org.cs.ws.inteceptor.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestCtl extends BaseCtl{

	@Valid(tk=false)
	@RequestMapping(value="/login",method = RequestMethod.POST)
//	@ResponseBody
	public String login(){
		
		String userId = UUID.randomUUID().toString().replaceAll("-", "");
		
//		String tk = AccessTokenUtil.genTk(userId);
//		AccessTokenUtil.putTk(userId, tk);
		
		return "";
	}
	
}
