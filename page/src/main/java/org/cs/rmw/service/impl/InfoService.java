package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.mgr.admin.dao.IAdminDao;
import org.cs.mgr.admin.dao.IUserRoleDao;
import org.cs.mgr.admin.model.Admin;
import org.cs.rmw.dao.IInfoDao;
import org.cs.rmw.dao.ILogisticsDao;
import org.cs.rmw.dao.IRmwUserDao;
import org.cs.rmw.model.Info;
import org.cs.rmw.model.Logistics;
import org.cs.rmw.model.RmwNews;
import org.cs.rmw.model.RmwUser;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.ILogisticsService;
import org.cs.util.OSSClientUtil;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("infoService")
public class InfoService extends BaseService<Info> implements IInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IAdminDao adminDao;
    @Autowired
    private IRmwUserDao rmwUserDao;

	/**
	 *  后台管理编辑信息内容
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateBuyInfo_admin(Info info){

		Info i = infoDao.findById(info.getId());
		if(i != null){
			if (StringUtil.isNotBlank(info.getCategoryTopName())){
				String[] arr1=info.getCategoryTopName().split(",");
				i.setCategoryTopId(Integer.valueOf(arr1[0]));
				i.setCategoryTopName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getCategoryName())){
				String[] arr1=info.getCategoryName().split(",");
				i.setCategoryId(Integer.valueOf(arr1[0]));
				i.setCategoryName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductTopArea())){
				String[] arr1=info.getProductTopArea().split(",");
				i.setProductTopAreaId(Integer.valueOf(arr1[0]));
				i.setProductTopArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductArea())){
				String[] arr1=info.getProductArea().split(",");
				i.setProductAreaId(Integer.valueOf(arr1[0]));
				i.setProductArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getRepertory())){
				String[] arr1=info.getRepertory().split(",");
				i.setRepertoryId(Integer.valueOf(arr1[0]));
				i.setRepertory(arr1[1]);
			}

			i.setBrand(info.getBrand());
			i.setPrice(info.getPrice());
			i.setEndPrice(info.getEndPrice());
			i.setCount(info.getCount());
			i.setExpiryDate(info.getExpiryDate());
			i.setContactName(info.getContactName());
			i.setContactTel(info.getContactTel());
			i.setSort(info.getSort());
			i.setNote(info.getNote());
			i.setModifyDate(new Date());
			if (i.getTraderId()!=null&&(i.getTraderId()!=info.getTraderId())){
				Admin a=adminDao.findById(info.getTraderId());
				i.setTraderId(info.getTraderId());
				i.setTraderName(a.getName());
				i.setTraderPhone(a.getPhone());
			}
			this.infoDao.update(i);
		}
		return getCode("200", "操作成功");
	}

	/**
	 *  后台管理编辑信息内容
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateSaleInfo_admin(Info info){

		Info i = infoDao.findById(info.getId());
		if(i != null){
			if (StringUtil.isNotBlank(info.getCategoryTopName())){
				String[] arr1=info.getCategoryTopName().split(",");
				i.setCategoryTopId(Integer.valueOf(arr1[0]));
				i.setCategoryTopName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getCategoryName())){
				String[] arr1=info.getCategoryName().split(",");
				i.setCategoryId(Integer.valueOf(arr1[0]));
				i.setCategoryName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductTopArea())){
				String[] arr1=info.getProductTopArea().split(",");
				i.setProductTopAreaId(Integer.valueOf(arr1[0]));
				i.setProductTopArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductArea())){
				String[] arr1=info.getProductArea().split(",");
				i.setProductAreaId(Integer.valueOf(arr1[0]));
				i.setProductArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getRepertory())){
				String[] arr1=info.getRepertory().split(",");
				i.setRepertoryId(Integer.valueOf(arr1[0]));
				i.setRepertory(arr1[1]);
			}

			i.setBrand(info.getBrand());
			i.setPrice(info.getPrice());
			i.setCount(info.getCount());
			i.setExpiryDate(info.getExpiryDate());
			i.setProductDate(info.getProductDate());
			i.setUnit(info.getUnit());
			i.setSort(info.getSort());
			i.setContactName(info.getContactName());
			i.setContactTel(info.getContactTel());
			i.setNote(info.getNote());
			i.setModifyDate(new Date());
			if (i.getTraderId()!=null&&(i.getTraderId()!=info.getTraderId())){
				Admin a=adminDao.findById(info.getTraderId());
				i.setTraderId(info.getTraderId());
				i.setTraderName(a.getName());
				i.setTraderPhone(a.getPhone());
			}
			this.infoDao.update(i);
		}
		return getCode("200", "操作成功");
	}

	/**
	 *  后台管理审核信息内容
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject checkInfo_admin(Info info) throws Exception {
		Info i = infoDao.findById(info.getId());
		if(i != null){
			i.setStatus(info.getStatus());
			if(i.getType()!=3){
				if (info.getStatus()==3){
					i.setReason(info.getReason());
					i.setTraderId(null);
					i.setTraderName(null);
					i.setTraderPhone(null);
				}else if (info.getStatus()==1){
					if (i.getIsSysTrade()==1){//平台指定交易员
						Admin a=adminDao.findById(info.getTraderId());
						i.setTraderId(info.getTraderId());
						i.setTraderName(a.getName());
						i.setTraderPhone(a.getPhone());
					}else {
						i.setTraderName(i.getContactName());
						i.setTraderPhone(i.getContactTel());
					}
					i.setReason(null);
				}
			}else {
				i.setNote(info.getNote());
			}
			this.infoDao.update(i);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台管理添加
	 * @Title: addNews
	 * @Description: TODO
	 * @param
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addInfo(Info info) throws Exception {

		if(info != null){
			RmwUser rmwUser =rmwUserDao.getByPhone(info.getTraderPhone(),1);
			if (rmwUser==null){
//				return JSONObject.toJSONString(getCode("500","用户不存在"));
				throw new Exception("用户不存在");
			}
			info.setCreateDate(new Date());
			info.setUserId(rmwUser.getId());
			if (StringUtil.isNotBlank(info.getCategoryTopName())){
				String[] arr1=info.getCategoryTopName().split(",");
				info.setCategoryTopId(Integer.valueOf(arr1[0]));
				info.setCategoryTopName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getCategoryName())){
				String[] arr1=info.getCategoryName().split(",");
				info.setCategoryId(Integer.valueOf(arr1[0]));
				info.setCategoryName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductTopArea())){
				String[] arr1=info.getProductTopArea().split(",");
				info.setProductTopAreaId(Integer.valueOf(arr1[0]));
				info.setProductTopArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductArea())){
				String[] arr1=info.getProductArea().split(",");
				info.setProductAreaId(Integer.valueOf(arr1[0]));
				info.setProductArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getRepertory())){
				String[] arr1=info.getRepertory().split(",");
				info.setRepertoryId(Integer.valueOf(arr1[0]));
				info.setRepertory(arr1[1]);
			}
			info.setType(1);
			info.setTraderPhone(null);
			info.setIsDelete(false);
			info.setModifyDate(new Date());
			info.setStatus(2);
			this.infoDao.insert(info);
			return JSONObject.toJSONString(getCode("200","保存成功"));
		}else{
//			return "{\"code\":500,\"data\":\"信息不全\"}";
			return JSONObject.toJSONString(getCode("500","信息不全"));
		}
//		return "{\"code\":200,\"data\":\"\"}";

	}

	/**
	 * 后台管理添加
	 * @Title: addNews
	 * @Description: TODO
	 * @param
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public String addBuyInfo(Info info) throws Exception {

		if(info != null){
			RmwUser rmwUser =rmwUserDao.getByPhone(info.getTraderPhone(),1);
			if (rmwUser==null){
				throw new Exception("用户不存在");
			}
			info.setCreateDate(new Date());
			info.setUserId(rmwUser.getId());
			if (StringUtil.isNotBlank(info.getCategoryTopName())){
				String[] arr1=info.getCategoryTopName().split(",");
				info.setCategoryTopId(Integer.valueOf(arr1[0]));
				info.setCategoryTopName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getCategoryName())){
				String[] arr1=info.getCategoryName().split(",");
				info.setCategoryId(Integer.valueOf(arr1[0]));
				info.setCategoryName(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductTopArea())){
				String[] arr1=info.getProductTopArea().split(",");
				info.setProductTopAreaId(Integer.valueOf(arr1[0]));
				info.setProductTopArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getProductArea())){
				String[] arr1=info.getProductArea().split(",");
				info.setProductAreaId(Integer.valueOf(arr1[0]));
				info.setProductArea(arr1[1]);
			}
			if (StringUtil.isNotBlank(info.getRepertory())){
				String[] arr1=info.getRepertory().split(",");
				info.setRepertoryId(Integer.valueOf(arr1[0]));
				info.setRepertory(arr1[1]);
			}
			info.setType(2);
			info.setTraderPhone(null);
			info.setIsDelete(false);
			info.setModifyDate(new Date());
			info.setStatus(2);
			this.infoDao.insert(info);
			return JSONObject.toJSONString(getCode("200","保存成功"));
		}else{
			return JSONObject.toJSONString(getCode("500","信息不全"));
		}
	}

	@Override
	@Transactional(readOnly = false)
	public String isExitUser(String phone) {

		RmwUser rmwUser =rmwUserDao.getByPhone(phone,1);
		if(rmwUser == null){
			return JSONObject.toJSONString(getCode("500","用户不存在"));
		}else{
			return JSONObject.toJSONString(getCode("200",""));
		}
	}

	/**
	 * 后台信息列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getInfoBypages_admin(Pager page, Map<String, String> map){
		Pager pager = this.infoDao.queryInfoPages_admin(page,map);
		return pager;
	}

	/**
	 * 后台信息列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager queryGhInfoPages_admin(Pager page, Map<String, String> map){
		Pager pager = this.infoDao.queryGhInfoPages_admin(page,map);
		return pager;
	}

	/**
	 * 后台管理删除信息
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deleteInfos_admin(Integer id){
		Info info = this.infoDao.findById(id);
		info.setIsDelete(true);
		this.infoDao.update(info);
		return getCode("200", "操作成功");
	}

	/**
	 * 后台管理获取交易员
	 * @return
	 */
	@Transactional(readOnly=false)
	public List<Map<String,Object>> getTraders_admin(){
		List<Map<String,Object>>  tlist= this.userRoleDao.findBySql("SELECT ma.id,ma.phone,ma.`name` FROM mgr_user_role ur LEFT JOIN mgr_admin ma ON ur.user_code=ma.`code` WHERE ur.role_code =102");
		return tlist;
	}

	/**
	 * 获取当天的平均价格
	 * @return
	 */
	@Transactional(readOnly=false)
	public String getAveragePrice_admin(Integer categoryId){
		List<Map<String,Object>>  tlist= this.infoDao.findBySql("SELECT AVG(r.price) averagePrice FROM rmw_info r WHERE r.is_delete=0 AND r.type=1 AND r.`status`=1 AND r.category_top_id="+categoryId+" AND r.create_date <NOW() AND r.create_date > date_sub(NOW(),interval 1 day)");
		if (tlist!=null&&tlist.size()>0){
			Map<String,Object> m=tlist.get(0);
			if (m.get("averagePrice")!=null)
				return m.get("averagePrice").toString();
			return null;
		}else {
			return null;
		}
	}

	/**
	 * 获取当天的最低价格
	 * @return
	 */
	@Transactional(readOnly=false)
	public String getMinPrice_admin(Integer categoryId){
		List<Map<String,Object>>  tlist= this.infoDao.findBySql("SELECT MIN(r.price) minPrice FROM rmw_info r WHERE r.is_delete=0 AND r.type=1 AND r.`status`=1 AND r.category_top_id="+categoryId+" AND r.create_date <NOW() AND r.create_date > date_sub(NOW(),interval 1 day)");
		if (tlist!=null&&tlist.size()>0){
			Map<String,Object> m=tlist.get(0);
			if (m.get("minPrice")!=null)
				return m.get("minPrice").toString();
			return null;
		}else {
			return null;
		}
	}


	/*=================================================下面是前端==============================================*/
	/**
	 * 前端信息列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getGoodsStocks(Pager page, Map<String, String> map){
		Pager pager = this.infoDao.getGoods(page,map);
		return pager;
	}

	/**
	 * 前端信息列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getGoodsSupply(Pager page, Map<String, String> map){
		Pager pager = this.infoDao.getGoods(page,map);
		return pager;
	}

	/**
	 * 前端我的信息列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getMyInfo(Pager page, Map<String, String> map){
		Pager pager = this.infoDao.getMyInfo(page,map);
		return pager;
	}

	/**
	 * 前端添加现货
	 * @param map
	 * @return
	 */
	@Override
    @Transactional(readOnly = false)
	public JSONObject addStocks(Map<String, String> map){

	    Integer userid = Integer.valueOf(map.get("userId"));
        RmwUser user= this.rmwUserDao.findById(userid);
        if(!user.getIsIdentity().equals("3")){
            return getJson(0, null, "请先进行会员入驻认证");
        }

		Info info = new Info();
		info.setUserId(Integer.valueOf(map.get("userId")));
		info.setContactTel(map.get("contactTel"));
		info.setProductArea(map.get("productArea"));
		info.setRepertory(map.get("repertory"));
		info.setProductTopAreaId(Integer.valueOf(map.get("productTopAreaId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
			if(StringUtil.isNotBlank(map.get("productDate"))){
		    	info.setProductDate(sdf.parse(map.get("productDate")));
			}
        }catch(Exception  e){
            e.printStackTrace();
        }
        if(StringUtil.isNotBlank(map.get("categoryId"))){
			info.setCategoryId(Integer.valueOf(map.get("categoryId")));
		}
        info.setCategoryName(map.get("categoryName"));
        info.setCategoryTopId(Integer.valueOf(map.get("categoryTopId")));
        info.setCount(Integer.valueOf(map.get("count")));
		info.setIsSysTrade(Integer.valueOf(map.get("isSysTrade")));
        info.setCategoryTopName(map.get("categoryTopName"));
        info.setBrand(map.get("brand"));
        info.setCategoryName(map.get("categoryName"));
        info.setProductTopArea(map.get("productTopArea"));
        if(StringUtil.isNotBlank(map.get("price"))){
        	info.setPrice(Integer.valueOf(map.get("price")));
		}
        info.setProductAreaId(Integer.valueOf(map.get("productAreaId")));
        info.setContactName(map.get("contactName"));
        info.setRepertoryId(Integer.valueOf(map.get("repertoryId")));
		if(StringUtil.isNotBlank(map.get("expiryDate"))){
			info.setExpiryDate(new Date(Long.valueOf(map.get("expiryDate"))));
		}
		if(StringUtil.isNotBlank(map.get("infoId"))){
			info.setInfoId(Integer.valueOf(map.get("infoId")));
		}
        info.setUnit(map.get("unit"));
		info.setType(Integer.valueOf(map.get("type")));
        info.setIsDelete(false);
        info.setStatus(2);
        info.setSort(0);
        info.setModifyDate(new Date());
        info.setCreateDate(new Date());

        info = this.infoDao.insert(info);

		return getJson(RetCode.SUCCESS);
	}

	/**
	 * 前端添加我要供货
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject addStocks3(Map<String, String> map){
		Info info = new Info();
		info.setUserId(Integer.valueOf(map.get("userId")));
		info.setContactTel(map.get("contactTel"));
		info.setProductArea(map.get("productArea"));
		info.setRepertory(map.get("repertory"));
		info.setProductTopAreaId(Integer.valueOf(map.get("productTopAreaId")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			info.setProductDate(sdf.parse(map.get("productDate")));
		}catch(Exception  e){
			e.printStackTrace();
		}
		if(StringUtil.isNotBlank(map.get("categoryId"))){

			info.setCategoryId(Integer.valueOf(map.get("categoryId")));
		}
		info.setCategoryName(map.get("categoryName"));
		info.setCategoryTopId(Integer.valueOf(map.get("categoryTopId")));
		info.setCount(Integer.valueOf(map.get("count")));
		info.setCategoryTopName(map.get("categoryTopName"));
		info.setBrand(map.get("brand"));
		info.setCategoryName(map.get("categoryName"));
		info.setProductTopArea(map.get("productTopArea"));
		info.setPrice(Integer.valueOf(map.get("price")));
		if(StringUtil.isNotBlank(map.get("productAreaId"))){

			info.setProductAreaId(Integer.valueOf(map.get("productAreaId")));
		}
		info.setContactName(map.get("contactName"));
		info.setRepertoryId(Integer.valueOf(map.get("repertoryId")));
		info.setExpiryDate(new Date(Long.valueOf(map.get("expiryDate"))));
		info.setUnit(map.get("unit"));

		info.setIsDelete(false);
		info.setType(3);
		info.setStatus(1);
		info.setSort(0);
		info.setModifyDate(new Date());
		info.setCreateDate(new Date());

		info.setInfoId(Integer.valueOf(map.get("infoId")));
		info.setType(Integer.valueOf(map.get("type")));

		info = this.infoDao.insert(info);

		return getJson(RetCode.SUCCESS);
	}

	/**
	 * 前端添加现货
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject addSupply(Map<String, String> map){
        Integer userid = Integer.valueOf(map.get("userId"));
        RmwUser user= this.rmwUserDao.findById(userid);
        if(!user.getIsIdentity().equals("3")){
            return getJson(0, null, "请先进行会员入驻认证");
        }

		Info info = new Info();
		info.setUserId(Integer.valueOf(map.get("userId")));
		info.setContactTel(map.get("contactTel"));
		info.setNote(map.get("note"));
		info.setProductArea(map.get("productArea"));
		info.setRepertory(map.get("repertory"));
		info.setProductTopAreaId(Integer.valueOf(map.get("productTopAreaId")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(StringUtil.isNotBlank(map.get("productDate"))){
				info.setProductDate(sdf.parse(map.get("productDate")));
			}
		}catch(Exception  e){
			e.printStackTrace();
		}
		if (StringUtil.isNotBlank(map.get("categoryId"))){
			info.setCategoryId(Integer.valueOf(map.get("categoryId")));
			info.setCategoryName(map.get("categoryName"));
		}
		info.setCategoryTopId(Integer.valueOf(map.get("categoryTopId")));
		info.setCount(Integer.valueOf(map.get("count")));
		info.setIsSysTrade(Integer.valueOf(map.get("isSysTrade")));
		info.setCategoryTopName(map.get("categoryTopName"));
		info.setBrand(map.get("brand"));
		info.setCategoryName(map.get("categoryName"));
		info.setProductTopArea(map.get("productTopArea"));
		if(StringUtil.isNotBlank(map.get("price"))){
			info.setPrice(Integer.valueOf(map.get("price")));
		}
		if(StringUtil.isNotBlank(map.get("endPrice"))){
			info.setEndPrice(Integer.valueOf(map.get("endPrice")));
		}
		info.setProductAreaId(Integer.valueOf(map.get("productAreaId")));
		info.setContactName(map.get("contactName"));
		info.setRepertoryId(Integer.valueOf(map.get("repertoryId")));
		info.setExpiryDate(new Date(Long.valueOf(map.get("expiryDate"))));
		info.setUnit(map.get("unit"));

		info.setIsDelete(false);
		info.setType(2);
		info.setStatus(2);
		info.setSort(0);
		info.setModifyDate(new Date());
		info.setCreateDate(new Date());

		info = this.infoDao.insert(info);

		return getJson(RetCode.SUCCESS);
	}

	/**
	 * 批量重新发布
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject gen(Map<String, String> map){
		String ids = map.get("ids");
		String[] ida = ids.split(",");

		for(String id:ida){
			if(StringUtil.isNotBlank(id)){
				Info info = this.infoDao.findById(Integer.valueOf(id));
				info.setStatus(2);
				info = this.infoDao.update(info);
			}
		}

		return getJson(RetCode.SUCCESS);
	}

	/**
	 * 批量删除
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject dels(Map<String, String> map){
		String ids = map.get("ids");
		String[] ida = ids.split(",");

		for(String id:ida){
			if(StringUtil.isNotBlank(id)){
				Info info = this.infoDao.findById(Integer.valueOf(id));
				info.setIsDelete(true);
				info = this.infoDao.update(info);
			}
		}

		return getJson(RetCode.SUCCESS);
	}

	/**
	 * 前端编辑现货
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject editStocks(Map<String, String> map){
		Info info = this.infoDao.findById(Integer.valueOf(map.get("id")));
		info.setUserId(Integer.valueOf(map.get("userId")));
		info.setContactTel(map.get("contactTel"));
		info.setProductArea(map.get("productArea"));
		info.setRepertory(map.get("repertory"));
		info.setProductTopAreaId(Integer.valueOf(map.get("productTopAreaId")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			info.setProductDate(sdf.parse(map.get("productDate")));
		}catch(Exception  e){
			e.printStackTrace();
		}
		info.setCategoryId(Integer.valueOf(map.get("categoryId")));
		info.setCategoryName(map.get("categoryName"));
		info.setCategoryTopId(Integer.valueOf(map.get("categoryTopId")));
		info.setCount(Integer.valueOf(map.get("count")));
		info.setCategoryTopName(map.get("categoryTopName"));
		info.setBrand(map.get("brand"));
		info.setCategoryName(map.get("categoryName"));
		info.setProductTopArea(map.get("productTopArea"));
		info.setPrice(Integer.valueOf(map.get("price")));
		info.setProductAreaId(Integer.valueOf(map.get("productAreaId")));
		info.setContactName(map.get("contactName"));
		info.setRepertoryId(Integer.valueOf(map.get("repertoryId")));
		info.setExpiryDate(new Date(Long.valueOf(map.get("expiryDate"))));
		info.setUnit(map.get("unit"));

		info.setIsDelete(false);
		info.setType(1);
		info.setStatus(2);
		info.setModifyDate(new Date());

		info = this.infoDao.update(info);

		return getJson(RetCode.SUCCESS);
	}

    /**
     * 前端添加现货
     * @param map
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public JSONObject editSupply(Map<String, String> map){
        Info info = this.infoDao.findById(Integer.valueOf(map.get("id")));
        info.setUserId(Integer.valueOf(map.get("userId")));
        info.setContactTel(map.get("contactTel"));
        info.setNote(map.get("note"));
        info.setProductArea(map.get("productArea"));
        info.setRepertory(map.get("repertory"));
        info.setProductTopAreaId(Integer.valueOf(map.get("productTopAreaId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            info.setProductDate(sdf.parse(map.get("productDate")));
        }catch(Exception  e){
            e.printStackTrace();
        }
        info.setCategoryId(Integer.valueOf(map.get("categoryId")));
        info.setCategoryName(map.get("categoryName"));
        info.setCategoryTopId(Integer.valueOf(map.get("categoryTopId")));
        info.setCount(Integer.valueOf(map.get("count")));
        info.setCategoryTopName(map.get("categoryTopName"));
        info.setBrand(map.get("brand"));
        info.setCategoryName(map.get("categoryName"));
        info.setProductTopArea(map.get("productTopArea"));
        info.setPrice(Integer.valueOf(map.get("price")));
        info.setEndPrice(Integer.valueOf(map.get("endPrice")));
        info.setProductAreaId(Integer.valueOf(map.get("productAreaId")));
        info.setContactName(map.get("contactName"));
        info.setRepertoryId(Integer.valueOf(map.get("repertoryId")));
        info.setExpiryDate(new Date(Long.valueOf(map.get("expiryDate"))));
        info.setUnit(map.get("unit"));

        info.setIsDelete(false);
        info.setType(2);
        info.setStatus(2);
        info.setSort(0);
        info.setModifyDate(new Date());

        info = this.infoDao.update(info);

        return getJson(RetCode.SUCCESS);
    }

	/**
	 *  后台获取统计数据
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public List<Map<String, Object>> getTjData(int tip,String type){
		return this.infoDao.getTjData(tip,type);
	}
}
