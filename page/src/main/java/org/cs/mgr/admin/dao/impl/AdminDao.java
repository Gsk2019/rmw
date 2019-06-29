package org.cs.mgr.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.cs.core.dao.impl.BaseDao;
import org.cs.mgr.admin.dao.IAdminDao;
import org.cs.mgr.admin.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao extends BaseDao<Admin>  implements IAdminDao{

	@Override
	public List<Map<String, Object>> findPermissions(String userCode) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(
				"SELECT tmur.role_code,tmr.name AS role_name, tmm.id AS menu_id, tmm.name AS menu_name, tma.id as user_id,max(tmrm.action_val) as action_val, menu_code, pid, url, weight,icon FROM mgr_user_role tmur left join mgr_admin tma on (tma.code = tmur.user_code)");
		sb.append(" LEFT JOIN  mgr_role tmr ON (tmur.`role_code` = tmr.code) ");
		sb.append(" RIGHT JOIN mgr_role_menu tmrm ON (tmur.`role_code` = tmrm.`role_code`)  ");
		sb.append(" LEFT JOIN mgr_menu tmm ON (tmm.code = tmrm.`menu_code`) ");
		sb.append(" WHERE tmur.user_code = '" + userCode + "'");
		sb.append(" GROUP BY tmm.id ORDER BY tmm.code, weight ");
		return (List<Map<String, Object>>) this.findBySql(sb.toString());
		
	}


	@Override
	public List<Map<String, Object>> findRoleList(int userId) {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer(
				" SELECT tmr.id AS role_id, tmr.code AS role_code, tmr.name AS role_name,  ");
		sb.append(
				" (SELECT IFNULL(count(tmur.id), 0) FROM mgr_user_role tmur LEFT JOIN mgr_admin tma ON tma.code = tmur.user_code WHERE tma.id = " + userId);
		sb.append(" AND tmr.code = tmur.role_code) AS 'set' ");
		sb.append(" FROM mgr_role tmr  ");
		return (List<Map<String, Object>>) this.findBySql(sb.toString());
	}
	
	
	

	

}
