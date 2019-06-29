package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.IRmwUserInfoService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Pager;
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
@RequestMapping("/mgr/userInfo")
public class UserInfoCtl extends BaseCtl {

	@Autowired
	private IRmwUserInfoService rmwUserInfoService;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private IRmwUserService rmwUserService;

	//后台分页获取现货列表
	@RequestMapping("list")
	public String list_sale(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/info/ui_list";
	}

	//后台分页获取现货/求购列表
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.rmwUserInfoService.getRmwUserInfoPages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(Integer id, String action, ModelMap mm){
		RmwUserInfo rmwUserInfo = null;
		if(id!=null){
			rmwUserInfo = this.rmwUserInfoService.findById(id);
			RmwUser rmwUser=rmwUserService.findById(rmwUserInfo.getUserId());//委托人
			Info info=infoService.findById(rmwUserInfo.getInfoId());
			RmwUser saleUser=rmwUserService.findById(info.getUserId());//被委托人
			mm.put("info", info);
			mm.put("rmwUser", rmwUser);
			mm.put("saleUser", saleUser);
			mm.put("rmwUserInfo", rmwUserInfo);
			return "mgr/info/ui_" + action;
		}
		return "";
	}

	@RequestMapping("del")
	@ResponseBody
	public String del(Integer id){
		try{
			return JSONObject.toJSONString(this.rmwUserInfoService.deleteRmwUserInfo_admin(id));
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}


	@RequestMapping(value="check", method = RequestMethod.POST)
	@ResponseBody
	public String check(RmwUserInfo rmwUserInfo){
		try{
			if(rmwUserInfo != null ){
				return JSONObject.toJSONString(this.rmwUserInfoService.checkRmwUserInfo_admin(rmwUserInfo), features);
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
