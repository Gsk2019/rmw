package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.Logistics;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

public interface ILogisticsService extends IBaseService<Logistics> {


	String addLogistics_admin(Logistics logistics);

	JSONObject updateLogistics_admin(Logistics logistics);

	Pager getLogisticsBypages_admin(Pager page, Map<String, String> map);

	JSONObject deleteLogistics_admin(Integer id);

	Pager getLogistics(Pager pager, Map<String, String> map);

	JSONObject addLogistics(Map<String, String> map);

	public JSONObject checkLogistics_admin(Logistics logistics);
}
