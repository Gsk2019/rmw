package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.PriceTrend;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: IPriceTrendDao
 * @Description:  
 * @author: gsk
 */
public interface IPriceTrendDao extends IBaseDao<PriceTrend> {

	/**
	 * 分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	Pager queryPriceTrendPages_admin(Pager page, Map<String, String> map);

	Pager getPriceTrends(Pager page, Map<String, String> map);

	List<Map<String, Object>> getBrokenTrend(Map<String, String> map);
}
