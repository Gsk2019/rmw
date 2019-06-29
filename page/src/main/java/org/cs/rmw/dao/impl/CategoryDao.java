package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.ICategoryDao;
import org.cs.rmw.model.Category;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	public List<Category> findCategoryByPid(Integer pid){
		StringBuffer stringBuffer = new StringBuffer("from Category where topId=:pid and isDelete =:isdelete");
		Map<String, Object> params = new HashMap<>();
		 params.put("pid", pid);
		 params.put("isdelete", false);
		return (List<Category>)this.findByHql(stringBuffer.toString(), params);

	}

	public List<Category> findCategoryByMap(Map<String, String> map){
		StringBuffer stringBuffer = new StringBuffer("from Category where isDelete =:isdelete");
		Map<String, Object> params = new HashMap<>();
		params.put("isdelete", false);
		if(StringUtil.isNotBlank(map.get("topid"))){
			params.put("topid", Integer.valueOf(map.get("topid")));
			stringBuffer.append(" and topId=:topid");
		}
		return (List<Category>)this.findByHql(stringBuffer.toString(), params);

	}
 }
