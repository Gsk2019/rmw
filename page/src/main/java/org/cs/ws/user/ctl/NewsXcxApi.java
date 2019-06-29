package org.cs.ws.user.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.service.INewsService;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/rest/news/xcx")
public class NewsXcxApi extends BaseCtl {
	private Logger log = Logger.getLogger(NewsXcxApi.class);

	@Autowired
	private INewsService newsService;


	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getAllNewsType")
	public JSONObject getAllNewsType(String data, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			json=newsService.getAllNewsType();
			return json;
		} catch (Exception e) {
			log.error(""+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getNewsByType")
	public JSONObject getNewsByType(String data) {
		try {
			Map<String, String> map = getMapFromJson(data.replace("'","\""));

			String page = map.get("page");
			String pageSize = map.get("pageSize");
			Pager pager = this.newsService.getNews(new Pager(Integer.valueOf(page), Integer.valueOf(pageSize)), map);
			return getJson(RetCode.SUCCESS, pager);
		} catch (Exception e) {
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
	@ResponseBody
	@RequestMapping("/getNewsById")
	public JSONObject newsDetail(String data, HttpServletRequest request){
		try {
			Map<String, String> map = getMapFromJson(data.replace("'","\""));
			return this.newsService.getNewsById(map);
		}catch(Exception e) {
			e.printStackTrace();
			return getJson(0, "", "获取详情失败");
		}
	}

}
