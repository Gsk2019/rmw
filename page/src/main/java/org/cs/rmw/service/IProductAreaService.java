package org.cs.rmw.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.model.ProductArea;
import org.cs.util.Pager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IAreaService 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月31日 下午2:58:35
 */
public interface IProductAreaService extends IBaseService<ProductArea> {

	public List<ProductArea> getProvice();
	
	
	public List<ProductArea> getCityOrCountry(String code);
	
	public List<ProductArea> getCityOrCountryByName(String name);


	JSONArray getPCCJson();

	List<ProductArea> getAllProductArea();

	Pager queryProductAreaPages_admin(Pager page, Map<String, String> map);

	String addProductArea_admin(ProductArea productArea);

	/**
	 *  后台管理编辑
	 * @Title: updateMgmProduct
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject updateProductArea_admin(ProductArea productArea);

	JSONObject deleteProductArea_admin(Integer id);
}
