package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IOtherDao;
import org.cs.rmw.model.Other;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OtherDao extends BaseDao<Other> implements IOtherDao {


    /**
     * 后台查询统计信息
     * @return
     */
    @Override
    public Pager queryOtherTj_admin(Pager page, Map<String, String> map){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type",map.get("type"));
        StringBuffer sb = new StringBuffer("SELECT o.id,o.`code`,o.modify_date,o.num,o.contents from other o WHERE o.type= :type ");
        return this.findPageBySql(sb.toString(),page,params);
    }

}
