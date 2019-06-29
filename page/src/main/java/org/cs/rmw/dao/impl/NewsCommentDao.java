package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.INewsCommentDao;
import org.cs.rmw.dao.INewsDao;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: NewsCommentDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Repository
public class NewsCommentDao extends BaseDao<NewsComment> implements INewsCommentDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * rest 接口查新闻列表
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryNewsCommentPager_admin(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(" SELECT c.content,c.id,c.create_date,u.user_name,n.title,c.state FROM rmw_news_comment c LEFT JOIN rmw_user u ON c.user_id=u.id LEFT JOIN rmw_news n ON n.id=c.news_id WHERE c.is_delete=0 ");

		if(StringUtil.isNotBlank(map.get("state"))){
			params.put("state", map.get("state"));
			sb.append(" and c.state=:state");
		}
		sb.append(" order by c.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}




	/*===================================下面是前台的方法==========================================*/

	/**
	 * rest 接口查新闻列表
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getLists(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(" SELECT c.content,c.id,c.create_date,u.user_name FROM rmw_news_comment c LEFT JOIN rmw_user u ON c.user_id=u.id LEFT JOIN rmw_news n ON n.id=c.news_id WHERE c.is_delete=:isdelete and c.state='1'");

		params.put("isdelete", false);
		if(StringUtil.isNotBlank(map.get("newid"))){
			sb.append(" and c.news_id = :newsid");
			params.put("newsid", Integer.valueOf(map.get("newid")));
		}
		sb.append(" order by c.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}
}
