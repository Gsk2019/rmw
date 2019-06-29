package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.mgr.admin.model.Menu;
import org.cs.mgr.admin.vo.ZTree;
import org.cs.rmw.model.Category;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.service.ICategoryService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgr/category")
public class CategoryCtl extends BaseCtl {
	
	Logger log = Logger.getLogger(CategoryCtl.class);

	@Autowired
	ICategoryService categoryService;


	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/category/list";
	}

	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(){
		List<Category> categorys = this.categoryService.getAllCategoryList();

		List<ZTree> list2 	= new ArrayList<ZTree>();
		ZTree zt = null;
		for(Category m : categorys){
			zt = new ZTree(m.getId(), m.getTopId(), m.getCategoryName() + "-" + m.getId(), m.getId().toString());
			zt.setOpen(true);
			list2.add(zt);
		}

		return JSONObject.toJSONString(list2, features);
	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Category admin = null;
		if(StringUtil.isNotBlank(id)){
			admin = this.categoryService.findById(Integer.valueOf(id));
			mm.put("category", admin);
		}
		Map<String, String> map = new HashMap<>();
		map.put("topid", "0");
		mm.put("menuList", this.categoryService.findCategoryByMap(map));

		return "mgr/category/" + action;
	}
	
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Category admin){
		try{
			if(admin != null ){
				return JSONObject.toJSONString(this.categoryService.addCategory(admin), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到要添加的信息"));
			}
		}catch (Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"));
		}
	}
	
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Category admin){
		try{

			if(admin != null ){
				return JSONObject.toJSONString(this.categoryService.updateCategory(admin), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到要编辑的信息"));
			}
		}catch (Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"));
		}
	}
	
	@RequestMapping("del")
	@ResponseBody
	public String del(String id){
		try{
			if(StringUtil.isNotBlank(id)){
				return JSONObject.toJSONString(this.categoryService.delCategory(id));
			}
			return JSONObject.toJSONString(getCode("200", "操作成功"), features);
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
		

	}

}
