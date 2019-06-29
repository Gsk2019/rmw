package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.ProductArea;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IAreaDao 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月31日 下午3:00:52
 */
public interface IProductAreaDao extends IBaseDao<ProductArea> {

	public List<ProductArea> getProvice() ;
	
	public List<ProductArea> getCityOrCountry(String code) ;
	
	public List<ProductArea> getCityOrCountryByName(String name) ;

	List<ProductArea> getAllProductArea();

	Pager queryProductAreaPages_admin(Pager page, Map<String, String> map);
	
}
