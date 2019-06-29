package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.service.ILogisticsService;
import org.cs.rmw.service.INewsService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/mgr/logistics")
public class LogisticsCtl extends BaseCtl {

	@Autowired
	private ILogisticsService logisticsService;


	//后台分页获取物流报价列表
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/logistics/list";
	}

	//后台分页获取物流委托列表
	@RequestMapping("list_wt")
	public String list_wt(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/logistics/list_wt";
	}

	//后台分页获取物流列表
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.logisticsService.getLogisticsBypages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Logistics logistics = null;
		if(StringUtil.isNotBlank(id)){
			logistics = this.logisticsService.findById(Integer.valueOf(id.trim()));
			mm.put("logistics", logistics);
		}
		return "mgr/logistics/" + action;
	}


	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Logistics logistics){
		if(logistics != null ){
			return this.logisticsService.addLogistics_admin(logistics);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}


//	@RequestMapping(value="edit", method = RequestMethod.POST)
//	@ResponseBody
//	public String edit(RmwNews admin){
//		try{
//			if(admin != null ){
//				return JSONObject.toJSONString(this.newsService.updateNews(admin), features);
//			}else{
//				return JSONObject.toJSONString(getCode("500", "未获取到产品信息"), features);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
//		}
//	}
//
	@RequestMapping("del")
	@ResponseBody
	public String del(Integer id){
		try{
			return JSONObject.toJSONString(this.logisticsService.deleteLogistics_admin(id));
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}

	@RequestMapping(value="/check",method = RequestMethod.POST)
	@ResponseBody
	public String  check(Logistics logistics){

		try{
			return JSONObject.toJSONString(logisticsService.checkLogistics_admin(logistics), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}
//
//
//	@RequestMapping(value="/updown",method = RequestMethod.POST)
//	@ResponseBody
//	public String updown(String val, String id){
//
//		try{
//			if(StringUtil.isNotBlank(val)){
//				if("1".equals(val)){
//					RmwNews news = this.newsService.findById(id);
//					news.setModifyDate(new Date());
//					news.setState("2");
//					news = this.newsService.update(news);
//				}else if("2".equals(val)){
//					RmwNews news = this.newsService.findById(id);
//					news.setModifyDate(new Date());
//					news.setState("1");
//					news = this.newsService.update(news);
//				}
//				return JSONObject.toJSONString(getCode("200", "操作成功"), features);
//			}else{
//				return JSONObject.toJSONString(getCode("500", "未获取到新闻信息"), features);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
//		}
//	}
}
