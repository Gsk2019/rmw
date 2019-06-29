package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.INewsDao;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.service.INewsService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
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
@Service("newsService")
public class NewsService extends BaseService<RmwNews> implements INewsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private INewsDao newsDao;



	/**
	 *  自动生成
	 *  后台管理编辑新闻内容
	 * @Title: updateNews
	 * @Description: TODO
	 * @param news						对象
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateNews(RmwNews news){

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", news.getTitle());
		params.put("id", news.getId());
		params.put("type", news.getType());
		List<?> mpold = this.newsDao.findByHql("from RmwNews where title = :title and id != :id and type=:type and isDelete=false", params);
		if(mpold != null && !mpold.isEmpty()){
			return getCode("500", "新闻已存在");
		}
		RmwNews mgm = this.newsDao.findById(news.getId());
		if(mgm != null){
			if(mgm.getLinkType()==2){
				news.setContent(news.getContent().replace(",",""));
				news.setContent(news.getContent().replace("<p>",""));
			}
			mgm.setTitle(news.getTitle());
			mgm.setSummary(news.getSummary());
			mgm.setContent(news.getContent());
			mgm.setMainImage(news.getMainImage());
			mgm.setPublisher(news.getPublisher());
			mgm.setType(news.getType());
			mgm.setSort(news.getSort());
			mgm.setModifyDate(new Date());
			this.newsDao.update(mgm);
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
	public Pager getNewsPages_admin(Pager page, Map<String, String> map){
		Pager pager = this.newsDao.queryNewsPager_admin(page, map);
		return pager;
	}


	/**
	 * 后台管理添加新闻
	 * @Title: addNews
	 * @Description: TODO
	 * @param news
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addNews(RmwNews news){

		if(news.getContent() == null){
			news.setContent("");
		}
		if(news.getLinkType()==2){
			news.setContent(news.getContent().replace(",",""));
		}
		if(news != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("title", news.getTitle());
			params.put("type", news.getType());
			List<?> mpold = this.newsDao.findByHql("from RmwNews where title = :title and type=:type and isDelete=false", params);
			if(mpold != null && !mpold.isEmpty()){
				return "{\"code\":500,\"data\":\"新闻已存在\"}";
			}
			news.setCreateDate(new Date());
			news.setIsDelete(false);
			news.setModifyDate(new Date());
			if(news.getMainImage() == null){
				news.setMainImage("");
			}
			news.setViewCount(0);
			news.setState("1");
			this.newsDao.insert(news);
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
	public JSONObject deleteNews_admin(String id){
		RmwNews news = this.newsDao.findById(Integer.valueOf(id));
		news.setIsDelete(true);
		news = this.newsDao.update(news);
		return getCode("200", "操作成功");
	}


	/*=================================下面是前台方法=====================================*/


	/**
	 * rest接口查询新闻详情
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject queryNewsDetail(Map<String, String> map){
		String id = map.get("id");
		String num = map.get("num");
		String type = map.get("type");

		if (StringUtil.isBlank(id) || StringUtil.isBlank(num) || StringUtil.isBlank(type)){
			return getJson(RetCode.EC_0400);
		}

		Pager prepager = null;
		if((Integer.valueOf(num).intValue() - 1) > 0){
			prepager = this.newsDao.getNews(new Pager(Integer.valueOf(num).intValue() - 1, 1), map);
		}
		Pager nextpager = this.newsDao.getNews(new Pager(Integer.valueOf(num).intValue() + 1,1), map);

		if(StringUtils.isBlank(id.trim())) {
			return getJson(RetCode.EC_0500, "");
		}
//		String type = "";
		RmwNews news = this.newsDao.findById(Integer.valueOf(id));
		if(news == null){
			return getJson(RetCode.EC_0500, "");
		}else{
			Integer cou = news.getViewCount();
			if(cou == null){
				news.setViewCount(1);
			}else{
				news.setViewCount(cou.intValue() + 1);
			}
			news = this.newsDao.update(news);
		}

		JSONObject json = new JSONObject();
		if(prepager != null && prepager.getTotal() > 0 && prepager.getResults().size() > 0){
			json.put("pre", prepager.getResults().get(0));
		}else{
			json.put("pre", null);
		}
		if(nextpager != null && nextpager.getTotal() > 0 && nextpager.getResults().size() > 0){
			json.put("next", nextpager.getResults().get(0));
		}else{
			json.put("next", null);
		}
		json.put("now", news);
		json.put("type", type);
		json.put("num", num);

		return getJson(RetCode.SUCCESS, json);
	}

	/**
	 * rest 接口查询新闻列表
	 * @return
	 */
	@Override
	public Pager getNews(Pager pager, Map<String, String> map){

		return this.newsDao.getNews(pager, map);
	}


	@Transactional(readOnly=false)
    @Override
	public JSONObject updateNewsCount(String newsId, String userId){

		RmwNews news = this.newsDao.findById(newsId);
		if(news == null){
			return getJson(RetCode.EC_0500);
		}else{
			if(news.getViewCount() == null){
				news.setViewCount(0);
			}else{
				news.setViewCount(news.getViewCount() + 1);
			}
			this.newsDao.update(news);
		}
		return getJson(RetCode.SUCCESS);
	}

    /**
     * 获取所有新闻分类
     * @return
     */
    @Transactional(readOnly=false)
    @Override
    public JSONObject getAllNewsType(){

        JSONObject json = new JSONObject();

        List<?> newsTypeList = this.newsDao.getAllNewsType();
        json.put("newsTypeList",newsTypeList);
        return getJson(RetCode.SUCCESS, json);
    }

    /**
     * 获取单个新闻
     * @return
     */
    @Transactional(readOnly=false)
    @Override
    public JSONObject getNewsById(Map<String, String> map){

        JSONObject json = new JSONObject();

        String id = map.get("id");

        if (StringUtil.isBlank(id)){
            return getJson(RetCode.EC_0400);
        }
        RmwNews news = this.newsDao.findById(Integer.valueOf(id));
        if(news == null){
            return getJson(RetCode.EC_0500, "");
        }else{
            Integer cou = news.getViewCount();
            if(cou == null){
                news.setViewCount(1);
            }else{
                news.setViewCount(cou.intValue() + 1);
            }
            news = this.newsDao.update(news);
        }
        json.put("news", news);
        return getJson(RetCode.SUCCESS, json);
    }


}
