package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.Constants;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.NewsType;
import org.cs.rmw.service.INewsTypeService;
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
@RequestMapping("/mgr/newsType")
public class NewsTypeCtl extends BaseCtl {

	@Autowired
	private INewsTypeService newsTypeService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		Object obj=this.get(Constants.SESSION_PERMISSIONS);
		if (obj==null)
			return "mgr/login";
		mm.put(MENU_CODE, menuCode);
		return "mgr/newsType/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.newsTypeService.getNewsTypePages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		NewsType admin = null;
		if(StringUtil.isNotBlank(id)){
			admin = this.newsTypeService.findById(Integer.valueOf(id.trim()));
			mm.put("newsType", admin);
		}
		return "mgr/newsType/" + action;
	}


	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(NewsType newsType){
		if(newsType != null ){
			return this.newsTypeService.addNewsType(newsType);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(NewsType newsType){
		try{
			if(newsType != null ){
				return JSONObject.toJSONString(this.newsTypeService.updateNewsType(newsType), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到新闻分类信息"), features);
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
				return JSONObject.toJSONString(this.newsTypeService.deleteNewsType_admin(id), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}
}
