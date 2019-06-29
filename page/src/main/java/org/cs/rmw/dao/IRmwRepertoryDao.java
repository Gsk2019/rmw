package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.RmwRepertory;
import org.cs.util.Pager;

public interface IRmwRepertoryDao extends IBaseDao<RmwRepertory> {

	public Pager queryRmwRepertorys(Pager page);
	
	
}
