package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.*;
import org.cs.rmw.service.*;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mgr/info")
public class InfoCtl extends BaseCtl {

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IRmwUserService rmwUserService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IRmwRepertoryService rmwRepertoryService;
	@Autowired
	private IProductAreaService productAreaService;


	//后台分页获取现货列表
	@RequestMapping("list_sale")
	public String list_sale(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/info/sale_list";
	}

	//后台分页获取求购列表
	@RequestMapping("list_buy")
	public String list_buy(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/info/buy_list";
	}

	//后台分页获取供货列表
	@RequestMapping("list_gh")
	public String list_gh(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/info/gh_list";
	}

	//后台分页获取现货/求购列表
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.infoService.getInfoBypages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}

	//后台分页获取供货列表
	@RequestMapping(value="list3", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list3(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.infoService.queryGhInfoPages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(Integer id, String action, ModelMap mm,String type){

		List<Category> categorylist =categoryService.findCategoryByMap(new HashMap<>());
		List<RmwRepertory> rrlist =rmwRepertoryService.getAllRmwRepertory();
		List<ProductArea> palist = this.productAreaService.getAllProductArea();
		mm.put("categorylist", categorylist);
		mm.put("rrlist", rrlist);
		mm.put("palist", palist);

		Info info = null;
		if(id!=null){
			info = this.infoService.findById(id);
			RmwUser rmwUser=rmwUserService.findById(info.getUserId());
			List<Map<String,Object>> tlist=infoService.getTraders_admin();

			mm.put("info", info);
			mm.put("rmwUser", rmwUser);
			mm.put("tlist", tlist);
			if (!"check".equals(action)){
				if (info.getType()==1){
					return "mgr/info/sale_" + action;
				}else if (info.getType()==2){
					return "mgr/info/buy_" + action;
				}else{// if (info.getType()==3)
					Info qginfo=infoService.findById(info.getInfoId());
					RmwUser qgUser=rmwUserService.findById(qginfo.getUserId());
					mm.put("qginfo", qginfo);
					mm.put("qgUser", qgUser);
					return "mgr/info/gh_" + action;
				}
			}else{
				if (info.getType()==3){
					return "mgr/info/gh_" + action;
				}else {
					return "mgr/info/" + action;
				}
			}
		}else{
			if ("1".equals(type)){
				return "mgr/info/sale_"+ action;
			}else{
				return "mgr/info/buy_"+ action;
			}
		}
	}

	@RequestMapping(value="getCategoryByTopId", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCategoryByTopId(){
		try{
			JSONObject json = new JSONObject();
			List<Category> categorylist =categoryService.findCategoryByMap(new HashMap<>());
			List<ProductArea> palist = this.productAreaService.getAllProductArea();

			json.put("categorylist",categorylist);
			json.put("palist",palist);
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Info info) throws Exception {
		if(info != null ){
			return this.infoService.addInfo(info);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}

	@RequestMapping(value="addBuy", method = RequestMethod.POST)
	@ResponseBody
	public String addBuy(Info info) throws Exception {
		if(info != null ){
			return this.infoService.addBuyInfo(info);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}

	@RequestMapping(value="isExitUser", method = RequestMethod.POST)
	@ResponseBody
	public String isExitUser(String phone) throws Exception {
		return this.infoService.isExitUser(phone);
	}

	@RequestMapping("del")
	@ResponseBody
	public String del(Integer id){
		try{
			return JSONObject.toJSONString(this.infoService.deleteInfos_admin(id));
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}


	@RequestMapping(value="editBuy", method = RequestMethod.POST)
	@ResponseBody
	public String editBuy(Info info){
		try{
			if(info != null ){
				return JSONObject.toJSONString(this.infoService.updateBuyInfo_admin(info), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}

	@RequestMapping(value="editSale", method = RequestMethod.POST)
	@ResponseBody
	public String editSale(Info info){
		try{
			if(info != null ){
				return JSONObject.toJSONString(this.infoService.updateSaleInfo_admin(info), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}

	@RequestMapping(value="check", method = RequestMethod.POST)
	@ResponseBody
	public String check(Info info){
		try{
			if(info != null ){
				return JSONObject.toJSONString(this.infoService.checkInfo_admin(info), features);
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
