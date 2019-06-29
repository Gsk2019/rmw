package org.cs.rmw.service;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.IBaseService;
import org.cs.rmw.model.FeedBack;
import org.cs.util.Pager;

import java.util.Map;

public interface IFeedBackService extends IBaseService<FeedBack> {

    public FeedBack addFeedBack(FeedBack feedBack);

    //意见反馈分页数据
    public Pager queryFeedBackPager(Pager pager, Map<String, String> map);

    public JSONObject result(String result, String id);

    JSONObject addFeedBack(Map<String, String> map);
}
