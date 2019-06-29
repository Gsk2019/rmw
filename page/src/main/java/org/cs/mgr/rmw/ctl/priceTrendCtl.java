package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.IPriceTrendService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgr/priceTrend")
public class priceTrendCtl extends BaseCtl {

	@Autowired
	private IPriceTrendService priceTrendService;

	//后台分页获取列表
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/priceTrend/list";
	}


	//后台分页获取列表数据
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.priceTrendService.getPriceTrendBypages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(Integer id, String action, ModelMap mm){
		PriceTrend priceTrend = null;
		if(id!=null){
			priceTrend = this.priceTrendService.findById(id);
			mm.put("priceTrend", priceTrend);
		}
		return "mgr/priceTrend/" + action;
	}

	@RequestMapping("del")
	@ResponseBody
	public String del(Integer id){
		try{
			return JSONObject.toJSONString(this.priceTrendService.deletePriceTrend_admin(id));
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}


	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(PriceTrend priceTrend){
		try{
			if(priceTrend != null ){
				return JSONObject.toJSONString(this.priceTrendService.updatePriceTrend_admin(priceTrend), features);
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
