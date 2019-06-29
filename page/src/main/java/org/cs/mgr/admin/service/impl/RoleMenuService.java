package org.cs.mgr.admin.service.impl;

import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.dao.IRoleDao;
import org.cs.mgr.admin.dao.IRoleMenuDao;
import org.cs.mgr.admin.model.Role;
import org.cs.mgr.admin.model.RoleMenu;
import org.cs.mgr.admin.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service("roleMenuService")
public class RoleMenuService extends BaseService<RoleMenu> implements IRoleMenuService{

	@Autowired
	public IRoleDao roleDao;
	
	@Autowired
	public IRoleMenuDao roleMenuDao;
	
	
	@Override
	@Transactional(readOnly=false)
	public boolean setPermission(int roleId, JSONArray ja){
		Role role = this.roleDao.findById(roleId);
		this.roleMenuDao.deleteByWhere(" and role_code='" + role.getCode() + "'");  // 清除该角色权限信息
		if(role != null) {
			RoleMenu rm = null;
			for(int i = 0; i < ja.size(); i++){
				rm = new RoleMenu();
				JSONObject jo = ja.getJSONObject(i);
				rm.setMenuCode(ja.getJSONObject(i).getString("menu_code"));
				rm.setActionVal(Integer.valueOf(jo.getIntValue("action_val")));
				rm.setRoleCode(role.getCode());
				this.roleMenuDao.insert(rm);
			}
			return true;
		}
		return false;
	}
	
}
