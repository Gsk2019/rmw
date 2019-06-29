package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.ICategoryDao;
import org.cs.rmw.model.Category;
import org.cs.rmw.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("categoryService")
public class CategoryService extends BaseService<Category> implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;


	/**
	 * 查询所有的品类
	 * @return
	 */
	public List<Category> getAllCategoryList(){
		StringBuffer stringBuffer = new StringBuffer("from Category where isDelete=:isdelete");
		Map<String, Object> params = new HashMap<>();
		params.put("isdelete", false);
		return (List<Category>)this.categoryDao.findByHql(stringBuffer.toString(), params);
	}

	/**
	 * 查询一级的品类
	 * @return
	 */
	public List<Category> getTopCategoryList(){
		StringBuffer stringBuffer = new StringBuffer("from Category where topId=0 and isDelete=:isdelete");
		Map<String, Object> params = new HashMap<>();
		params.put("isdelete", false);
		return (List<Category>)this.categoryDao.findByHql(stringBuffer.toString(), params);
	}

	@Transactional(readOnly = false)
	public JSONObject delCategory(String id){
		Category feedBack = this.categoryDao.findById(Integer.valueOf(id));
		feedBack.setIsDelete(true);
		this.categoryDao.update(feedBack);

		List<Category> clist = this.categoryDao.findCategoryByPid(Integer.valueOf(id));
		for(Category category:clist){
			category.setIsDelete(true);
			this.categoryDao.update(category);
		}
		return getCode("200", "操作成功");
	}

	public List<Category> findCategoryByMap(Map<String, String> map){
		return this.categoryDao.findCategoryByMap(map);
	}

	@Transactional(readOnly = false)
	public JSONObject updateCategory(Category category){

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", category.getCategoryName());
		params.put("id", category.getId());
		List<?> mpold = this.categoryDao.findByHql("from Category where categoryName = :name and id != :id and isDelete=false", params);
		if(mpold != null && !mpold.isEmpty()){
			return getCode("500", "品类已存在");
		}

		Category mgm = this.categoryDao.findById(category.getId());
		if(mgm != null){
			mgm.setCategoryName(category.getCategoryName());
			mgm.setModifyDate(new Date());
			mgm = this.categoryDao.update(mgm);
		}

		return getCode("200", "操作成功");
	}

	@Transactional(readOnly = false)
	public JSONObject addCategory(Category cat){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", cat.getCategoryName());
		params.put("topId", cat.getTopId());
		List<?> mpold = this.categoryDao.findByHql("from Category where categoryName = :name and topId=:topId and isDelete=false", params);
		if(mpold != null && !mpold.isEmpty()){
			return getCode("500", "已存在相同名称的品类");
		}

		cat.setIsDelete(false);
		cat.setModifyDate(new Date());
		cat.setCreateDate(new Date());
		cat.setStatus(1);

		cat = this.categoryDao.insert(cat);
		return getCode("200", "操作成功");
	}

	/*===============================================接口=================================================*/

	public JSONObject getCategory(){
		List<Category> alllist = this.categoryDao.findCategoryByMap(new HashMap<>());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allcategory", alllist);
		return jsonObject;
	}

	public JSONObject getCategoryByMap(Map<String, String> map){
		List<Category> alllist = this.categoryDao.findCategoryByMap(map);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("category", alllist);
		return jsonObject;
	}
}
