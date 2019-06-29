package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.Info;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: IInfoDao
 * @Description:  
 * @author: gsk
 */
public interface IInfoDao extends IBaseDao<Info> {

	/**
	 * 分页查询发布的信息
	 * @param page
	 * @param map
	 * @return
	 */
	Pager queryInfoPages_admin(Pager page, Map<String, String> map);

	/**
	 * 后台查询供货信息
	 * @param page
	 * @param map
	 * @return
	 */
	Pager queryGhInfoPages_admin(Pager page, Map<String, String> map);

	Pager getGoods(Pager page, Map<String, String> map);

	Pager getMyInfo(Pager page, Map<String, String> map);

	//后台获取统计内容
	List<Map<String, Object>> getTjData(int tip, String type);
}
