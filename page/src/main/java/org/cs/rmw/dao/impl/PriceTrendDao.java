package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IInfoDao;
import org.cs.rmw.dao.IPriceTrendDao;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.PriceTrend;
import org.cs.util.DateStyle;
import org.cs.util.DateUtil;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @ClassName: PriceTrendDao
 * @Description:  
 * @author: sunny_shi
 */
@Repository
public class PriceTrendDao extends BaseDao<PriceTrend> implements IPriceTrendDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 后台分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryPriceTrendPages_admin(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("  from  PriceTrend WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(map.get("categoryId"))){
			params.put("categoryId",Integer.valueOf(map.get("categoryId")));
			sb.append("  AND categoryId=:categoryId ");
		}
		if (StringUtil.isNotBlank(map.get("status"))){
			params.put("status",Integer.valueOf(map.get("status")));
			sb.append("  AND status= :status");
		}
		if (StringUtil.isNotBlank(map.get("startDate"))){
			params.put("startDate",DateUtil.stringToDate(map.get("startDate"), DateStyle.YYYY_MM_DD));
			sb.append("  AND createDate > :startDate");
		}
		if (StringUtil.isNotBlank(map.get("endDate"))){
			params.put("endDate",DateUtil.stringToDate(map.get("endDate"), DateStyle.YYYY_MM_DD));
			sb.append("  AND createDate < :endDate");
		}
		sb.append(" order by createDate desc");
		return this.findPageByHql(sb.toString(), page, params) ;
	}


//	===========================前台===================================
	public Pager getPriceTrends(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("  from  PriceTrend WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(map.get("status"))){
			sb.append("  AND status= 1");
		}
		if (StringUtil.isNotBlank(map.get("categoryId"))){
			sb.append("  AND categoryId= :categoryId");
			params.put("categoryId", Integer.valueOf(map.get("categoryId")));
		}
		sb.append(" order by createDate desc");
		return this.findPageByHql(sb.toString(), page, params) ;
	}

	public List<Map<String, Object>> getBrokenTrend(Map<String, String> map){
		Date date = new Date();

		Date startdata = DateUtil.addDay(date, -7);

		if(StringUtil.isNotBlank(map.get("mt"))){
			if("1".equals(map.get("mt"))){
				startdata = DateUtil.addDay(date, -30);
			}else if("2".equals(map.get("mt"))){
				startdata = DateUtil.addDay(date, -90);
			}else if("3".equals(map.get("mt"))){
				startdata = DateUtil.addDay(date, -180);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String startdate = sdf.format(startdata);
		StringBuffer sb = new StringBuffer("select ave_price, min_price, DATE_FORMAT(price_date,'%m/%d') as price_data, category_name, category_id from rmw_price_trend WHERE status=1 and create_date >= ? and create_date <= now()");
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("startdate", false);*/
		List<Object> olist = new ArrayList<>();
		olist.add(startdate);
		if(StringUtil.isNotBlank(map.get("categoryTopId"))){
			sb.append(" and category_id =?");
			olist.add(Integer.valueOf(map.get("categoryTopId")));
		}
		sb.append(" order by create_date asc");
		return (List<Map<String, Object>>)this.findBySql(sb.toString(), olist.toArray()) ;
	}
}
