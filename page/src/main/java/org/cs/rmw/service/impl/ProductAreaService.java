package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IProductAreaDao;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.model.ProductArea;
import org.cs.rmw.service.IProductAreaService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 
 * @ClassName: AreaService
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月31日 下午6:08:10
 */
@Service("areaService")
public class ProductAreaService extends BaseService<ProductArea> implements IProductAreaService {

	@Autowired
	private IProductAreaDao areaDao;


	/**
	 * 后台列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryProductAreaPages_admin(Pager page, Map<String, String> map){
		Pager pager = this.areaDao.queryProductAreaPages_admin(page,map);
		return pager;
	};


	/**
	 * 后台管理添加物流
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addProductArea_admin(ProductArea productArea){
		if(productArea != null){
			productArea.setCreateDate(new Date());
			productArea.setModifyDate(new Date());
			productArea.setStatus(1);
			productArea.setIsDelete(false);
			this.areaDao.insert(productArea);
		}else{
			return "{\"code\":500,\"data\":\"信息不全\"}";
		}
		return "{\"code\":200,\"data\":\"\"}";
	}

	/**
	 *  后台管理编辑
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateProductArea_admin(ProductArea productArea){

		ProductArea pt = areaDao.findById(productArea.getId());
		if(pt != null){
			pt.setAreaName(productArea.getAreaName());
			pt.setStatus(productArea.getStatus());
			this.areaDao.update(pt);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台管理删除
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteProductArea_admin(Integer id){
		ProductArea productArea = this.areaDao.findById(id);
		productArea.setIsDelete(true);
		this.areaDao.update(productArea);
		return getCode("200", "操作成功");
	}


	@Override
	public List<ProductArea> getProvice() {
		// TODO Auto-generated method stub
		return this.areaDao.getProvice();
	}

	@Override
	public List<ProductArea> getCityOrCountry(String code) {
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(code)) {
			return null;
		} else {
			return this.areaDao.getCityOrCountry(code);
		}
	}

	@Override
	public List<ProductArea> getCityOrCountryByName(String name) {
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(name)) {
			return null;
		} else {
			return this.areaDao.getCityOrCountryByName(name);
		}
	}

	@Override
	public JSONArray getPCCJson() {
		//省
		List<ProductArea> area0list = this.findAll(null);
		Collections.sort(area0list, new Comparator<ProductArea>() {
			@Override
			public int compare(ProductArea o1, ProductArea o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		List<ProductArea> area1list = new ArrayList<ProductArea>();
		area1list.addAll(area0list);
		List<ProductArea> area2list = new ArrayList<ProductArea>();
		area2list.addAll(area0list);
		JSONArray allarea = new JSONArray();
		JSONObject provice = null;
		for (ProductArea area : area0list) {
			if(null != area && area.getTopId() == 0)
			{
				String key = area.getId().toString();
				provice = new JSONObject();
				provice.put("code", key);
				provice.put("name", area.getAreaName());
				provice.put("nodes", "");
				//市
				JSONArray cities = new JSONArray();
				JSONObject city = null;
				for (ProductArea ciarea : area1list)
				{
					if(ciarea.getTopId() == area.getId())
					{
						String key2 = ciarea.getId().toString();
						city = new JSONObject();
						city.put("code", key2);
						city.put("name", ciarea.getAreaName());
						city.put("nodes", "");
						JSONArray countries = new JSONArray();
						JSONObject country = null;
						//区
						for (ProductArea coarea : area2list)
						{
							if(coarea.getTopId() == ciarea.getId())
							{
								String key3 = coarea.getId().toString();
								country = new JSONObject();
								country.put("code", key3);
								country.put("name", coarea.getAreaName());
								countries.add(country);
							}
						}
						city.put("nodes", countries);
						cities.add(city);
					}
				}
				provice.put("nodes", cities);
				allarea.add(provice);
			}
		}
		return allarea;
	}

	/*===========================================前端下面=================================================*/

    @Override
    public List<ProductArea> getAllProductArea() {
        // TODO Auto-generated method stub
        return this.areaDao.getAllProductArea();
    }

}
