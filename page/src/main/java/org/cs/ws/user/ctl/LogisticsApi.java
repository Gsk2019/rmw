package org.cs.ws.user.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.service.ILogisticsService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.cs.ws.inteceptor.Valid;
import org.cs.ws.supplyApi.ctl.SupplyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest/logistics")
public class LogisticsApi extends BaseCtl {
	private Logger log = Logger.getLogger(LogisticsApi.class);

	@Autowired
	private ILogisticsService logisticsService;


	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getLogistics")
	public JSONObject getSupply(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data.replace("'","\""));
		JSONObject json = new JSONObject();
		try {
			Integer page = Integer.valueOf(map.get("page"));
			Integer pageSize = Integer.valueOf(map.get("pageSize"));
			Pager logisticsList = this.logisticsService.getLogistics(new Pager(page, pageSize), map);

			return getJson(RetCode.SUCCESS , logisticsList);
		} catch (Exception e) {
			log.error(""+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/addLogistics")
	public JSONObject addLogistics(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data.replace("'","\""));
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.logisticsService.addLogistics(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

}
