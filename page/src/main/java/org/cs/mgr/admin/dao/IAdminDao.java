package org.cs.mgr.admin.dao;

import java.util.List;
import java.util.Map;

import org.cs.core.dao.IBaseDao;
import org.cs.mgr.admin.model.Admin;

public interface IAdminDao extends IBaseDao<Admin> {

	public List<Map<String, Object>> findPermissions(String userCode) ;
	
	public List<Map<String, Object>> findRoleList(int userId);
	
}
