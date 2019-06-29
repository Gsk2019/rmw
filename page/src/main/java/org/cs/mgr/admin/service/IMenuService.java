package org.cs.mgr.admin.service;

import java.util.List;
import java.util.Map;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.Menu;
import org.cs.util.Pager;

public interface IMenuService extends IBaseService<Menu> {

	public  List<Menu> getMenuList();
	
	public Pager getSuperMenuList(Pager page);
	
	public List<Menu> getSuperMenuList();
	
	public List<Menu> getSecMenuList();
	
	public List<Map<String,Object>> getRoleMenu(int roleId);

	Menu addMenu(Menu menu);
	
}
