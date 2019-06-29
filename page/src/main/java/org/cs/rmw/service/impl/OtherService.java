package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.constants.RetCode;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IOtherDao;
import org.cs.rmw.model.Other;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.service.IOtherService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("otherService")
public class OtherService extends BaseService<Other> implements IOtherService {

	@Autowired 
	private IOtherDao otherDao;

	@Transactional(readOnly = false)
	public Other getByCode(Other other){
		Map<String, Object> params = new HashMap<>();
		params.put("code", other.getCode());
		params.put("type", other.getType());
		Other bak = this.otherDao.findOneByHql("from Other where code =:code and type=:type and isDelete=false", params);
		if(bak == null){
			other.setContents("");
			other.setNum(0);
			other.setIsDelete(false);
			bak = this.otherDao.insert(other);
		}
		return bak;
	}

	/**
	 * 后台管理查询统计信息
	 * @param page
	 * @param map
	 * @return
	 */
	public Pager queryOtherTj_admin(Pager page, Map<String, String> map){
		return otherDao.queryOtherTj_admin(page, map);
	}

	/**
	 *  后台管理编辑统计信息
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updateOther_admin(Other other){

		Other pt = otherDao.findById(other.getId());
		if(pt != null){
			if("1".equals(pt.getType())){
				pt.setNum(other.getNum());
			}else {
				pt.setContents(other.getContents());
				pt.setCode(other.getCode());
			}
			pt.setModifyDate(new Date());
			this.otherDao.update(pt);
		}
		return getCode("200", "操作成功");
	}

}
