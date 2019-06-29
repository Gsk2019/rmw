package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.model.Admin;
import org.cs.rmw.dao.IRmwUserInfoDao;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.RmwUserInfo;
import org.cs.rmw.service.IRmwUserInfoService;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.cs.util.Pager;

/**
 * 
 * @ClassName: RmwUserInfo 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-08 21:32:01
 */
@Service("rmwUserInfoService")
public class RmwUserInfoService extends BaseService<RmwUserInfo> implements IRmwUserInfoService {
	private Logger log = Logger.getLogger(RmwUserInfoService.class);

	@Autowired
	private IRmwUserInfoDao rmwUserInfoDao;

	/**
	 * 后台查询委托列表
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getRmwUserInfoPages_admin(Pager page, Map<String, String> map){
		return this.rmwUserInfoDao.queryRmwUserPages_admin(page,map);
	}

	/**
	 * 鑷姩鐢熸垚
	 * @Title: delRmwUserInfo 
	 * @Description: TODO
	 * @param id						涓婚敭id
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean delRmwUserInfo(String id){
	
		RmwUserInfo rmwUserInfo = this.rmwUserInfoDao.findById(id);
		if(rmwUserInfo != null){
			rmwUserInfo.setIsDelete(true);				
			this.rmwUserInfoDao.update(rmwUserInfo);
		}
		return true;
	}

    /**
     * 后台管理删除信息
     * @return
     */
    @Transactional(readOnly=false)
    public JSONObject deleteRmwUserInfo_admin(Integer id){
        RmwUserInfo rmwUserInfo = this.rmwUserInfoDao.findById(id);
        rmwUserInfo.setIsDelete(true);
        this.rmwUserInfoDao.update(rmwUserInfo);
        return getCode("200", "操作成功");
    }

    /**
     *  后台管理处理委托信息
     * @return
     * @return: boolean
     */
    @Override
    @Transactional(readOnly = false)
    public JSONObject checkRmwUserInfo_admin(RmwUserInfo rmwUserInfo) {
        RmwUserInfo i = rmwUserInfoDao.findById(rmwUserInfo.getId());
        if(i != null){
            i.setStatus(rmwUserInfo.getStatus());
            i.setModifyDate(new Date());
            if (rmwUserInfo.getStatus()==2){
                i.setNote(rmwUserInfo.getNote());
            }else if (rmwUserInfo.getStatus()==1){
                i.setNote(null);
            }
            this.rmwUserInfoDao.update(i);
        }
        return getCode("200", "操作成功");
    }
	
	
	/**
	 * 鑷姩鐢熸垚
	 * @Title: addRmwUserInfo 
	 * @Description: TODO
	 * @param rmwUserInfo						瀵硅薄
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean addRmwUserInfo(RmwUserInfo rmwUserInfo){
	
		if(rmwUserInfo != null){
			this.rmwUserInfoDao.insert(rmwUserInfo);
		}
		return true;
	}
	
	
	/**
	 *  鑷姩鐢熸垚
	 * @Title: updateRmwUserInfo 
	 * @Description: TODO
	 * @param rmwUserInfo						瀵硅薄
	 * @return
	 * @return: boolean
	 */
	@Override
	public boolean updateRmwUserInfo(RmwUserInfo rmwUserInfo){
	
		RmwUserInfo rmwUserInfo2 = this.rmwUserInfoDao.findById(rmwUserInfo.getId());
		if(rmwUserInfo2 != null){
			rmwUserInfo.mergeTo(rmwUserInfo2);
			this.rmwUserInfoDao.update(rmwUserInfo2);
		}
		
		return true;
	}
	
//	======================================================下面是前端方法==========================================================


	@Override
	@Transactional(readOnly = false)
	public JSONObject addRmwUserInfo(Map<String, String> map){

		if(map != null){

			RmwUserInfo userinfo = new RmwUserInfo();
			userinfo.setUserId(Integer.valueOf(map.get("userId")));
			if("1".equals(map.get("status"))){
				if(StringUtil.isBlank(map.get("infoId"))){
					return  this.getJson(150, null, "未获取到现货信息");
				}
			}else if("2".equals(map.get("status"))){
				if(StringUtil.isBlank(map.get("infoId"))){
					return  this.getJson(150, null, "未获取到供货信息");
				}
			}else{
				return  this.getJson(150, null, "未知的请求信息");
			}
			userinfo.setStatus(Integer.valueOf(map.get("status")));
			userinfo.setInfoId(Integer.valueOf(map.get("infoId")));
			userinfo.setCount(Integer.valueOf(map.get("count")));

			Map<String, Object> params = new HashMap<>();
			params.put("userid",Integer.valueOf(map.get("userId")));
			params.put("status",Integer.valueOf(map.get("status")));
			params.put("infoId",Integer.valueOf(map.get("infoId")));
			RmwUserInfo ruserinfo = this.rmwUserInfoDao.findOneByHql("from RmwUserInfo where isDelete=false and userId=:userid and status=:status and infoId=:infoId", params);

			if(ruserinfo != null){
				if("1".equals(map.get("status"))){
					return getJson(150, null, "您已委托过此商品, 请耐心等待工作人员的联系");
				}else if("2".equals(map.get("status"))){
					return getJson(150, null, "您已发起过供货, 请耐心等待工作人员的联系");
				}
			}

			userinfo.setIsDelete(false);
			userinfo.setCreateDate(new Date());
			userinfo.setModifyDate(new Date());
			this.rmwUserInfoDao.insert(userinfo);
		}
		return getJson(RetCode.SUCCESS);
	}
	
}
