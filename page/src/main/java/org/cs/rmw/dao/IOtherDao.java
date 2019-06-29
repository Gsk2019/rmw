package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.Other;
import org.cs.util.Pager;

import java.util.List;
import java.util.Map;

public interface IOtherDao extends IBaseDao<Other> {



    public Pager queryOtherTj_admin(Pager page, Map<String, String> map);


}
