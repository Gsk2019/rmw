package org.cs.ws.supplyApi.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Info;
import org.cs.rmw.service.IInfoService;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/rest/supply")
public class SupplyApi extends BaseCtl {

	private Logger log = Logger.getLogger(SupplyApi.class);

    @Autowired
	private  IInfoService infoService;


	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getSupply")
	public JSONObject getSupply(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		try {
			Integer page = Integer.valueOf(map.get("page"));
			Integer pageSize = Integer.valueOf(map.get("pageSize"));
			Pager pager = this.infoService.getGoodsSupply(new Pager(page, pageSize), map);

			return getJson(RetCode.SUCCESS, pager);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/getMySupply")
	public JSONObject getMySupply(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		map.put("userId",  request.getAttribute("userId").toString());
		try {
			Integer page = Integer.valueOf(map.get("page"));
			Integer pageSize = Integer.valueOf(map.get("pageSize"));
			Pager pager = this.infoService.getMyInfo(new Pager(page, pageSize), map);

			return getJson(RetCode.SUCCESS, pager);
		} catch (Exception e) {
			log.error("获取失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/addSupply")
	public JSONObject addStocks(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.infoService.addSupply(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/editSupply")
	public JSONObject editSupply(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.infoService.editSupply(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/getInfo")
	public JSONObject getInfo(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		try {
			Info info = this.infoService.findById(Integer.valueOf(map.get("infoid")));
			return getJson(RetCode.SUCCESS, info);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

}
