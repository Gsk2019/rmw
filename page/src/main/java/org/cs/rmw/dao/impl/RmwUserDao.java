package org.cs.rmw.dao.impl;

import org.cs.core.dao.impl.BaseDao;
import org.cs.rmw.dao.IFeedBackDao;
import org.cs.rmw.dao.IRmwUserDao;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.model.RmwUser;
import org.cs.util.DateUtil;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class RmwUserDao extends BaseDao<RmwUser> implements IRmwUserDao {

    public RmwUser getByUserCode(String userCode){

        StringBuffer stringBuffer = new StringBuffer("from RmwUser where code = :code and isDelete=:isdelete and status=:status");
        Map<String, Object> params = new HashMap<>();
        params.put("code", userCode);
        params.put("isdelete", false);
        params.put("status", "1");
        return this.findOneByHql(stringBuffer.toString(), params);
    }

    @Override
    public RmwUser getByAccount(String account)
    {
//		Map<String, Object> params = new HashMap<>();
//		params.put("usercode", usercode);
        Map<String, Object> params = new HashMap<>();
        params.put("account", account);
        StringBuffer sb = new StringBuffer("from RmwUser where userName=?");
        RmwUser user = this.findOneByHql(sb.toString(), params);
        return user;
    }

    @Override
    public RmwUser getByPhone(String phone,Integer type) {
        Map<String, Object> params = new HashMap<>();
//		Map<String,Object> params = new HashMap<>();
//		params.put("phone", phone);
        String hql = "from RmwUser ma where ma.phone=:phone";
        params.put("phone", phone);
        RmwUser users = this.findOneByHql(hql, params);

        return users;
    }

    public Pager getList(Pager pager, Map<String, String> map){
        StringBuffer sb = new StringBuffer("from RmwUser ma where isDelete=0  ");
        Map<String, Object> params = new HashMap<>();

        if(StringUtil.isNotBlank(map.get("userName"))){
            sb.append(" and userName like :userName");
            params.put("userName", "%" + map.get("userName") + "%");
        }
        if(StringUtil.isNotBlank(map.get("phone"))){
            sb.append(" and phone like :phone");
            params.put("phone", "%" + map.get("phone") + "%");
        }

        if(StringUtil.isNotBlank(map.get("isIdentity"))){
            sb.append(" and isIdentity = :isIdentity");
            params.put("isIdentity", map.get("isIdentity"));
        }

        Pager page = this.findPageByHql(sb.toString(), pager, params);

        return page;
    }

    public RmwUser findByWhere(String pro,  String provalue){
        Map<String, Object> params = new HashMap<>();
        params.put("pro", provalue);
        return this.findOneByHql("from RmwUser where isDelete=false and " + pro + "=:pro", params);
    }


    public Map<String, Object> getTjData(){
        Map<String, Object> datamap=new HashMap<>();

        StringBuffer sb1 = new StringBuffer(" select COUNT(1) count from rmw_user where to_days(create_date) = to_days(now()) AND is_delete=0 ");
         List<Map<String, Object>> list1=(List<Map<String, Object>>)this.findBySql(sb1.toString()) ;
        Map<String, Object> daymap1=list1.get(0);
        datamap.put("day1",daymap1.get("count"));
        StringBuffer sb2 = new StringBuffer(" select COUNT(1) count from rmw_user where to_days(create_date) = to_days(now()) AND is_delete=0 AND is_iddentity =3" );
        List<Map<String, Object>> list2=(List<Map<String, Object>>)this.findBySql(sb2.toString()) ;
        Map<String, Object> daymap2=list2.get(0);
        datamap.put("day2",daymap2.get("count"));

        StringBuffer sb3 = new StringBuffer(" SELECT COUNT(1) count FROM rmw_user WHERE DATE_FORMAT( create_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) AND is_delete=0 ");
        List<Map<String, Object>> monthlist1=(List<Map<String, Object>>)this.findBySql(sb3.toString()) ;
        Map<String, Object> monthmap1=monthlist1.get(0);
        datamap.put("month1",monthmap1.get("count"));
        StringBuffer sb4 = new StringBuffer(" SELECT COUNT(1) count FROM rmw_user WHERE DATE_FORMAT( create_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) AND is_delete=0 AND is_iddentity =3 ");
        List<Map<String, Object>> monthlist2=(List<Map<String, Object>>)this.findBySql(sb4.toString()) ;
        Map<String, Object> monthmap2=monthlist2.get(0);
        datamap.put("month2",monthmap2.get("count"));

        StringBuffer sb5 = new StringBuffer(" SELECT COUNT(1) count FROM rmw_user WHERE is_delete=0 ");
        List<Map<String, Object>> sumlist1=(List<Map<String, Object>>)this.findBySql(sb5.toString()) ;
        Map<String, Object> summap1=sumlist1.get(0);
        datamap.put("total1",summap1.get("count"));
        StringBuffer sb6 = new StringBuffer(" SELECT COUNT(1) count FROM rmw_user WHERE is_delete=0 AND is_iddentity =3");
        List<Map<String, Object>> sumlist2=(List<Map<String, Object>>)this.findBySql(sb6.toString()) ;
        Map<String, Object> summap2=sumlist2.get(0);
        datamap.put("total2",summap2.get("count"));
        return datamap;
    }

    public RmwUser getRmwUserByOpenId(String openid){
        Map<String, Object> params = new HashMap<>();
        params.put("pro", openid);
        return this.findOneByHql("from RmwUser where isDelete=false and openid=:pro", params);
    }


    public RmwUser getRmwUserByPhone(String phone){
        Map<String, Object> params = new HashMap<>();
        params.put("pro", phone);
        return this.findOneByHql("from RmwUser where isDelete=false and phone=:pro", params);
    }
}
