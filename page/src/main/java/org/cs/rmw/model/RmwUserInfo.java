package org.cs.rmw.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Date;
import org.cs.core.model.BaseEntity;

/**
 * 
 * @ClassName: RmwUserInfo 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-08 21:32:01
 */
@Table(name = "rmw_user_info")
@Entity
public class RmwUserInfo extends  BaseEntity {

    private static final Class<RmwUserInfo> ENTITY_CLASS = RmwUserInfo.class;
    
	public RmwUserInfo()
	{
	}

	private Integer userId;  // 
	
	private Integer infoId;  //

	private Integer count;//委托数量
	
	private Integer status;  // 1:待处理  2已处理

	private String note;//备注
	@Column(name = "count", columnDefinition="INT default NULL" )
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "user_id", columnDefinition="INT default NULL" )
	public Integer  getUserId()
	{
		return userId;
	}
	
	public void  setUserId(Integer userId)
	{
		this.userId= userId;
	}

	
	@Column(name = "info_id", columnDefinition="INT default NULL" )
	public Integer  getInfoId()
	{
		return infoId;
	}
	
	public void  setInfoId(Integer infoId)
	{
		this.infoId= infoId;
	}

	
	@Column(name = "status", columnDefinition="INT default NULL" )
	public Integer  getStatus()
	{
		return status;
	}
	
	public void  setStatus(Integer status)
	{
		this.status= status;
	}

	
	 
	public Class entityClass()
	{
		return ENTITY_CLASS;
	}
	
	
 	protected StringBuilder toStringBuilder(StringBuilder sb)
 	{
 		return sb 
		.append(",userId=").append(this.userId)
		.append(",infoId=").append(this.infoId)
		.append(",status=").append(this.status)
		.append(",id=").append(this.id)
		.append(",createDate=").append(this.createDate)
		.append(",modifyDate=").append(this.modifyDate)
		.append(",isDelete=").append(this.isDelete)
		;
 	}
 	
 	
 	@Override
	public String toString()
	{
		return toStringBuilder(new StringBuilder().append("RmwUserInfo[")).append(']').toString();
	}
	 
	
	public Map<String,Object> mapValue()
	{
	    Map<String,Object> map=new HashMap<String,Object>();
	    if(this.userId!=null) map.put("userId",this.userId);
		
	    if(this.infoId!=null) map.put("infoId",this.infoId);
		
	    if(this.status!=null) map.put("status",this.status);
		
	    if(this.id!=null) map.put("id",this.id);
		
		  if(getCreateDate() != null) map.put("createDate", this.createDate);
		  if(getModifyDate() != null) map.put("modifyDate", this.modifyDate);
		  if(getIsDelete() != null) map.put("isDelete", this.isDelete);
		return  map;
	}
	
	public void mergeTo(RmwUserInfo en)
	{
		  if(getUserId()!=null) en.setUserId(getUserId());
		  if(getInfoId()!=null) en.setInfoId(getInfoId());
		  if(getStatus()!=null) en.setStatus(getStatus());
		  if(getId()!=null) en.setId(getId());
		  if(getCreateDate() != null) en.setCreateDate(getCreateDate());
		  if(getModifyDate() != null) en.setModifyDate(getModifyDate());
		  if(getIsDelete() != null) en.setIsDelete(getIsDelete());
	}
	
  	@Override
  	public void copyFrom(Object obj)
  	{  
  		 if(!(obj instanceof RmwUserInfo)) {
  	 		super.copyFrom(obj);
  	 	}else {
  	  		RmwUserInfo bean=( RmwUserInfo)obj;
     				this.setUserId(bean.getUserId());
			this.setInfoId(bean.getInfoId());
			this.setStatus(bean.getStatus());
			this.setId(bean.getId());
     	this.setCreateDate(bean.getCreateDate());
     	this.setModifyDate(bean.getModifyDate());
     	this.setIsDelete(bean.getIsDelete());
     	}
 	 }

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
