package org.cs.mgr.admin.dao;

import java.util.List;
import java.util.Map;

import org.cs.core.dao.IBaseDao;
import org.cs.mgr.admin.model.Menu;
import org.cs.util.Pager;

public interface IMenuDao extends IBaseDao<Menu> {

	public String getNewSuperMenuCode();
	
	public String getNewSecMenuCode(Menu m);
	
	public Pager getSuperMenuList(Pager page);
	
	public List<Menu> getSuperMenuList() ;
	
	public List<Menu> getSecMenuList();
	
	public List<Map<String,Object>>  getRoleMenu(int roleId);
	
	public List<Menu> getMenuList();
	
}
