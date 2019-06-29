package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.NewsType;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IMgmProductService
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
public interface INewsTypeService extends IBaseService<NewsType> {


	String addNewsType(NewsType newsType);

	Pager getNewsTypePages_admin(Pager page, Map<String, String> map);

	JSONObject updateNewsType(NewsType newsType);

	JSONObject deleteNewsType_admin(String id);

	Pager getNewsType(Pager pager, Map<String, String> map);

	public List<?> getAllNewsType_admin();

}
