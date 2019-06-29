package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.util.Pager;

import java.util.Map;

public interface IRmwUserInfoService extends IBaseService<RmwUserInfo> {

	Pager getRmwUserInfoPages_admin(Pager page, Map<String, String> map);

	public boolean delRmwUserInfo(String id);
	
	public boolean addRmwUserInfo(RmwUserInfo rmwUserInfo);

	JSONObject checkRmwUserInfo_admin(RmwUserInfo rmwUserInfo);

	public JSONObject deleteRmwUserInfo_admin(Integer id);
	
	public boolean updateRmwUserInfo(RmwUserInfo rmwUserInfo);

	JSONObject addRmwUserInfo(Map<String, String> map);

}
