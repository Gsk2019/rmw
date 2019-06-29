package org.cs.mgr.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.dao.IActionDao;
import org.cs.mgr.admin.model.Action;
import org.cs.mgr.admin.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("actionService")
public class ActionService extends BaseService<Action> implements IActionService {

	@Autowired
	private IActionDao actionDao;
	
	@Override
	public List<Map<String, Object>> getAllAction(String roleId){
//		StringBuffer sb  = new StringBuffer("SELECT tma.id AS action_id, tma.name AS action_name, tma.menu_code, tma.val AS action_val, tmm.id as menu_id, ");
//		sb.append("  IFNULL((SELECT tma.val & tmrm.action_val  FROM mgr_role_menu tmrm ");
//		sb.append("  LEFT JOIN  mgr_role tmr ON tmr.code = tmrm.role_code  ");
//		sb.append("  WHERE tmr.id ="+ roleId);
//		sb.append("  AND tma.menu_code = tmrm.menu_code ");
//		sb.append("  ),0  ) AS `set` ");
//		sb.append("  FROM mgr_action tma  left join mgr_menu tmm on tmm.code = tma.menu_code");
//		return (List<Map<String, Object>>) this.findBySql(sb.toString());
		return this.actionDao.findAllAction(roleId);
	}
	
	@Override
	public List<Action> getAllAction(){
		return (List<Action>) this.findByHql("from Action", null);
	}
	
	@Override
	public List<Map<String, Object>> getAllAction2(){
		StringBuffer sb = new StringBuffer(" select tma.id, tma.name, tmm.id as menu_id, tma.val  ");
		sb.append(" from mgr_action tma left join mgr_menu tmm on  tma.menu_code = tmm.code ");
		return (List<Map<String, Object>>) this.findBySql(sb.toString());
	}
	
	@Override
	@Transactional(readOnly = false)
	public Action addAction(Action ac){
		boolean isSave = false;
		Map<String, Object> mo = new HashMap<String, Object>();
		mo.put("menuCode", ac.getMenuCode());
		List<Action> list = (List<Action>) this.findByHql("from Action where menu_code =:menuCode order by val", mo);
		int i = 0;
		for(; i < list.size(); i++){
			int t = (int) Math.pow(2, i);
			if(list.get(i).getVal() == t){
				continue;
			}
			else{
				ac.setVal(t);
				ac = this.add(ac);
				isSave = true;
				break;
			}
		}
		
		if(!isSave){
			int t = (int) Math.pow(2, i);
			ac.setVal(t);
			ac = this.add(ac);
		}
		
		return ac;
	}
	
	@Override
	public List<Action> getActionByMenuCode(String menuCode) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuCode", menuCode);
		
		return (List<Action>) this.findByHql("from Action where menu_code =:menuCode", params);
	}
	
	

	
	
}
