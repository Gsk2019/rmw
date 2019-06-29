package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.service.IFeedBackService;
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
@RequestMapping("/mgr/feedback")
public class FeedBackCtl extends BaseCtl {
	
	Logger log = Logger.getLogger(FeedBackCtl.class);

	@Autowired
	IFeedBackService feedBackService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/feedback/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.feedBackService.queryFeedBackPager(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		FeedBack admin = null;
		if(StringUtil.isNotBlank(id)){
			admin = this.feedBackService.findById(Integer.valueOf(id.trim()));
			mm.put("user", admin);
		}
		return "mgr/feedback/" + action;
	}
	
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(FeedBack admin){
//		public String add(Admin admin, @RequestParam(required = false) MultipartFile headImg){
		if(admin != null ){
//			this.appUserService.addUser(admin);
		}
		return AJAX_SUCCESS;
	}
	
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(FeedBack admin){
		if(admin != null ){
//			this.appUserService.updateUser(admin);
		}
		return AJAX_SUCCESS;
	}
	
	@RequestMapping("del")
	@ResponseBody
	public String del(String ids){
		try{
			if(StringUtil.isNotBlank(ids)){
				for(String id:ids.split(",")){
					if(StringUtil.isNotBlank(id)){
						FeedBack feedBack = this.feedBackService.findById(Integer.valueOf(id));
						feedBack.setIsDelete(true);
						this.feedBackService.update(feedBack);
					}
				}
			}
			return JSONObject.toJSONString(getCode("200", "操作成功"), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
		

	}

	@RequestMapping("result")
	@ResponseBody
	public String result(String result, String id){
		try{
			return JSONObject.toJSONString(this.feedBackService.result(result, id), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}

	}

}
