package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.ISmsDao;
import org.cs.rmw.model.SMS;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SmsDao extends BaseDao<SMS> implements ISmsDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public SMS getSMSByPhone(String phone, String code, int type) {
		String hql = "from SMS where phone=:phone and code=:code and type=:type";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("phone", phone);
		params.put("code", code);
		params.put("type", type);
		List<SMS> list = (List<SMS>)findByHql(hql, params);
        if (list!=null && list.size() > 0) {
        	return list.get(0);
        }
		return null;
	}

}
