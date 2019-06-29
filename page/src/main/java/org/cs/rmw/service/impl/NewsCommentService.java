package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.INewsCommentDao;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.service.INewsCommentService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: NewsService
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Service("newsCommentService")
public class NewsCommentService extends BaseService<NewsComment> implements INewsCommentService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private INewsCommentDao newsCommentDao;


	/**
	 * 后台管理新闻列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getNewsCommentPages_admin(Pager page, Map<String, String> map){
		Pager pager = this.newsCommentDao.queryNewsCommentPager_admin(page, map);
		return pager;
	}



	/**
	 * 后台管理删除新闻
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteNewsComment_admin(String id){
		NewsComment newsCommentnews = this.newsCommentDao.findById(Integer.valueOf(id));
		newsCommentnews.setIsDelete(true);
		newsCommentnews = this.newsCommentDao.update(newsCommentnews);
		return getCode("200", "操作成功");
	}

	/**
	 *  后台管理审核
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject checkNewsComment_admin(NewsComment nc) throws Exception {
		NewsComment n = newsCommentDao.findById(nc.getId());
		if(n != null){
			n.setState(nc.getState());
			this.newsCommentDao.update(n);
		}
		return getCode("200", "操作成功");
	}


	/*=================================下面是前台方法=====================================*/



	/**
	 * rest 接口查询新闻列表
	 * @param data
	 * @return
	 */
	@Override
	public JSONObject newsContents( Map<String, String> data){

		String page = data.get("page");
		String pageSize = data.get("pageSize");
//		String type = data.get("state");
		if(StringUtil.isBlank(page)){
			page = "1";
		}
		if(StringUtil.isBlank(pageSize)){
			pageSize = "10";
		}
		JSONObject json = new JSONObject();
		/*if(StringUtil.isBlank(type)){
			return getJson(RetCode.EC_1601, null);
		}*/

		Pager pager = this.newsCommentDao.getLists(new Pager(Integer.valueOf(page),Integer.valueOf(pageSize)), data);
		json.put("pager", pager);

		return getJson(RetCode.SUCCESS, json);
	}

	@Transactional(readOnly = false)
	public  JSONObject addNewsContent(Map<String, String> map ){
		NewsComment newsComment = new NewsComment();
		newsComment.setState("2");
		newsComment.setContent(map.get("content"));
		newsComment.setNewsId(Integer.valueOf(map.get("newid")));
		newsComment.setUserId(Integer.valueOf(map.get("userid")));
		newsComment.setCreateDate(new Date());
		newsComment.setIsDelete(false);
		newsComment.setModifyDate(new Date());
		newsComment = this.newsCommentDao.insert(newsComment);
		return getJson(RetCode.SUCCESS);
	}


}
