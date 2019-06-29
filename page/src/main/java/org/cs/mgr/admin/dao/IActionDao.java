package org.cs.mgr.admin.dao;

import java.util.List;
import java.util.Map;

import org.cs.core.dao.IBaseDao;
import org.cs.mgr.admin.model.Action;

public interface IActionDao extends IBaseDao<Action>{

	public List<Map<String, Object>> findAllAction(String roleId);
	
}
