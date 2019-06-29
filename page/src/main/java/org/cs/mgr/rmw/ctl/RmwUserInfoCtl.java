package org.cs.mgr.rmw.ctl;

import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.rmw.service.IRmwUserInfoService;
import org.cs.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/rmwUserInfoCtl")
public class RmwUserInfoCtl extends BaseCtl {

	private Logger log = Logger.getLogger(RmwUserInfoCtl.class);
	
	@Autowired
	private IRmwUserInfoService rmwUserInfoService;
	
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "bis/rmwuserinfo/list";	
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize){
//		Pager pager = this.rmwUserInfoService.getRmwUserInfos(new Pager(page, pageSize));
		JSONObject json = new JSONObject();
//		json.put("rows", pager.getResults());
//		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);

	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		RmwUserInfo rmwUserInfo = null;
		if(StringUtil.isNotBlank(id)){
			rmwUserInfo = this.rmwUserInfoService.findById(id.trim());
			mm.put("rmwUserInfo", rmwUserInfo);
		}
		return "bis/rmwUserInfo" + action;
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(RmwUserInfo rmwUserInfo){
		if(rmwUserInfo != null ){
			this.rmwUserInfoService.add(rmwUserInfo);
		}
		return AJAX_SUCCESS;
	}
	
	
	
	@RequestMapping("del")
	@ResponseBody
	public String del(String id){
		if(StringUtil.isNotBlank(id)){
			this.rmwUserInfoService.deleteById(id);
		}
		
		return AJAX_SUCCESS;
	}
	
	
	
		
}

