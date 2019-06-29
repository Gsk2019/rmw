package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang.StringUtils;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IFeedBackDao;
import org.cs.rmw.dao.IRmwUserDao;
import org.cs.rmw.dao.ISmsDao;
import org.cs.rmw.model.NewsComment;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IRmwUserService;
import org.cs.rmw.service.ISmsService;
import org.cs.util.*;
import org.cs.ws.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("rmwUserService")
public class RmwUserService extends BaseService<RmwUser> implements IRmwUserService {

	@Autowired
	private IRmwUserDao rmwUserDao;
	@Autowired
	private ISmsService smsService;


	public RmwUser getByUserCode(String userCode){
		return this.rmwUserDao.getByUserCode(userCode);
	}

	@Override
	@Transactional(readOnly=false)
	public JSONObject login(Map<String, String> map, HttpServletRequest request) {

		String plat = request.getParameter("plat");
		String userAgment = request.getAttribute("userAgent").toString();

		if(map==null){
			return getJson(RetCode.EC_0401, "");
		}
		String phone = map.get("phone");
		String code = map.get("code");
//		String account = map.get("account");
		String pwd = map.get("pwd");
		String type = map.get("type");// 1:密码登录 account pwd 2：验证码登录 phone code

		if("1".equals(type)){
			if(StringUtil.isBlank(phone) || StringUtil.isBlank(pwd)){
				return getJson(RetCode.EC_0400, null);
			}
			RmwUser user = this.rmwUserDao.findByWhere("phone", phone);
			if(user == null){
				return getJson(RetCode.EC_0402, null);//RetCode.EC_0400
			}
			if("2".equals(user.getStatus())){
				return getJson(RetCode.EC_0402, null);
			}
			String md5Pass = Md5.getMd5(pwd.trim() + user.getSalt());
			if(md5Pass != null && md5Pass.equals(user.getPwd())){
				if("web".equals(plat)){
					return getJson(RetCode.SUCCESS, getLoginInfoByWeb(user, userAgment));
				}else{
					return getJson(RetCode.SUCCESS, getLoginInfo(user));
				}
			}else{
//				return getJson(RetCode.EC_0500, null);
				return getJson(0, "", "用户名或密码错误");
			}
		}else if("2".equals(type)){
			if(StringUtil.isBlank(phone) || StringUtil.isBlank(code)){
				return getJson(RetCode.EC_0500, null);
			}
			RmwUser  user = rmwUserDao.getByPhone(phone, null);
			if("2".equals(user.getStatus())){
				return getJson(RetCode.EC_0402, null);
			}
			if(user == null){
				return getJson(RetCode.EC_0400, null);
			}
			JSONObject json = this.smsService.checkCode(phone, 3, code);
			if("1".equals(json.get("ret").toString())){
				if("web".equals(plat)){
					return getJson(RetCode.SUCCESS, getLoginInfoByWeb(user, userAgment));
				}else{
					return getJson(RetCode.SUCCESS, getLoginInfo(user));
				}
			}else{
				return getJson(RetCode.EC_1503, null);
			}
		}else{
			return getJson(RetCode.EC_0500, null);
		}

	}

	private JSONObject getLoginInfoByWeb(RmwUser user, String userAgment){
		JSONObject data = new JSONObject();
		data.put("name", user.getUserName());
		data.put("img", user.getImg());
		data.put("phone", user.getPhone());
		data.put("identity", user.getIsIdentity());
		// 放入缓存
		String tk = AccessTokenUtil.genTk(user.getId(), userAgment);
		AccessTokenUtil.putTk(user.getId(), tk);
		data.put("tk", tk);
		return data;
	}

	private JSONObject getLoginInfo(RmwUser user){
		JSONObject data = new JSONObject();
		data.put("name", user.getUserName());
		data.put("img", user.getImg());
		data.put("phone", user.getPhone());
		data.put("identity", user.getIsIdentity());
		// 放入缓存
		String tk = AccessTokenUtil.genTk(user.getId());
		AccessTokenUtil.putTk(user.getId(), tk);
		data.put("tk", tk);
		return data;
	}


	public Pager getList(Pager pager, Map<String, String> map){
		pager = this.rmwUserDao.getList(pager, map);
		List<Map<String, Object>> results = (List<Map<String, Object>>)pager.getResults();

		return pager;
	}

	@Override
	@Transactional(readOnly=false)
	public JSONObject editPwd(Map<String, String> map) {
		String phone = map.get("phone");
		String code = map.get("code");
		String pwd = map.get("pwd");

		JSONObject checkCode = smsService.checkCode(phone, 2, code);
		int checkRet = checkCode.getIntValue("ret");
		if(checkRet!=1)
			return checkCode;

		JSONObject json = new JSONObject();

		if(StringUtils.isNotBlank(phone)){
			RmwUser user = rmwUserDao.getByPhone(phone,null);
			if(user!=null){
				String md5Pass = Md5.getMd5(pwd.trim() + user.getSalt());

				Map<String, Object> params= new HashMap<>();
				params.put("pwd", md5Pass);
				params.put("phone", phone);
				rmwUserDao.execute("update rmw_user ma set ma.is_delete=0, ma.pwd=:pwd where ma.phone=:phone ", "sql", params);

				json = getJson(RetCode.SUCCESS, "");
				/*Msg msg = new Msg();
				msg.setName("密码修改提醒");
				msg.setStatus(0);
				Date date = new Date();
				msg.setNote("您在"+DateUtil.formatTime(date, "yyyy年MM月dd日 HH:mm:ss")+"进行修改密码操作成功，请确认是本人操作，如非本人操作，请联系管理员。");
				msg.setType(2);
				msg.setUserCode(user.get("code").toString());
				msg.setCreateDate(date);
				msg.setIsDelete(false);
				msg.setModifyDate(date);
				this.msgService.add(msg);*/
			}else{
				json = getJson(RetCode.EC_0400, null);
			}
		}else{
			json = getJson(RetCode.EC_0500, null);
		}
		return json;
	}

	@Override
	@Transactional(readOnly=false)
	public JSONObject modifyPwd(Map<String, String> map) {
		String old_password = map.get("old_password");
		String new_password = map.get("new_password");
		String userId = map.get("userId");

		JSONObject json = new JSONObject();

		if(StringUtils.isNotBlank(new_password) && StringUtils.isNotBlank(old_password) && StringUtils.isNotBlank(userId)){
			RmwUser user = rmwUserDao.findById(	Integer.valueOf(userId));
			if(user!=null){
				String md5Pass = Md5.getMd5(old_password.trim() + user.getSalt());
				if(md5Pass.equals(user.getPwd())){
					user.setModifyDate(new Date());
					String newpass = Md5.getMd5(new_password.trim() + user.getSalt());
					user.setPwd(newpass);
					user = this.rmwUserDao.update(user);
					json = getJson(RetCode.SUCCESS, "");
				}else{
					getJson(0, null, "错误的旧密码");
				}
			}else{
				json = getJson(RetCode.EC_0400, null);
			}
		}else{
			json = getJson(RetCode.EC_0500, null);
		}
		return json;
	}

	@Override
	@Transactional(readOnly=false)
	public JSONObject identity(Map<String, String> map) {
		String userId = map.get("userId");
		JSONObject json = getJson(RetCode.SUCCESS);

			RmwUser user = rmwUserDao.findById(	Integer.valueOf(userId));
			if(user!=null){

				if(StringUtil.isNotBlank(map.get("licenceJy"))){
					user.setLicenceJy(map.get("licenceJy"));
				}
				user.setBusinessLicence(map.get("businessLicence"));
				user.setCompany(map.get("company"));
				user.setCompanyOwner(map.get("companyOwner"));
				user.setCompanyTel(map.get("companyTel"));
				user.setIdentity(map.get("identity"));
				user.setIdentityFront(map.get("identityFront"));
				user.setIdentityReverse(map.get("identityReverse"));
				if(StringUtil.isNotBlank(map.get("licenceSc"))){
					user.setLicenceSc(map.get("licenceSc"));
				}
				user.setIsIdentity("2");
				user = this.rmwUserDao.update(user);

			}else{
				json = getJson(RetCode.EC_0400, null);
			}
		return json;
	}

	/**
	 *
	 * @Title: register
	 * @Description: 用户注册
	 * @param: @param user
	 * @param: @return
	 */
	@Override
	@Transactional(readOnly=false)
	public JSONObject register(RmwUser user, HttpServletRequest request){

		String phone = user.getPhone();  //手机号
		try {
			String pwd =user.getPwd();//密码
			String code = user.getCode();    //注册验证码 借用用户代码属性赋值

			user.setCode("");//code清空 按正常业务处理

			//验证码校验
			JSONObject checkCode = smsService.checkCode(phone, 1, code);
			int checkRet = checkCode.getIntValue("ret");
			if (checkRet != 1)
				return checkCode;

			RmwUser appuser = rmwUserDao.findByWhere("phone", user.getPhone());
			//已有该手机
			if (null != appuser) {
				return getJson(0, "", "手机已注册");
			}
			if(StringUtil.isNotBlank(pwd)){
				user.setPwd(pwd);
			}else{
				return getJson(0, "", "密码错误");
			}
			user.setSalt(RandomUtil.generateString(6));

			String md5Pass = Md5.getMd5(pwd + user.getSalt());
			user.setPwd(md5Pass);

			user.setImg("");
			user.setStatus("1");
			user.setIsIdentity("1");
			user.setIsDelete(false);
			user.setCreateDate(new Date());
			user.setModifyDate(new Date());

			RmwUser mgmUser= this.rmwUserDao.insert(user);

			//推送系统消息
			/*Map<String,Object> param = new HashMap<String,Object>();
			param.put("type", "0");
			msgService.sendMsg(user.getId(),"东旭通", "欢迎您注册东旭集团，您的注册账号是："+phone, user.getClientId(),param );*/
            String userAgment = request.getAttribute("userAgent").toString();

			return getJson(1, getLoginInfoByWeb(user, userAgment), "注册成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getJson(0, null, "注册失败");
	}

	/***************************************以下是后台方法***********************************************************/

	/**
	 *  自动生成
	 *  后台管理编辑新闻内容
	 * @Title: updateNews
	 * @Description: TODO
	 * @param rmwUser						对象
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateRmwUser(RmwUser rmwUser){

		RmwUser ru = this.rmwUserDao.findById(rmwUser.getId());
		if(ru != null){
			if(!"1".equals(ru.getIsIdentity())){
//				ru.setImg(rmwUser.getImg());
				ru.setBusinessLicence(rmwUser.getBusinessLicence());
				ru.setCompany(rmwUser.getCompany());
				ru.setCompanyOwner(rmwUser.getCompanyOwner());
				ru.setCompanyTel(rmwUser.getCompanyTel());
				ru.setIdentity(rmwUser.getIdentity());
//				ru.setIdentityFront(rmwUser.getIdentityFront());
//				ru.setIdentityReverse(rmwUser.getIdentityReverse());
				ru.setLicenceJy(rmwUser.getLicenceJy());
				ru.setLicenceSc(rmwUser.getLicenceSc());
				ru.setModifyDate(new Date());
			}
			ru.setUserName(rmwUser.getUserName());
			this.rmwUserDao.update(ru);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台管理删除新闻
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteRmwUser_admin(String id){
		RmwUser ru = this.rmwUserDao.findById(Integer.valueOf(id));
		ru.setIsDelete(true);
		this.rmwUserDao.update(ru);
		return getCode("200", "操作成功");
	}

	/**
	 *  后台管理审核
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject checkRmwUser_admin(RmwUser ru) throws ClientException {
		RmwUser u = rmwUserDao.findById(ru.getId());
		if(u != null){
			u.setIsIdentity(ru.getIsIdentity());
			this.rmwUserDao.update(u);
			AliMsgUtils.sendSms(u.getPhone(),"SMS_146290198");
		}
		return getCode("200", "操作成功");
	}

	/**
	 *  后台获取统计数据
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public Map<String, Object> getTjData() {
		return rmwUserDao.getTjData();
	}

	/**
	 *  根据openid获取用户
	 * @return
	 * @return: boolean
	 */
	@Override
	public RmwUser getRmwUserByOpenId(String openid) {
		return rmwUserDao.getRmwUserByOpenId(openid);
	}

	@Override
	public RmwUser getRmwUserByPhone(String phone) {
		return rmwUserDao.getRmwUserByPhone(phone);
	}
}
