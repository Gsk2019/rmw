package org.cs.mgr.admin.service;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.RoleMenu;

import com.alibaba.fastjson.JSONArray;

public interface IRoleMenuService extends IBaseService<RoleMenu> {

	public boolean setPermission(int roleId, JSONArray ja);
	
}
