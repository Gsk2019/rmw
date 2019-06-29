package org.cs.mgr.rmw.ctl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cs.core.ctl.BaseCtl;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.ProductArea;
import org.cs.rmw.service.IProductAreaService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: AreaCtl 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月31日 下午5:28:50
 */
@Controller
@RequestMapping("/mgr/productArea")
public class ProductAreaCtl extends BaseCtl {

	Logger log = Logger.getLogger(ProductAreaCtl.class);
	
	@Autowired
	IProductAreaService productAreaService;

	//后台分页获取列表
	@RequestMapping("list")
	public String list_sale(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "mgr/productArea/list";
	}

	//后台分页获取列表
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search){
		Map<String, String> map = getMapFromJson(search);
		Pager pager = this.productAreaService.queryProductAreaPages_admin(new Pager(page, pageSize), map);
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		if(json instanceof JSONObject)
			return JSONObject.toJSONString(json, features);
		return null;
	}


	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		ProductArea productArea = null;
		if(StringUtil.isNotBlank(id)){
			productArea = this.productAreaService.findById(Integer.valueOf(id.trim()));
			mm.put("productArea", productArea);
		}
		return "mgr/productArea/" + action;
	}


	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(ProductArea productArea){
		if(productArea != null ){
			return this.productAreaService.addProductArea_admin(productArea);
		}else{
			return JSONObject.toJSONString(getCode("500","数据异常"));
		}
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(ProductArea productArea){
		try{
			if(productArea != null ){
				return JSONObject.toJSONString(this.productAreaService.updateProductArea_admin(productArea), features);
			}else{
				return JSONObject.toJSONString(getCode("500", "未获取到产品信息"), features);
			}
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍后再试"), features);
		}
	}

	@RequestMapping("del")
	@ResponseBody
	public String del(Integer id){
		try{
			return JSONObject.toJSONString(this.productAreaService.deleteProductArea_admin(id));
		}catch(Exception e){
			e.printStackTrace();
			return JSONObject.toJSONString(getCode("500", "请稍候再试"), features);
		}
	}

	@RequestMapping(value="getProvice", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getProvice(ModelMap mm){
		List<ProductArea> list = this.productAreaService.getProvice();
		return JSONObject.toJSONString(list, features);
	}
	
	@RequestMapping(value="getCityOrCountry", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getCityOrCountry(String param, ModelMap mm){
		List<ProductArea> list = this.productAreaService.getCityOrCountryByName(param);
		return JSONObject.toJSONString(list, features);
	}
	
	@RequestMapping(value="getByParentCode", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getByParentCode(String param, ModelMap mm){
		List<ProductArea> list = this.productAreaService.getCityOrCountry(param);
		return JSONObject.toJSONString(list, features);
	}

	
}
