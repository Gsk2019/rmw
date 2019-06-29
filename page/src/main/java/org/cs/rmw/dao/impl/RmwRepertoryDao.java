package org.cs.rmw.dao.impl;

import org.cs.rmw.dao.IRmwRepertoryDao;
import org.cs.rmw.model.RmwRepertory;
import org.springframework.stereotype.Repository;
import org.cs.core.dao.impl.BaseDao;
import org.cs.util.Pager;

/**
 * 
 * @ClassName: RmwRepertory 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-03 22:45:30
 */
@Repository
public class RmwRepertoryDao extends BaseDao<RmwRepertory> implements IRmwRepertoryDao {

	public Pager queryRmwRepertorys(Pager page){
		String hql = "from RmwRepertory where isDelete = false order by sort desc";
		return findPageByHql(hql, page, null);
	}
}
