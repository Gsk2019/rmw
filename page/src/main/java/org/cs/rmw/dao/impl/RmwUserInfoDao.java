package org.cs.rmw.dao.impl;

import org.cs.rmw.dao.IRmwUserInfoDao;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.util.StringUtil;
import org.springframework.stereotype.Repository;
import org.cs.core.dao.impl.BaseDao;
import org.cs.util.Pager;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: RmwUserInfo 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-08 21:32:01
 */
@Repository
public class RmwUserInfoDao extends BaseDao<RmwUserInfo> implements IRmwUserInfoDao {

	public Pager queryRmwUserPages_admin(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("SELECT ui.id, ui.`status`, ui.create_date, ui.count as uicount, u.phone, u.user_name, ");
		sb.append("i.brand, i.category_name, i.category_top_name, i.contact_name, i.contact_tel, i.count, i.expiry_date, i.price, i.product_area, i.product_date, i.note, i.repertory, i.trader_id, i.trader_name, i.trader_phone, i.unit");
		sb.append(" FROM rmw_user_info ui LEFT JOIN rmw_user u ON ui.user_id=u.id LEFT JOIN rmw_info i ON ui.info_id =i.id ");
		sb.append(" WHERE ui.is_delete=0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtil.isNotBlank(map.get("status"))){
			params.put("status",map.get("status"));
			sb.append("  AND ui.status= :status");
		}
        if (StringUtil.isNotBlank(map.get("startDate"))){
            params.put("startDate",map.get("startDate"));
            sb.append("  AND ui.create_date > :startDate");
        }
        if (StringUtil.isNotBlank(map.get("endDate"))){
            params.put("endDate",map.get("endDate"));
            sb.append("  AND ui.create_date < :endDate");
        }
		sb.append(" order by ui.create_date desc");

		return this.findPageBySql(sb.toString(), page, params) ;
	}
}
