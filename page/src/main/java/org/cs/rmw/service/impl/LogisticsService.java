package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.ILogisticsDao;
import org.cs.rmw.dao.INewsDao;
import org.cs.rmw.dao.IRmwUserDao;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.ILogisticsService;
import org.cs.rmw.service.INewsService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("logisticsService")
public class LogisticsService extends BaseService<Logistics> implements ILogisticsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ILogisticsDao logisticsDao;
	@Autowired
	private IRmwUserDao rmwUserDao;

	/**
	 *  后台管理编辑新闻内容
	 * @Title: updateMgmProduct
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateLogistics_admin(Logistics logistics){

		Logistics lo = this.logisticsDao.findById(logistics.getId());
		if(lo != null){
			lo.setStatus(logistics.getStatus());
			this.logisticsDao.update(lo);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台物流列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getLogisticsBypages_admin(Pager page, Map<String, String> map){
		Pager pager = this.logisticsDao.queryLogisticsPager_admin(page,map);
		return pager;
	}


	/**
	 * 后台管理添加物流
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addLogistics_admin(Logistics logistics){


		if(logistics != null){
			logistics.setCreateDate(new Date());
			logistics.setModifyDate(new Date());
			logistics.setStatus(2);
			logistics.setIsDelete(false);
			this.logisticsDao.insert(logistics);
		}else{
			return "{\"code\":500,\"data\":\"信息不全\"}";
		}
		return "{\"code\":200,\"data\":\"\"}";
	}

	/**
	 * 后台管理删除物流
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteLogistics_admin(Integer id){
		Logistics logistics = this.logisticsDao.findById(id);
		logistics.setIsDelete(true);
		this.logisticsDao.update(logistics);
		return getCode("200", "操作成功");
	}

	/**
	 *  后台管理审核
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject checkLogistics_admin(Logistics logistics) {
		Logistics n = logisticsDao.findById(logistics.getId());
		if(n != null){
			n.setStatus(logistics.getStatus());
			this.logisticsDao.update(n);
		}
		return getCode("200", "操作成功");
	}

//	===========================================前端接口市容=============================================================================
	/**
	 * 前台物流列表查询
	 * @param map
	 * @return
	 */
	@Override
	public Pager getLogistics(Pager pager,Map<String, String> map){
		return this.logisticsDao.getLogistics(pager, map);
	}

	@Override
	@Transactional(readOnly = false)
	public JSONObject addLogistics(Map<String, String> map){

		Logistics logistics = new Logistics();
		logistics.setUserId(Integer.valueOf(map.get("userId")));
		RmwUser user = this.rmwUserDao.findById(Integer.valueOf(map.get("userId")));
		logistics.setLinkMan(user.getUserName());
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			logistics.setExpiryDate(sdf.parse(map.get("expiryDate")));
		}catch(Exception e){
			e.printStackTrace();
			return getJson(150, null, "请稍后再试");
		}
		logistics.setPhone(map.get("phone"));
		logistics.setTonnage(Integer.valueOf(map.get("tonnage")));
		logistics.setEndPoint(map.get("endPoint"));
		if(StringUtil.isNotBlank(map.get("price"))){
			logistics.setPrice(Integer.valueOf(map.get("price")));
		}
		logistics.setStartPoint(map.get("startPoint"));
		logistics.setConsignment(map.get("consignment"));
		logistics.setIsDelete(false);
		logistics.setCreateDate(new Date());
		logistics.setModifyDate(new Date());
		logistics.setStatus(1);
		logistics.setType(Integer.valueOf(map.get("type")));
		this.logisticsDao.insert(logistics);
		return getJson(RetCode.SUCCESS);
	}

}
