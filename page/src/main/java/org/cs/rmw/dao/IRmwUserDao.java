package org.cs.rmw.dao;

import org.cs.core.dao.IBaseDao;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.model.RmwUser;
import org.cs.util.Pager;

import java.util.Map;

public interface IRmwUserDao extends IBaseDao<RmwUser>{

    RmwUser getByUserCode(String userCode);

    RmwUser getByAccount(String account);

    RmwUser getByPhone(String phone,Integer type);

    Pager getList(Pager pager, Map<String, String> map);

    RmwUser findByWhere(String pro,  String provalue);

    Map<String, Object> getTjData();

    RmwUser getRmwUserByOpenId(String openid);

    RmwUser getRmwUserByPhone(String phone);
}
