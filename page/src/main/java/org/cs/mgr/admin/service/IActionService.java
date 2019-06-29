package org.cs.mgr.admin.service;

import java.util.List;
import java.util.Map;

import org.cs.core.service.IBaseService;
import org.cs.mgr.admin.model.Action;

public interface IActionService  extends IBaseService<Action>{

	public List<Action> getActionByMenuCode(String menuCode);
	
	/**
	 * 获取所有action
	 * @Title: getAllAction 
	 * @Description: TODO
	 * @return
	 * @return: List<Action>
	 */
	public List<Action> getAllAction();
	
	public Action addAction(Action ac);
	
	public List<Map<String, Object>> getAllAction2();
	
	List<Map<String, Object>> getAllAction(String roleId);
	
}
