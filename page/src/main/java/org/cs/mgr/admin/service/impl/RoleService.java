package org.cs.mgr.admin.service.impl;

import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.model.Role;
import org.cs.mgr.admin.service.IRoleService;
import org.cs.util.Pager;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends BaseService<Role> implements IRoleService{
	
	public Pager getRoleList(Pager page){
		return this.findPageByHql("from Role ", page, null);
	}
	
	

}
