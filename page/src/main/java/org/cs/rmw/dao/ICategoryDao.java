package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.Category;
import org.cs.rmw.model.FeedBack;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

public interface ICategoryDao extends IBaseDao<Category>{

    List<Category> findCategoryByPid(Integer pid);
    List<Category> findCategoryByMap(Map<String, String> map);
}
