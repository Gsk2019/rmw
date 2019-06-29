package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.Constants;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.service.INewsService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgr/news")
public class NewsCtl extends BaseCtl {

	@Autowired
	private INewsService newsService;

	@Autowired
	private INewsTypeService newsTypeService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		Object obj=this.get(Constants.SESSION_PERMISSIONS);
		if (obj==null)
			return "mgr/login";
		mm.put(MENU_CODE, menuCode);
		return "mgr/news/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.newsService.getNewsPages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		RmwNews admin = null;
		List<?> typeList=newsTypeService.getAllNewsType_admin();
		if(StringUtil.isNotBlank(id)){
			admin = this.newsService.findById(Integer.valueOf(id.trim()));
			mm.put("news", admin);
		}
		mm.put("typeList", typeList);
		return "mgr/news/" + action;
	}


	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(RmwNews news){
		if(news != null ){
			return this.newsService.addNews(news);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(RmwNews news){
		try{
			if(news != null ){
				return JSONObject.toJSONString(this.newsService.updateNews(news), features);
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
				return JSONObject.toJSONString(this.newsService.deleteNews_admin(id), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}


	@RequestMapping(value="/updown",method = RequestMethod.POST)
	@ResponseBody
	public String updown(String val, String id){

		try{
			if(StringUtil.isNotBlank(val)){
				if("1".equals(val)){
					RmwNews news = this.newsService.findById(Integer.valueOf(id));
					news.setModifyDate(new Date());
					news.setState("2");
					news = this.newsService.update(news);
				}else if("2".equals(val)){
					RmwNews news = this.newsService.findById(Integer.valueOf(id));
					news.setModifyDate(new Date());
					news.setState("1");
					news = this.newsService.update(news);
				}
				return JSONObject.toJSONString(getCode("200", "操作成功"), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到新闻信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}
}
