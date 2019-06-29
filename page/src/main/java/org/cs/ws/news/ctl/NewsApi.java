package org.cs.ws.news.ctl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.service.INewsCommentService;
import org.cs.rmw.service.INewsService;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/rest/news")
public class NewsApi extends BaseCtl {

	@Autowired
	private INewsService newsService;
	@Autowired
	private INewsCommentService newsCommentService;


	/**
	 * rest接口查询新闻列表
	 * 参数
	 *    page：分页
	 *    pageSize：条数
	 *    title:标题
	 *
	 */
	@Valid(tk=false)
	@RequestMapping(value="/getNews",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getNews(String data){
        try {
            Map<String, String> map = getMapFromJson(data);
            String page = map.get("page");
            String pageSize = map.get("pageSize");
            Pager pager = this.newsService.getNews(new Pager(Integer.valueOf(page), Integer.valueOf(pageSize)), map);
            return getJson(RetCode.SUCCESS, pager);
		}catch(Exception e) {
			e.printStackTrace();
			return getJson(0, null, "获取新闻列表失败");
		}

	}

	/**
	 * 查询新闻详情
	 * 参数 id：新闻id
	 * 返回值结构
	 *
	 */
	@Valid(tk=false)
	@RequestMapping(value="detail", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject newsDetail(String data, HttpServletRequest request){
		try {
			Map<String, String> map = getMapFromJson(data);
			return this.newsService.queryNewsDetail(map);
		}catch(Exception e) {
			e.printStackTrace();
			return getJson(0, "", "获取详情失败");
		}
	}

	/**
	 * 查询新闻评论列表
	 * 参数 id：新闻id
	 * 返回值结构
	 *
	 */
	@Valid(tk=false)
	@RequestMapping(value="getNewsComments", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getNewsComments(String data, HttpServletRequest request){
		try {
			Map<String, String> map = getMapFromJson(data);
			return this.newsCommentService.newsContents(map);
		}catch(Exception e) {
			e.printStackTrace();
			return getJson(0, "", "获取详情失败");
		}
	}

	/**
	 * 保存新闻评论
	 * 参数 id：新闻id
	 * 返回值结构
	 *
	 */
	@Valid(tk=true)
	@RequestMapping(value="addNewsComments", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addNewsComments(String data, HttpServletRequest request){
		try {
			Map<String, String> map = getMapFromJson(data);
			String userId = getUserId(request);
			map.put("userid", userId);
			return this.newsCommentService.addNewsContent(map);
		}catch(Exception e) {
			e.printStackTrace();
			return getJson(0, "", "获取详情失败");
		}
	}
}
