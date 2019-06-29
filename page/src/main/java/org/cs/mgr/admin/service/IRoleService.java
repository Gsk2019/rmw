package org.cs.mgr.admin.service;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.Role;
import org.cs.util.Pager;

public interface IRoleService extends IBaseService<Role> {

	public Pager getRoleList(Pager page);
	
}
