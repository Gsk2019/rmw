package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.INewsTypeDao;
import org.cs.rmw.model.NewsType;
import org.cs.rmw.service.INewsTypeService;
import org.cs.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: NewsService
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Service("newsTypeService")
public class NewsTypeService extends BaseService<NewsType> implements INewsTypeService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private INewsTypeDao newsTypeDao;

	/**
	 *  后台管理编辑新闻分类
	 * @Title: updateNews
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateNewsType(NewsType newsType){

		NewsType nt = this.newsTypeDao.findById(newsType.getId());
		if(nt != null){
			nt.setName(newsType.getName());
			nt.setSort(newsType.getSort());
			nt.setState(newsType.getState());
			nt.setModifyDate(new Date());
			this.newsTypeDao.update(nt);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台管理新闻列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getNewsTypePages_admin(Pager page, Map<String, String> map){
		Pager pager = this.newsTypeDao.queryNewsTypePager_admin(page, map);
		return pager;
	}

	/**
	 * 获取所有新闻分类
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public List<?> getAllNewsType_admin(){
		List<?> list = this.newsTypeDao.findByHql("from NewsType where isDelete =false and state=1 ", new HashMap<String, Object>());
		return list;
	}

	/**
	 * 后台管理添加新闻分类
	 * @Title: addNews
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addNewsType(NewsType newsType){

		if(newsType != null){
			newsType.setCreateDate(new Date());
			newsType.setIsDelete(false);
			newsType.setModifyDate(new Date());
			newsType.setState("1");
			newsType.setSort(1);
			this.newsTypeDao.insert(newsType);
		}else{
			return "{\"code\":500,\"data\":\"信息不全\"}";
		}
		return "{\"code\":200,\"data\":\"\"}";
	}

	/**
	 * 后台管理删除新闻
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteNewsType_admin(String id){
		NewsType newsType = this.newsTypeDao.findById(Integer.valueOf(id));
		newsType.setIsDelete(true);
		newsType = this.newsTypeDao.update(newsType);
		return getCode("200", "操作成功");
	}


	/*=================================下面是前台方法=====================================*/

	/**
	 * rest 接口查询新闻分类列表
	 * @return
	 */
	@Override
	public Pager getNewsType(Pager pager, Map<String, String> map){

		return this.newsTypeDao.getNewsType(pager, map);
	}

}
