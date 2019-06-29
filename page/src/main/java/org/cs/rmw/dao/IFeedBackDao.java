package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.FeedBack;
import org.cs.util.Pager;

import java.util.Map;

public interface IFeedBackDao extends IBaseDao<FeedBack>{

    public Pager queryFeedBackPager(Pager pager, Map<String, String> map);
}
