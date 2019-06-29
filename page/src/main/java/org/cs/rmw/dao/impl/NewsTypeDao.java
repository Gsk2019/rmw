package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.INewsTypeDao;
import org.cs.rmw.model.NewsType;
import org.cs.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: NewsDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Repository
public class NewsTypeDao extends BaseDao<NewsType> implements INewsTypeDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * rest 接口查新闻列表
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryNewsTypePager_admin(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("SELECT t.id,t.name,t.sort,t.create_date,t.state,t.modify_date FROM rmw_news_type t WHERE t.is_delete = 0 ");

		sb.append(" order by t.sort desc,t.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}


	/*===================================下面是前台的方法==========================================*/

	/**
	 * 前台管理新闻列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getNewsType(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from NewsType where  isDelete = false and state='1'");

		sb.append(" order by sort desc,");
		return this.findPageByHql(sb.toString(), page, params) ;
	}
}
