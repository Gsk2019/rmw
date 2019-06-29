package org.cs.mgr.rmw.ctl;

import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.RmwRepertory;
import org.cs.rmw.service.IRmwRepertoryService;
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
@RequestMapping("/mgr/rmwRepertoryCtl")
public class RmwRepertoryCtl extends BaseCtl {

	private Logger log = Logger.getLogger(RmwRepertoryCtl.class);
	
	@Autowired
	private IRmwRepertoryService rmwRepertoryService;
	
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/rmwRepertory/list";
	}
	
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize){
		Pager pager = this.rmwRepertoryService.getRmwRepertorys(new Pager(page, pageSize));
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	
	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		RmwRepertory rmwRepertory = null;
		if(StringUtil.isNotBlank(id)){
			rmwRepertory = this.rmwRepertoryService.findById(Integer.valueOf(id.trim()));
			mm.put("rmwRepertory", rmwRepertory);
		}
		return "mgr/rmwRepertory/" + action;
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(RmwRepertory rmwRepertory){
		if(rmwRepertory != null ){
			this.rmwRepertoryService.addRmwRepertory(rmwRepertory);
		}
		return AJAX_SUCCESS;
	}
	
	
	
	@RequestMapping("del")
	@ResponseBody
	public String del(String id){
		if(StringUtil.isNotBlank(id)){
			this.rmwRepertoryService.delRmwRepertory(id);
		}
		
		return AJAX_SUCCESS;
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(RmwRepertory admin){
		try{
			if(admin != null ){
				this.rmwRepertoryService.updateRmwRepertory(admin);
				return JSONObject.toJSONString(getCode("200", "操作成功"), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到地址信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}
	
		
}

