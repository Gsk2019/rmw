package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.INewsDao;
import org.cs.rmw.model.NewsType;
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
 * @ClassName: NewsDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Repository
public class NewsDao extends BaseDao<RmwNews> implements INewsDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * rest 接口查新闻列表
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryNewsPager_admin(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("SELECT n.id,n.title,n.summary,n.content,n.main_image,n.publisher,n.link_type,n.remark,n.sort,n.type,t.name typeName,n.view_count,n.create_date,n.state,n.keywords FROM rmw_news n left join rmw_news_type t ON t.id=n.type WHERE n.is_delete = 0 ");

		if(StringUtil.isNotBlank(map.get("state"))){
			params.put("state", map.get("state"));
			sb.append(" and state=:state");
		}
		if(StringUtil.isNotBlank(map.get("title"))){
			params.put("title", "%" + map.get("title") + "%");
			sb.append(" and title like :title");
		}
		if(StringUtil.isNotBlank(map.get("type"))){
			params.put("type", map.get("type"));
			sb.append(" and type =:type");
		}

		sb.append(" order by n.sort desc,n.create_date desc");
		return this.findPageBySql(sb.toString(), page, params) ;
	}

//	/**
//	 * rest接口查询新闻详情
//	 * @param map
//	 * @return
//	 */
//	@Override
//	 public Map<String, Object> queryNewsDetail(Map<String, String> map){
//		StringBuffer sb = new StringBuffer("select mp.view_total as viewTotal, mp.formater, mp.d_size as dSize, mp.gc, mp.id as id, mp.main_image as mainImage, mp.url as url, mp.title as title, mp.content as content, mp.create_date as createDate from rmw_news mp ");
//		List<Map<String, Object>> a = new ArrayList<>();
//		List<Object> olist = new ArrayList<>();
//
//		if(StringUtil.isNotBlank(map.get("id"))){
//			sb.append(" where mp.id=? ");
//			olist.add(map.get("id"));
//		}
//		a = this.findBySql(sb.toString(), olist.toArray());
//		if(a != null && !a.isEmpty()){
//			return a.get(0);
//		}
//		 return new HashMap<String, Object>();
//	 }

//	 @Override
//	 public int countAllNews(){
//		return this.countBySql("select count(*) from rmw_news where type='1' and is_delete=false and state='1'", null).intValue();
//	 }


	/*===================================下面是前台的方法==========================================*/

	/**
	 * 前台管理新闻列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getNews(Pager page, Map<String, String> map){
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("state", "1");
		StringBuffer sb = new StringBuffer("from RmwNews where  isDelete = false and state='1'");//product_state=:state and

		/*//其他条件
		if(StringUtil.isNotBlank(map.get("type"))){
			params.put("type", map.get("type"));
			sb.append(" and type=:type");
		}*/
		if(StringUtil.isNotBlank(map.get("type"))){
			params.put("type", map.get("type"));
			sb.append(" and type = :type");
		}

		sb.append(" order by sort desc,");
		sb.append(" createDate desc");
		return this.findPageByHql(sb.toString(), page, params) ;
	}

	/**
	 * 获取所有新闻分类
	 * @return
	 */
	@Override
	public List<?> getAllNewsType(){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from NewsType where  isDelete = false and state='1'");//product_state=:state and
		sb.append(" order by sort desc ");
		return this.findByHql(sb.toString(), params) ;
	}

}
