package org.cs.rmw.dao.impl;


import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IProductAreaDao;
import org.cs.rmw.model.ProductArea;
import org.cs.util.DateStyle;
import org.cs.util.DateUtil;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductAreaDao extends BaseDao<ProductArea> implements IProductAreaDao {

	@Override
	public List<ProductArea> getProvice() {
		// TODO Auto-generated method stub
		return (List<ProductArea>) this.findByHql("from ProductArea where topId = 0 order by createDate", null);
	}

	@Override
	public List<ProductArea> getCityOrCountry(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", Integer.valueOf(code));
		return (List<ProductArea>) this.findByHql("from ProductArea where topId =:code  order by createDate", params);
		
	}
	
	

	@Override
	public List<ProductArea> getCityOrCountryByName(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		ProductArea area = this.findOneByHql("from ProductArea where areaName =:name", params);
		if(area != null){
			return (List<ProductArea>) this.findByHql("from ProductArea where topId = " + area.getId()+" order by createDate", params);
		}
		
		return null;
	}

	public List<ProductArea> getAllProductArea(){
		return (List<ProductArea>) this.findByHql("from ProductArea where isDelete=false and status=1 order by createDate", null);
	}


	/***********************************************************以下是后台方法*******************************************************************/

	/**
	 * 后台分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryProductAreaPages_admin(Pager page, Map<String, String> map){
		StringBuffer sb = new StringBuffer("  SELECT p.id,p.area_name,p.create_date,p.`status`,p.top_id from rmw_product_area p WHERE p.is_delete=0 AND p.top_id !=0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtil.isNotBlank(map.get("status"))){
			params.put("status",Integer.valueOf(map.get("status")));
			sb.append("  AND p.status= :status");
		}
		if (StringUtil.isNotBlank(map.get("topId"))){
			params.put("topId",Integer.valueOf(map.get("topId")));
			sb.append("  AND p.top_id= :topId");
		}
		sb.append(" order by p.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}




}
