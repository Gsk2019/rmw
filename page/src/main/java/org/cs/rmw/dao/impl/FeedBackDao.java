package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IFeedBackDao;
import org.cs.rmw.model.FeedBack;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FeedBackDao extends BaseDao<FeedBack> implements IFeedBackDao {

    @Override
    public Pager queryFeedBackPager(Pager pager, Map<String, String> map){
        StringBuffer stringBuffer = new StringBuffer("select id, user_id,user_name,phone, modify_date, create_date, content, result, status from app_feedback where 1=1 and is_delete =:isdelete");
	    Map<String, Object> params = new HashMap<>();
	    params.put("isdelete", false);
	    if(StringUtil.isNotBlank(map.get("userCode"))){
	        params.put("usercode", map.get("userCode"));
	        stringBuffer.append(" and user_code=:usercode");
        }

        if(StringUtil.isNotBlank(map.get("name"))){
            params.put("name", "%" + map.get("name") + "%");
            stringBuffer.append(" and content like :name");
        }
        stringBuffer.append(" order by create_date desc");
        return this.findPageBySql(stringBuffer.toString(), pager, params);
    }
}
