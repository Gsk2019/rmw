package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.Category;
import java.util.List;
import java.util.Map;

public interface ICategoryService extends IBaseService<Category> {

    List<Category> getAllCategoryList();

    List<Category> getTopCategoryList();

    JSONObject delCategory(String id);

    List<Category> findCategoryByMap(Map<String, String> map);

    JSONObject updateCategory(Category category);

    JSONObject addCategory(Category cat);

    /*===============================================接口=================================================*/

    JSONObject getCategory();

    JSONObject getCategoryByMap(Map<String, String> map);
}
