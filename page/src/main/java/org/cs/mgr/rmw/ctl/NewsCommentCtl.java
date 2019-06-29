package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.service.INewsCommentService;
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
@RequestMapping("/mgr/comment")
public class NewsCommentCtl extends BaseCtl {

	@Autowired
	private INewsCommentService newsCommentService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/comment/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.newsCommentService.getNewsCommentPages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		NewsComment newsComment = null;
		if(StringUtil.isNotBlank(id)){
			newsComment = this.newsCommentService.findById(Integer.valueOf(id.trim()));
			mm.put("newsComment", newsComment);
		}
		return "mgr/comment/" + action;
	}


//	@RequestMapping(value="add", method = RequestMethod.POST)
//	@ResponseBody
//	public String add(NewsComment newsComment){
//		if(news != null ){
//			return this.newsCommentService.addNews(newsComment);
//		}else{
//			return JSONObject.toJSONString(getCode("500","数据异常"));
//		}
//	}

//	@RequestMapping(value="edit", method = RequestMethod.POST)
//	@ResponseBody
//	public String edit(NewsComment newsComment){
//		try{
//			if(newsComment != null ){
//				return JSONObject.toJSONString(this.newsCommentService.updateNews(newsComment), features);
//			}else{
//				return JSONObject.toJSONString(getCode("500", "未获取到新闻信息"), features);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
//		}
//	}

	@RequestMapping("del")
	@ResponseBody
	public String del(String id){
		try{
			if(StringUtil.isNotBlank(id)){
				return JSONObject.toJSONString(this.newsCommentService.deleteNewsComment_admin(id), features);
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
	public String  check(NewsComment newsComment){

		try{
			return JSONObject.toJSONString(newsCommentService.checkNewsComment_admin(newsComment), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}
}
