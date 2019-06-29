package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IInfoDao;
import org.cs.rmw.dao.ILogisticsDao;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.Logistics;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: InfoDao
 * @Description:  
 * @author: sunny_shi
 */
@Repository
public class InfoDao extends BaseDao<Info> implements IInfoDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 后台分页查询发布/求购的信息
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryInfoPages_admin(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("SELECT i.id,i.brand,i.category_id,i.category_name,i.category_top_id,i.category_top_name,i.contact_name,i.contact_tel,i.count,i.create_date,i.expiry_date,i.price,i.end_price endPrice,i.product_area,i.product_date,i.reason,i.note,i.repertory,i.sort,i.`status`,i.trader_id,i.trader_name,i.trader_phone,i.type,i.unit,i.is_sys_trade isSysTrade ,i.user_id,u.user_name,u.phone FROM rmw_info i LEFT JOIN rmw_user u ON u.id=i.user_id ");
		sb.append(" WHERE i.is_delete=0 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type",map.get("type"));
		sb.append("  AND i.type=:type ");
		if (StringUtil.isNotBlank(map.get("publisher"))){
			params.put("publisher","%"+map.get("publisher")+"%");
			sb.append("  AND (u.user_name like :publisher OR u.phone like :publisher )");
		}
		if (StringUtil.isNotBlank(map.get("categoryTopSelect"))){
			params.put("categoryTopSelect",map.get("categoryTopSelect"));
			sb.append("  AND i.category_top_name= :categoryTopSelect");
		}
		if (StringUtil.isNotBlank(map.get("categorySelect"))){
			params.put("categorySelect",map.get("categorySelect"));
			sb.append("  AND i.category_name= :categorySelect");
		}
		if (StringUtil.isNotBlank(map.get("status"))){
			params.put("status",map.get("status"));
			sb.append("  AND i.status= :status");
		}
		if (StringUtil.isNotBlank(map.get("startDate"))){
			params.put("startDate",map.get("startDate"));
			sb.append("  AND i.create_date > :startDate");
		}
		if (StringUtil.isNotBlank(map.get("endDate"))){
			params.put("endDate",map.get("endDate"));
			sb.append("  AND i.create_date < :endDate");
		}
		sb.append(" order by i.sort desc,i.create_date desc");

		return this.findPageBySql(sb.toString(), page, params) ;
	}
	/**
	 * 后台分页查询供货信息
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryGhInfoPages_admin(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer(" SELECT i.id, i.category_name, i.category_top_name,i.is_sys_trade isSysTrade, i.contact_name, i.contact_tel, i.count, i.create_date, i.price, i.product_area, i.product_date, i.`status`, i.unit, ");
		sb.append(" ii.category_name categoryName, ii.category_top_name categoryTopName, ii.contact_name contactName, ii.contact_tel contactTel,ii.count count2, ii.price price2, ii.trader_name, ii.trader_phone ,ii.create_date createDate ");
		sb.append(" FROM rmw_info i LEFT JOIN rmw_info ii ON i.info_id=ii.id WHERE i.type=3 and i.is_delete=0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtil.isNotBlank(map.get("status"))){
			params.put("status",map.get("status"));
			sb.append("  AND i.status= :status");
		}
		if (StringUtil.isNotBlank(map.get("startDate"))){
			params.put("startDate",map.get("startDate"));
			sb.append("  AND i.create_date > :startDate");
		}
		if (StringUtil.isNotBlank(map.get("endDate"))){
			params.put("endDate",map.get("endDate"));
			sb.append("  AND i.create_date < :endDate");
		}
		sb.append(" order by i.create_date desc");

		return this.findPageBySql(sb.toString(), page, params) ;
	}

	/**
	 * 后台分页查询发布的信息
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getGoods(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("SELECT ma.phone as t_phone, ma.name as t_name, i.*, u.user_name, u.company FROM rmw_info i LEFT JOIN rmw_user u ON u.id=i.user_id left join mgr_admin ma on ma.id= i.trader_id");
		sb.append(" WHERE 1=1 and i.is_delete=:isdelete and i.status = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isdelete", false);
		params.put("type",map.get("type"));
		sb.append("  AND i.type=:type ");
		if("2".equals(map.get("type"))){
			sb.append(" and i.expiry_date > now()");
		}

		if(StringUtil.isNotBlank(map.get("name"))){
			sb.append(" and (i.brand like :name or i.repertory like :name or i.ptop_area like :name or i.product_area like :name or i.category_top_name like :name or i.category_name like :name)");
			params.put("name", "%" + map.get("name")+"%");
		}

		if(StringUtil.isNotBlank(map.get("brand"))){
			sb.append(" and i.brand like :brand ");
			params.put("brand", "%" + map.get("brand")+"%");
		}

		if(StringUtil.isNotBlank(map.get("category"))){
			sb.append(" and (i.category_top_name like :category or i.category_name like :category)");
			params.put("category", "%" + map.get("category")+"%");
		}

		if(StringUtil.isNotBlank(map.get("category1"))){
			sb.append(" and i.category_top_id=:category1");
			params.put("category1", Integer.valueOf(map.get("category1")));
		}
		if(StringUtil.isNotBlank(map.get("category2"))){
			sb.append(" and i.category_id=:category2");
			params.put("category2", Integer.valueOf(map.get("category2")));
		}
		if(StringUtil.isNotBlank(map.get("area1"))){
			sb.append(" and i.ptop_area_id=:area1");
			params.put("area1", Integer.valueOf(map.get("area1")));
		}
		if(StringUtil.isNotBlank(map.get("area2"))){
			sb.append(" and i.p_area_id=:area2");
			params.put("area2", Integer.valueOf(map.get("area2")));
		}
		if(StringUtil.isNotBlank(map.get("rep")) && !"0".equals(map.get("rep"))){
			sb.append(" and i.repertory_id=:rep");
			params.put("rep", Integer.valueOf(map.get("rep")));
		}
		if(StringUtil.isNotBlank(map.get("ord")) ){

			if("1".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.create_date asc");
			}else if("2".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.create_date desc");
			}else if("3".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.count asc");
			}else if("4".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.count desc");
			}else if("5".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.price asc");
			}else if("6".equals(map.get("ord"))){
				sb.append(" order by i.sort asc, i.price desc");
			}else{
				sb.append(" order by i.sort asc, i.create_date desc");
			}
		}else{
			sb.append(" order by i.sort asc, i.create_date desc");
		}

		return this.findPageBySql(sb.toString(), page, params) ;
	}

	public  Pager getMyInfo(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("SELECT i.*, u.user_name FROM rmw_info i LEFT JOIN rmw_user u ON u.id=i.user_id ");
		sb.append(" WHERE 1=1 and i.is_delete=:isdelete");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isdelete", false);
		params.put("type",map.get("type"));
		sb.append("  AND i.type=:type ");

		if(StringUtil.isNotBlank(map.get("userId")) ){
			sb.append(" and i.user_id=:userid");
			params.put("userid", Integer.valueOf(map.get("userId")));
		}
		sb.append(" order by i.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}

	public List<Map<String, Object>> getTjData(int tip,String type){
		StringBuffer sql = new StringBuffer();
		if (tip==1){
			sql.append(" SELECT c.category_name cname,(SELECT COUNT(1) FROM rmw_info i WHERE i.is_delete=0 AND i.type="+type+") sum,(SELECT SUM(i.count) FROM rmw_info i WHERE i.is_delete=0 AND i.type="+type+") sumDun,(SELECT COUNT(1) FROM rmw_info i WHERE i.is_delete=0 AND i.category_top_id= c.id AND i.type= "+type+") count,( SELECT SUM(i.count) FROM rmw_info i WHERE i.is_delete = 0 AND i.category_top_id = c.id AND i.type ="+type+" ) dun FROM rmw_category c  WHERE c.top_id=0 AND c.is_delete=0 ");
		}else{
			sql.append(" SELECT c.category_name cname,(SELECT COUNT(1) FROM rmw_info i WHERE to_days(i.create_date) = to_days(now()) and i.is_delete=0 AND i.type="+type+") sum,(SELECT SUM(i.count) FROM rmw_info i WHERE to_days(i.create_date) = to_days(now()) and i.is_delete=0 AND i.type="+type+") sumDun,(SELECT COUNT(1) FROM rmw_info i WHERE to_days(i.create_date) = to_days(now()) and i.is_delete=0 AND i.category_top_id= c.id AND i.type="+type+") count,(SELECT SUM(i.count) FROM rmw_info i WHERE to_days(i.create_date) = to_days(now()) and i.is_delete=0 AND i.category_top_id= c.id AND i.type="+type+") dun FROM rmw_category c  WHERE c.top_id=0 AND c.is_delete=0  ");
		}
		List<Map<String, Object>> listMap=(List<Map<String, Object>>)this.findBySql(sql.toString()) ;
		return listMap;
	}


}
