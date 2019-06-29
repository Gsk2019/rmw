package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.NewsType;
import org.cs.rmw.model.RmwNews;
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
public interface INewsService extends IBaseService<RmwNews> {


	String addNews(RmwNews news);

	Pager getNewsPages_admin(Pager page, Map<String, String> map);

	JSONObject updateNews(RmwNews news);

	JSONObject deleteNews_admin(String ppid);

	JSONObject updateNewsCount(String productId, String userId);

//	JSONObject categoryNews(Map<String, String> data1);

	Pager getNews(Pager pager, Map<String, String> map);

	JSONObject queryNewsDetail(Map<String, String> map);

	JSONObject  getAllNewsType();

	JSONObject getNewsById(Map<String, String> map);



}
