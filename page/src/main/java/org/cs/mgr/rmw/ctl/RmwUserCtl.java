package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("/mgr/user")
public class RmwUserCtl extends BaseCtl {

	@Autowired
	private IRmwUserService rmwUserService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/user/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.rmwUserService.getList(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		RmwUser rmwUser = null;
		if(StringUtil.isNotBlank(id)){
			rmwUser = this.rmwUserService.findById(Integer.valueOf(id.trim()));
			mm.put("user", rmwUser);
		}
		return "mgr/user/" + action;
	}


	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(RmwUser rmwUser){
		try{
			if(rmwUser != null ){
				return JSONObject.toJSONString(this.rmwUserService.updateRmwUser(rmwUser), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到新闻信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}

	@RequestMapping("del")
	@ResponseBody
	public String del(String id){
		try{
			if(StringUtil.isNotBlank(id)){
				return JSONObject.toJSONString(this.rmwUserService.deleteRmwUser_admin(id), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}

	@RequestMapping(value="/check",method = RequestMethod.POST)
	@ResponseBody
	public String  check(RmwUser rmwUser){

		try{
			return JSONObject.toJSONString(rmwUserService.checkRmwUser_admin(rmwUser), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}


}
