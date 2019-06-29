package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.util.Pager;

import java.util.Map;

public interface IRmwUserInfoDao extends IBaseDao<RmwUserInfo> {

	public Pager queryRmwUserPages_admin(Pager page, Map<String, String> map);
	
	
}
