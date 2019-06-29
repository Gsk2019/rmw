package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.util.Pager;

import java.util.Map;

/**
 * 
 * @ClassName: IMgmProductService
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
public interface INewsCommentService extends IBaseService<NewsComment> {


	Pager getNewsCommentPages_admin(Pager page, Map<String, String> map);

	JSONObject deleteNewsComment_admin(String ppid);

	public JSONObject checkNewsComment_admin(NewsComment nc) throws Exception;

	JSONObject newsContents( Map<String, String> data);

	JSONObject addNewsContent(Map<String, String> map );
}
