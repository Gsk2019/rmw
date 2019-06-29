package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.Logistics;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ILogisticsDao
 * @Description:  
 * @author: gsk
 */
public interface ILogisticsDao extends IBaseDao<Logistics> {

	/**
	 * 分页查询物流信息
	 * @param page
	 * @param map
	 * @return
	 */
	Pager queryLogisticsPager_admin(Pager page, Map<String, String> map);

	Pager getLogistics(Pager pager, Map<String, String> map);
}
