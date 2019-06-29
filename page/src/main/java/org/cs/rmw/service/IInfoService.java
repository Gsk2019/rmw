package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.RmwNews;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IInfoService extends IBaseService<Info> {

	Pager getInfoBypages_admin(Pager page, Map<String, String> map);

	Pager queryGhInfoPages_admin(Pager page, Map<String, String> map);

	JSONObject deleteInfos_admin(Integer id);

	/**
	 * 后台管理获取交易员
	 * @return
	 */
	@Transactional(readOnly=false)
	public List<Map<String,Object>> getTraders_admin();

	public String addInfo(Info info) throws Exception;

	public String addBuyInfo(Info info) throws Exception;

	public String isExitUser(String phone);

	/**
	 *  后台管理编辑信息内容
	 * @Title: updateMgmProduct
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject updateBuyInfo_admin(Info info);

	/**
	 *  后台管理编辑信息内容
	 * @Title: updateMgmProduct
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject updateSaleInfo_admin(Info info);

	/**
	 *  后台管理审核信息内容
	 * @Title: updateMgmProduct
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject checkInfo_admin(Info info) throws Exception;

	/**
	 * 获取当天的平均价格
	 * @return
	 */
	@Transactional(readOnly=false)
	public String getAveragePrice_admin(Integer categoryId);

	/**
	 * 获取当天的最低价格
	 * @return
	 */
	@Transactional(readOnly=false)
	public String getMinPrice_admin(Integer categoryId);

	Pager getGoodsStocks(Pager page, Map<String, String> map);

	/**
	 * 添加现货
	 * @param map
	 * @return
	 */
	JSONObject addStocks(Map<String, String> map);

	Pager getGoodsSupply(Pager page, Map<String, String> map);

	JSONObject addSupply(Map<String, String> map);

	Pager getMyInfo(Pager page, Map<String, String> map);

	JSONObject addStocks3(Map<String, String> map);

	JSONObject gen(Map<String, String> map);

	JSONObject dels(Map<String, String> map);

	JSONObject editStocks(Map<String, String> map);

    JSONObject editSupply(Map<String, String> map);

    //后台获取统计内容
	List<Map<String, Object>> getTjData(int tip,String type);
}
