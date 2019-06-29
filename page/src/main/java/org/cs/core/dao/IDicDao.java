package org.cs.core.dao;

import java.util.List;

import org.cs.core.model.Dic;

public interface IDicDao extends IBaseDao<Dic>{

	/**
	 * 根据code返回对应的数据字典
	 * @Title: findByCode 
	 * @Description: TODO
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByCode(String code);
	
	/**
	 * 根据类型返回数据字典列表
	 * @Title: findByType 
	 * @Description: TODO
	 * @param type
	 * @return
	 * @return: List<Dic>
	 */
	public List<Dic> findByType(String type);
	
	/**
	 * 根据类型和code返回数据字典
	 * @Title: findByTypeAndCode 
	 * @Description: TODO
	 * @param type
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByTypeAndCode(String type, String code);
	
	/**
	 * 
	 * @Title: findByCodeForLock 
	 * @Description: TODO
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByCodeForLock(String code);
	
	/**
	 * 
	 * @Title: findByTypeAndCodeForLock 
	 * @Description: TODO
	 * @param type
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByTypeAndCodeForLock(String type, String code);
	
	/**
	 * 
	 * @Title: findAndIncrease 
	 * @Description: TODO
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findAndIncrease(String code);
	
}
