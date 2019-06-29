package org.cs.ws.trend.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.service.IPriceTrendService;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.cs.ws.supplyApi.ctl.SupplyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rest/trend")
public class PriceTrendApi extends BaseCtl {

	@Autowired
	private IPriceTrendService priceTrendService;

	private Logger log = Logger.getLogger(SupplyApi.class);

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getTrend")
	public JSONObject getTrend(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		try {
			Integer page = Integer.valueOf(map.get("page"));
			Integer pageSize = Integer.valueOf(map.get("pageSize"));
			Pager pager = this.priceTrendService.getPriceTrends(new Pager(page, pageSize), map);

			return getJson(RetCode.SUCCESS, pager);
		} catch (Exception e) {
			log.error("获取趋势失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getBrokenTrend")
	public JSONObject getBrokenTrend(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		if(map == null){
			map = new HashMap<>();
		}
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> bak = this.priceTrendService.getBrokenTrend(map);
			return getJson(RetCode.SUCCESS, bak);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

}
