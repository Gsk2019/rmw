package org.cs.mgr.admin.service;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.UserRole;

public interface IUserRoleService extends IBaseService<UserRole>{

	public boolean setUserRole(String roleIds, int userId);
	
}
