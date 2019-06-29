package org.cs.rmw.service.impl;

import org.apache.log4j.Logger;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IRmwRepertoryDao;
import org.cs.rmw.model.RmwRepertory;
import org.cs.rmw.service.IRmwRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.cs.util.Pager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: RmwRepertory 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-03 22:45:30
 */
@Service("rmwRepertoryService")
public class RmwRepertoryService extends BaseService<RmwRepertory> implements IRmwRepertoryService {
	private Logger log = Logger.getLogger(RmwRepertoryService.class);

	@Autowired
	private IRmwRepertoryDao rmwRepertoryDao;
	
	@Override
	public Pager getRmwRepertorys(Pager page){
		return this.rmwRepertoryDao.queryRmwRepertorys(page);
	}
	
	/**
	 * 鑷姩鐢熸垚
	 * @Title: delRmwRepertory 
	 * @Description: TODO
	 * @param id						涓婚敭id
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean delRmwRepertory(String id){
	
		RmwRepertory rmwRepertory = this.rmwRepertoryDao.findById(Integer.valueOf(id));
		if(rmwRepertory != null){
			rmwRepertory.setIsDelete(true);				
			this.rmwRepertoryDao.update(rmwRepertory);
		}
		return true;
	}
	
	
	/**
	 * 鑷姩鐢熸垚
	 * @Title: addRmwRepertory 
	 * @Description: TODO
	 * @param rmwRepertory						瀵硅薄
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean addRmwRepertory(RmwRepertory rmwRepertory){
	
		if(rmwRepertory != null){
			rmwRepertory.setIsDelete(false);
			rmwRepertory.setCreateDate(new Date());
			rmwRepertory.setModifyDate(new Date());
			this.rmwRepertoryDao.insert(rmwRepertory);
		}
		return true;
	}
	
	
	/**
	 *  鑷姩鐢熸垚
	 * @Title: updateRmwRepertory 
	 * @Description: TODO
	 * @param rmwRepertory						瀵硅薄
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean updateRmwRepertory(RmwRepertory rmwRepertory){
	
		RmwRepertory rmwRepertory2 = this.rmwRepertoryDao.findById(Integer.valueOf(rmwRepertory.getId()));
		if(rmwRepertory2 != null){
//			rmwRepertory.mergeTo(rmwRepertory2);
			rmwRepertory2.setAddr(rmwRepertory.getAddr());
			rmwRepertory2.setName(rmwRepertory.getName());
			rmwRepertory2.setSort(rmwRepertory.getSort());
			rmwRepertory2.setModifyDate(new Date());
			this.rmwRepertoryDao.update(rmwRepertory2);
		}
		
		return true;
	}
	
	/*==========================================下面是前端页面======================================================*/

	/**
	 *  查询所有的交割仓库
	 * @return
	 */
	@Override
	public List<RmwRepertory> getAllRmwRepertory(){
		return (List<RmwRepertory>)this.rmwRepertoryDao.findByHql("from RmwRepertory where isDelete=false order by sort desc" , null);
	}
}
