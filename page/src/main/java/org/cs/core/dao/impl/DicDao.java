package org.cs.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cs.core.dao.IDicDao;
import org.cs.core.model.Dic;
import org.springframework.stereotype.Repository;

@Repository
public class DicDao extends BaseDao<Dic> implements IDicDao{

	@Override
	public Dic findByCode(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return this.findOneByHql("from Dic where code =:code", params);
	}
	
	
	@Override
	public Dic findByCodeForLock(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return this.findOneByHqlForLock("from Dic where code =:code", params);
	}
	
	
	@Override
	public Dic findAndIncrease(String code){
		
		Dic dic = findByCodeForLock(code);
		if(dic != null){
			int value = Integer.valueOf(dic.getValue()) + 1;
			dic.setValue(value + "");
			this.update(dic);
		}
		return dic;
		
	}
	
	
	
	@Override
	public List<Dic> findByType(String type) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		return (List<Dic>) this.findByHql("from Dic where type =:type", params);
	}
	
	@Override
	public Dic findByTypeAndCode(String type, String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("type", type);
		return this.findOneByHql("from Dic where code =:code and type =:type", params);
	}
	
	@Override
	public Dic findByTypeAndCodeForLock(String type, String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("type", type);
		return this.findOneByHqlForLock("from Dic where code =:code and type =:type", params);
	}
	
	
	
}
