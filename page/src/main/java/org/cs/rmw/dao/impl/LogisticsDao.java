package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.ILogisticsDao;
import org.cs.rmw.dao.INewsDao;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.RmwNews;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: MgmProductDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Repository
public class LogisticsDao extends BaseDao<Logistics> implements ILogisticsDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 后台分页查询物流信息
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryLogisticsPager_admin(Pager page, Map<String, String> map){

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("type", map.get("type"));
		StringBuffer sb = new StringBuffer("SELECT l.id,l.link_man linkMan,l.phone,l.start_point startPoint,l.end_point endPoint,l.price,l.create_date createDate,l.tonnage,l.consignment,l.expiry_date,l.status  FROM rmw_logistics l  WHERE l.type=:type and l.is_delete=0  ");
		if (StringUtil.isNotBlank(map.get("status"))){
			sb.append(" and l.`status`= :status ");
			params.put("status", map.get("status"));
		}
		sb.append(" order by l.create_date desc ");
		return this.findPageBySql(sb.toString(), page, params) ;
	}

//==============================================前端==========================================================================

	/**
	 * 前端查询
	 * @param map
	 * @return
	 */
	@Override
	public Pager getLogistics(Pager pager, Map<String, String> map){

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 2);
		params.put("type", Integer.valueOf(map.get("type")));
		StringBuffer sb = new StringBuffer("FROM Logistics l  WHERE l.type=:type and l.status = :status and isDelete=false");
		if(StringUtil.isNotBlank(map.get("startPoint"))){
			sb.append(" and startPoint like :startPoint");
			params.put("startPoint", "%" + map.get("startPoint") + "%");
		}
		if(StringUtil.isNotBlank(map.get("endPoint"))){
			sb.append(" and endPoint like :endPoint");
			params.put("endPoint", "%" + map.get("endPoint") + "%");
		}
		sb.append(" order by l.createDate desc ");
		return this.findPageByHql(sb.toString(), pager, params) ;
	}
}
