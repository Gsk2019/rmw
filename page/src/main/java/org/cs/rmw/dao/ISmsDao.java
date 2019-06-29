package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.SMS;

public interface ISmsDao extends IBaseDao<SMS> {
	
	public SMS getSMSByPhone(String phone, String code, int type);
	
}
