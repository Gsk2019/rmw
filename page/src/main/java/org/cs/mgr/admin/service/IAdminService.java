package org.cs.mgr.admin.service;

import java.util.List;
import java.util.Map;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.Admin;
import org.cs.util.Pager;

public interface IAdminService extends IBaseService<Admin>{

	/**
	 * 根据账号查询用户信息
	 * @param account
	 * @return
	 */
	public Admin getByAccount(String account);
	
	public List<Map<String,Object>> getPermissions(String userCode);
	
	
	public Pager getList(Pager page);
	
	
	public boolean initPwd(int id);
	
	public List<Map<String, Object>> getRoleList(int userId);
	
	public Admin addUser(Admin user);
	
	public Admin updateUser(Admin user);
	
}
