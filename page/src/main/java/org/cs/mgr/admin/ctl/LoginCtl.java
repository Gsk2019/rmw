package org.cs.mgr.admin.ctl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cs.constants.Constants;
import org.cs.core.ctl.BaseCtl;
import org.cs.mgr.admin.model.Admin;
import org.cs.mgr.admin.service.IAdminService;
import org.cs.mgr.admin.service.IMenuService;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Md5;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgr")
public class LoginCtl extends BaseCtl{

	private  Logger log = Logger.getLogger(LoginCtl.class);
	
	private static String LOGIN = "redirect:/";
	private static String LOGIN_SUCCESS = "redirect:/mgr/main.cs";
	
	@Autowired 
	private IMenuService menuService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IRmwUserService rmwUserService;
	@Autowired
	private IInfoService infoService;
	
	
	
	@RequestMapping("index")
	public String index(ModelMap mm){
		Map<String,Object> userMap=rmwUserService.getTjData();
		mm.put("userMap",userMap);
		List<Map<String,Object>> xhList1=infoService.getTjData(1,"1");
		List<Map<String,Object>> xhList2=infoService.getTjData(2,"1");
		List<Map<String,Object>> qgList1=infoService.getTjData(1,"2");
		List<Map<String,Object>> qgList2=infoService.getTjData(2,"2");
		mm.put("xhList1",xhList1);
		mm.put("xhList2",xhList2);
		mm.put("qgList1",qgList1);
		mm.put("qgList2",qgList2);
		return "index";
	}
	
	@RequestMapping("login")
	public String login(String account, String pwd, String code,  ModelMap mm){
		
		
		if(StringUtil.isBlank(account, pwd)){
			log.error("登录信息错误");
			return  LOGIN;
		}
		
		Admin user = this.adminService.getByAccount(account);
		
		if(user == null){
			log.error("账户信息不存在");
			return  LOGIN;
		}
		
		if(user.getPwd().equals(Md5.getMd5(pwd.trim() + account.trim()))){
			this.set(Constants.SESSION_USER, user);   // 用户信息
			return LOGIN_SUCCESS;
		}
		else {
			log.error("密码错误");
			return  LOGIN;
		}
		
	}
	
	
	@RequestMapping("main")
	public String main(ModelMap mm){
		
		// 初始化左侧菜单及权限信息
		Admin user = (Admin) this.get(Constants.SESSION_USER);
		
		if(user == null) {
			return "redirect:/";
		}
		
		List<Map<String,Object>> permissions =  this.adminService.getPermissions(user.getCode());
		
		this.set(Constants.SESSION_PERMISSIONS, permissions);  //权限信息
		
		mm.put("user", user);
		mm.put("roleName", permissions.get(0).get("role_name"));
		mm.put(Constants.SESSION_PERMISSIONS, permissions);
		 return "main";
	}

	@RequestMapping("logout")
	public String login(ModelMap mm){
		
		 return "redirect:/";
	}
	
}
