package org.cs.mgr.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.cs.core.dao.impl.BaseDao;
import org.cs.mgr.admin.dao.IActionDao;
import org.cs.mgr.admin.model.Action;
import org.springframework.stereotype.Repository;

@Repository
public class ActionDao extends BaseDao<Action> implements IActionDao{

	@Override
	public List<Map<String, Object>> findAllAction(String roleId){
		StringBuffer sb  = new StringBuffer("SELECT tma.id AS action_id, tma.name AS action_name, tma.menu_code, tma.val AS action_val, tmm.id as menu_id, ");
		sb.append("  IFNULL((SELECT tma.val & tmrm.action_val  FROM mgr_role_menu tmrm ");
		sb.append("  LEFT JOIN  mgr_role tmr ON tmr.code = tmrm.role_code  ");
		sb.append("  WHERE tmr.id ="+ roleId);
		sb.append("  AND tma.menu_code = tmrm.menu_code ");
		sb.append("  ),0  ) AS `set` ");
		sb.append("  FROM mgr_action tma  left join mgr_menu tmm on tmm.code = tma.menu_code");
		return (List<Map<String, Object>>) this.findBySql(sb.toString());
	}
	
	
}
