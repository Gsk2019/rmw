package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.util.Pager;

import java.util.Map;

/**
 * 
 * @ClassName: INewsDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
public interface INewsCommentDao extends IBaseDao<NewsComment> {

//	Map<String, Object> queryNewsDetail(Map<String, String> map);

	Pager queryNewsCommentPager_admin(Pager page, Map<String, String> map);

//	int countAllNews();

//	Pager queryNews(Pager page, Map<String, String> map);

	Pager getLists(Pager page, Map<String, String> map);
}
