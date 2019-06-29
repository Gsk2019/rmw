package org.cs.ws.StockApi.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.ProductArea;
import org.cs.rmw.model.RmwRepertory;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.*;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest/stock")
public class StockApi extends BaseCtl {

	private Logger log = Logger.getLogger(StockApi.class);
	
	@Autowired
	private ICategoryService categoryService;
    @Autowired
    private IProductAreaService productAreaService;
    @Autowired
    private IRmwRepertoryService rmwRepertoryService;
    @Autowired
	private  IInfoService infoService;
	@Autowired
	private IRmwUserService rmwUserService;
	@Autowired
	private IOtherService otherService;


	/**
	 * 登录
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/home")
	public JSONObject home(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
		    // 品种
			json = categoryService.getCategory();
			//产地
            List<ProductArea> palist = this.productAreaService.getAllProductArea();
            json.put("productAreas", palist);
            //交割仓库地址
			List<RmwRepertory> rrlist = new ArrayList<>();
//			RmwRepertory rr = new RmwRepertory();
//			rr.setName("不限");
//			rrlist.add(rr);
            rrlist.addAll(this.rmwRepertoryService.getAllRmwRepertory());
            json.put("rmwRepertorys", rrlist);

			return getJson(RetCode.SUCCESS, json);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/home2")
	public JSONObject home2(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
			// 品种
			json = categoryService.getCategory();
			//产地
			List<ProductArea> palist = this.productAreaService.getAllProductArea();
			json.put("productAreas", palist);
			//交割仓库地址
			List<RmwRepertory> rrlist = this.rmwRepertoryService.getAllRmwRepertory();
			json.put("rmwRepertorys", rrlist);

			String userId=request.getAttribute("userId").toString();
			RmwUser rmwUser=rmwUserService.findById(Integer.valueOf(userId));
			json.put("rmwUser", rmwUser);

			return getJson(RetCode.SUCCESS, json);
		} catch (Exception e) {
			log.error("获取失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getStocks")
	public JSONObject getStocks(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		try {
			Integer page = Integer.valueOf(map.get("page"));
			Integer pageSize = Integer.valueOf(map.get("pageSize"));
			Pager pager = this.infoService.getGoodsStocks(new Pager(page, pageSize), map);

			return getJson(RetCode.SUCCESS, pager);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/getLunbotu")
	public JSONObject getLunbotu(String data, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Map<String, String> map=new HashMap<String, String>();
			map.put("type","3");
			List<?> list = this.otherService.queryOtherTj_admin(new Pager(1, 10), map).getResults();
			return getJson(RetCode.SUCCESS, list);
		} catch (Exception e) {
			log.error("获取轮播图失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/addStocks")
	public JSONObject addStocks(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.infoService.addStocks(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/editStocks")
	public JSONObject editStocks(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.infoService.editStocks(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}


	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/addStocks3")
	public JSONObject addStocks3(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		map.put("userId", request.getAttribute("userId").toString());
		JSONObject json = new JSONObject();
		try {
			return this.infoService.addStocks(map);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/getMyStock")
	public JSONObject getMyStock(String data, HttpServletRequest request){
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

	//批量重新发布
	@Valid(tk=true)
	@ResponseBody
	@RequestMapping("/gen")
	public JSONObject gen(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);
		JSONObject json = new JSONObject();
		map.put("userId",  request.getAttribute("userId").toString());
		try {
			return this.infoService.gen(map);
		} catch (Exception e) {
			log.error("获取失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}

	//批量删除
    @Valid(tk=true)
    @ResponseBody
    @RequestMapping("/dels")
    public JSONObject dels(String data, HttpServletRequest request){
        Map<String, String> map = getMapFromJson(data);
        JSONObject json = new JSONObject();
        map.put("userId",  request.getAttribute("userId").toString());
        try {
            return this.infoService.dels(map);
        } catch (Exception e) {
            log.error("获取失败："+e.getMessage());
            return getJson(RetCode.EC_0500, "");
        }
    }

    //批量删除
    @Valid(tk=true)
    @ResponseBody
    @RequestMapping("/info")
    public JSONObject info(String data, HttpServletRequest request){
        Map<String, String> map = getMapFromJson(data);
        JSONObject json = new JSONObject();
        map.put("userId",  request.getAttribute("userId").toString());
        try {
            Info currinfo = this.infoService.findById(Integer.valueOf(map.get("id")));
            return getJson(RetCode.SUCCESS, currinfo);
        } catch (Exception e) {
            log.error("获取失败："+e.getMessage());
            return getJson(RetCode.EC_0500, "");
        }
    }


}
