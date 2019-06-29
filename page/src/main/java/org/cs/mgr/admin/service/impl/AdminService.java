package org.cs.mgr.admin.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import jd.util.DateUtil;
import jd.util.PropUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.cs.core.dao.IDicDao;
import org.cs.core.model.Dic;
import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.dao.IAdminDao;
import org.cs.mgr.admin.model.Admin;
import org.cs.mgr.admin.service.IAdminService;
import org.cs.util.Md5;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminService")
public class AdminService extends BaseService<Admin> implements IAdminService {

	@Autowired
	private IAdminDao adminDao;
	
	@Autowired
	private IDicDao dicDao;
	
	@Override
	public Admin getByAccount(String account) {
		// TODO Auto-generated method stub
		List<Admin> list = (List<Admin>) this.findByHql("from Admin where account = '" + account + "'", null);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Transactional(readOnly=false)
	public List<Map<String, Object>> getPermissions(String userCode) {
		return this.adminDao.findPermissions(userCode);
	}

	@Override
	public Pager getList(Pager page) {
		// TODO Auto-generated method stub
		return this.findPageByHql("from Admin where account !='admin' ", page, null);
//		return this.findPageBySql("SELECT a.id, a.`name`,a.account,a.createDate, a.phone, GROUP_CONCAT(r.`name`) roles FROM mgr_admin a, mgr_user_role ro, mgr_role r WHERE a.`code`=ro.user_code AND ro.role_code=r.`code` GROUP BY a.`name` ", page, null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean initPwd(int id) {
		// TODO Auto-generated method stub
		Admin admin = this.findById(id);
		if (admin != null) {
			admin.setPwd(Md5.getMd5(PropUtil.getString("user.initPwd") + admin.getAccount()));
			this.update(admin);
			return true;
		}

		return false;
	}

	public List<Map<String, Object>> getRoleList(int userId) {
		return this.adminDao.findRoleList(userId);
	}
	
	

	@Override
	@Transactional(readOnly=false)
	public Admin addUser(Admin user) {
		// TODO Auto-generated method stub
		Dic dic = this.dicDao.findAndIncrease("admin_code");
		user.setCode(dic.getValue());
		user.setCreateDate(DateUtil.getCurrentDate());
		user.setPwd(Md5.getMd5(user.getPwd().trim() + user.getAccount().trim()));
		return this.adminDao.insert(user);
	}

	@Override
	@Transactional(readOnly=false)
	public Admin updateUser(Admin user) {
		// TODO Auto-generated method stub
		Admin oldUser = this.adminDao.findById(user.getId());
		
		if(oldUser != null){
			try {
				BeanUtils.copyProperties(oldUser, user);
				if (StringUtil.isNotBlank(user.getPwd())){
					oldUser.setPwd(Md5.getMd5(user.getPwd().trim() + user.getAccount().trim()));
				}
				return this.adminDao.update(oldUser);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	
}
