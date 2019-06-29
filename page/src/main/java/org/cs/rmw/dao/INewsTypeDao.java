package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.NewsType;
import org.cs.util.Pager;
import java.util.Map;

/**
 * 
 * @ClassName: INewsDao
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
public interface INewsTypeDao extends IBaseDao<NewsType> {

	Pager queryNewsTypePager_admin(Pager page, Map<String, String> map);

//	Pager queryNews(Pager page, Map<String, String> map);

	Pager getNewsType(Pager page, Map<String, String> map);
}
