package org.cs.mgr.admin.service.impl;

import jd.util.DateUtil;
import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.dao.IAdminDao;
import org.cs.mgr.admin.dao.IUserRoleDao;
import org.cs.mgr.admin.model.Admin;
import org.cs.mgr.admin.model.UserRole;
import org.cs.mgr.admin.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userRoleService")
public class UserRoleService extends BaseService<UserRole> implements IUserRoleService {
	
	@Autowired
	public IAdminDao adminDao;
	
	
	@Autowired
	public IUserRoleDao userRoleDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean setUserRole(String roleCodes, int userId){
		Admin user = this.adminDao.findById(userId);
		this.userRoleDao.deleteByWhere(" and user_code='" + user.getCode() + "'");  // 清除该角色权限信息
		String[] codes = roleCodes.split(",");
		
		if(user != null){
			for(String code : codes ){
				UserRole ur = new UserRole();
				ur.setRoleCode(code);
				ur.setUserCode(user.getCode());
				ur.setCreateDate(DateUtil.getCurrentDate());
				this.userRoleDao.insert(ur);
			}
			return true;
		}
		return false;
		
	}
	
}
