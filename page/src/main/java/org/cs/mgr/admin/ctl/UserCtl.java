package org.cs.mgr.admin.ctl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.mgr.admin.model.Admin;
import org.cs.mgr.admin.service.IAdminService;
import org.cs.mgr.admin.service.IUserRoleService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserCtl extends BaseCtl{
	
	Logger log = Logger.getLogger(UserCtl.class);
	
	@Autowired
	IAdminService adminService;
	
	
	@Autowired
	IUserRoleService userRoleService;
	
	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "sys/user/list";	
	}
	
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Pager pager = this.adminService.getList(new Pager(page, pageSize));
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Admin admin = null;
		if(StringUtil.isNotBlank(id)){
			admin = this.adminService.findById(Integer.valueOf(id.trim()));
			mm.put("user", admin);
		}
		return "sys/user/" + action;
	}
	
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Admin admin){
//		public String add(Admin admin, @RequestParam(required = false) MultipartFile headImg){
		if(admin != null ){
			this.adminService.addUser(admin);
		}
		return AJAX_SUCCESS;
	}
	
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Admin admin){
		if(admin != null ){
			this.adminService.updateUser(admin);
		}
		return AJAX_SUCCESS;
	}
	
	@RequestMapping("del")
	@ResponseBody
	public String del(int id){
		
//		mm.put("superMenuList", this.menuService.getSuperMenuList());
		if(id != 0){
			this.adminService.deleteById(id);
		}
		
		return AJAX_SUCCESS;
	}
	
	
	@RequestMapping("getRoleList")
	@ResponseBody
	public String getRoleList(int userId){
		List<Map<String, Object>> list = this.adminService.getRoleList(userId);
		return JSONObject.toJSONString(list, features);
	}
	
	@RequestMapping("initPwd")
	@ResponseBody
	public String initPwd(int id){
		
		this.adminService.initPwd(id);
		
		return AJAX_SUCCESS;
	}
	
	@RequestMapping("setUserRole")
	@ResponseBody
	public String setUserRole(String roleCodes, int userId){
		
		this.userRoleService.setUserRole(roleCodes, userId);
		
		return AJAX_SUCCESS;
	}
	
	

}
