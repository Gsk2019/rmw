package org.cs.ws.Category.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.constants.RetCode;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.ProductArea;
import org.cs.rmw.model.RmwRepertory;
import org.cs.rmw.service.ICategoryService;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.IProductAreaService;
import org.cs.rmw.service.IRmwRepertoryService;
import org.cs.util.Pager;
import org.cs.ws.inteceptor.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest/category")
public class CategoryApi extends BaseCtl {

	private Logger log = Logger.getLogger(CategoryApi.class);
	
	@Autowired
	private ICategoryService categoryService;

	/**
	 * 分类
	 * @param data
	 * @return
	 */
	@Valid(tk=false)
	@ResponseBody
	@RequestMapping("/category")
	public JSONObject home(String data, HttpServletRequest request){
		Map<String, String> map = getMapFromJson(data);

		JSONObject json = new JSONObject();
		try {
		    // 品种
			json = categoryService.getCategoryByMap(map);

			return getJson(RetCode.SUCCESS, json);
		} catch (Exception e) {
			log.error("获取验证码失败："+e.getMessage());
			return getJson(RetCode.EC_0500, "");
		}
	}


}
