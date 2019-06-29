package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.PriceTrend;
import org.cs.util.Pager;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

public interface IPriceTrendService extends IBaseService<PriceTrend> {

	Pager getPriceTrendBypages_admin(Pager page, Map<String, String> map);

	/**
	 *  后台管理删除
	 * @Title: updateMgmProduct
	 * @return
	 */
	JSONObject deletePriceTrend_admin(Integer id);

	/**
	 *  后台管理编辑
	 * @Title: updateMgmProduct
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject updatePriceTrend_admin(PriceTrend priceTrend);

	/**
	 * 计算当日一级品类的平均价格
	 * @return
	 */
	@Transactional(readOnly=false)
	public void doPriceTrend_admin();

//	/**
//	 *  后台管理添加
//	 * @Title: updateMgmProduct
//	 * @return
//	 */
//	@Transactional(readOnly=false)
//	public JSONObject addPriceTrend_admin(PriceTrend priceTrend);


	Pager getPriceTrends(Pager page, Map<String, String> map);

	Map<String, Object> getBrokenTrend(Map<String, String> map);
}
