package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Other;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.service.IOtherService;
import org.cs.rmw.service.IPriceTrendService;
import org.cs.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/mgr/other")
public class OtherCtl extends BaseCtl {

	@Autowired
	private IOtherService otherService;

	//后台获取三个统计数据
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/other/list_tj";
	}


	//后台分页获取列表数据
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		map.put("type","1");
		Pager pager = this.otherService.queryOtherTj_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(Integer id, String action, ModelMap mm){
		Other other = null;
		if(id!=null){
			other = this.otherService.findById(id);
			mm.put("other", other);
		}
		return "mgr/other/" + action;
	}



	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Other other ){
		try{
			if(other != null ){
				return JSONObject.toJSONString(this.otherService.updateOther_admin(other), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
