package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IFeedBackDao;
import org.cs.rmw.model.FeedBack;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IFeedBackService;
import org.cs.rmw.service.IRmwUserService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service("feedbackService")
public class FeedBackService extends BaseService<FeedBack> implements IFeedBackService {

	@Autowired
	private IFeedBackDao feedBackDao;
	@Autowired
	private IRmwUserService rmwUserService;

	@Override
	@Transactional(readOnly = false)
	public FeedBack addFeedBack(FeedBack feedBack) {
		feedBack.setStatus("1");
		feedBack.setIsDelete(false);
		RmwUser user = this.rmwUserService.findById(feedBack.getUserId());
		feedBack.setUserName(user.getUserName());

		return feedBackDao.insert(feedBack);
	}

	@Override
	public Pager queryFeedBackPager(Pager pager, Map<String, String> map) {
		return feedBackDao.queryFeedBackPager(pager, map);
	}

	@Override
	@Transactional(readOnly = false)
	public JSONObject result(String result, String id) {

		if (StringUtil.isNotBlank(id)) {
			FeedBack feedBack = this.feedBackDao.findById(Integer.valueOf(id));
			feedBack.setModifyDate(new Date());
			feedBack.setStatus("2");
			if (StringUtil.isNotBlank(result)) {
				feedBack.setResult(result);
			} else {
				feedBack.setResult("");
			}
			this.feedBackDao.update(feedBack);


			/*Msg msg = new Msg();
			msg.setName("反馈问题处理");
			msg.setStatus(0);
			Date date = new Date();
			msg.setNote("您在"+DateUtil.formatTime(date, "yyyy年MM月dd日 HH:mm:ss")+"提交的反馈问题已被处理，请到“我的反馈”中进行查看。");
			msg.setType(2);
			msg.setUserCode(feedBack.getUserCode());
			msg.setCreateDate(date);
			msg.setIsDelete(false);
			msg.setModifyDate(date);
			this.msgService.add(msg);*/

			return getCode("200", "操作成功");

		}else{
			return getCode("500", "未获取到反馈信息");
		}

	}

//	==================================================下面前端方法===================================================================================

	@Override
	@Transactional(readOnly = false)
	public JSONObject addFeedBack(Map<String, String> map) {

		FeedBack feedBack = new FeedBack();

		feedBack.setUserId(Integer.valueOf(map.get("userId")));
		RmwUser user = this.rmwUserService.findById(Integer.valueOf(map.get("userId")));
		feedBack.setUserName(user.getUserName());
		feedBack.setStatus("1");
		feedBack.setType(Integer.valueOf(map.get("type")));
		feedBack.setPhone(user.getPhone());
		feedBack.setContent(map.get("content"));
		feedBack.setStatus("1");
		feedBack.setIsDelete(false);
		feedBack.setUserName(user.getUserName());

		feedBack = feedBackDao.insert(feedBack);

		return getJson(RetCode.SUCCESS);
	}
}
