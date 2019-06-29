package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.model.RmwUser;
import org.cs.util.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IRmwUserService extends IBaseService<RmwUser> {
    RmwUser getByUserCode(String userCode);

    JSONObject login(Map<String, String> map, HttpServletRequest request);

    Pager getList(Pager pager, Map<String, String> map);

    JSONObject editPwd(Map<String, String> map);

    JSONObject register(RmwUser user , HttpServletRequest request);

    public JSONObject updateRmwUser(RmwUser rmwUser);

    public JSONObject deleteRmwUser_admin(String id);

    public JSONObject checkRmwUser_admin(RmwUser ru) throws ClientException;

    JSONObject identity(Map<String, String> map);

    JSONObject modifyPwd(Map<String, String> map);

    Map<String, Object> getTjData();

    RmwUser getRmwUserByOpenId(String openid);

    RmwUser getRmwUserByPhone(String phone);
}
