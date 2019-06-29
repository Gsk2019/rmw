package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.Other;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

public interface IOtherService extends IBaseService<Other> {


	Other getByCode(Other other);

	public Pager queryOtherTj_admin(Pager page, Map<String, String> map);

	public JSONObject updateOther_admin(Other other);

}
