package org.cs.ws.user.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.service.IFeedBackService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/rest/feedback")
public class FeedBackApi extends BaseCtl {

	private Logger log = Logger.getLogger(FeedBackApi.class);

	@Autowired
	private IRmwUserService rmwUserService;
	@Autowired
	private IFeedBackService feedBackService;
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/feedback")
	public JSONObject feedback(String data, HttpServletRequest request){

		JSONObject json = new JSONObject();
		try {
			String userId = getUserId(request);
			Map<String, String> map = getMapFromJson(data);
			map.put("userId", userId);
			return feedBackService.addFeedBack(map);
		} catch (Exception e) {
			log.error("意见反馈失败："+e.getMessage());
			return getJson(RetCode.EC_0500);
		}
	}

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/feedbacklist")
	public JSONObject feedbacklist(String data){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		try {
			Pager pager = new Pager();
			if(StringUtil.isNotBlank(map.get("page"))){
				if (StringUtil.isNotBlank(map.get("pageSize"))){
					pager = new Pager(Integer.valueOf(map.get("page").trim()), Integer.valueOf(map.get("pageSize").trim()));
				}
				else
				{
					pager = new Pager(Integer.valueOf(map.get("page").trim()));
				}
			}
			Pager page = feedBackService.queryFeedBackPager(pager, map);
			json = getJson(RetCode.SUCCESS, page);
		} catch (Exception e) {
			log.error("意见反馈失败："+e.getMessage());
			json = getJson(RetCode.EC_0500, "");
		}
		return json;
	}
	

}
