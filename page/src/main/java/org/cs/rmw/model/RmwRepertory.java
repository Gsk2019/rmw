package org.cs.rmw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import org.cs.core.model.BaseEntity;

/**
 * 仓库交割地址
 * @ClassName: RmwRepertory 
 * @Description:  
 * @author: cosco gt.fan@msn.cn
 * @date: 2018-09-03 22:45:30
 */
@Table(name = "rmw_repertory")
@Entity
public class RmwRepertory extends  BaseEntity {

    private static final Class<RmwRepertory> ENTITY_CLASS = RmwRepertory.class;
    
	public RmwRepertory()
	{
	}

	
	private String name;  // 仓库交割地址名称
	
	private String addr;  // 详细地址

	private Integer sort;  // 排序值
	
	 
	
	@Column(name = "name", columnDefinition="VARCHAR(125) default NULL comment '仓库交割地址名称'" )
	public String  getName()
	{
		return name;
	}
	
	public void  setName(String name)
	{
		this.name= name;
	}

	
	@Column(name = "addr", columnDefinition="VARCHAR(255) default NULL comment '详细地址'" )
	public String  getAddr()
	{
		return addr;
	}
	
	public void  setAddr(String addr)
	{
		this.addr= addr;
	}

	@Column(name = "sort" )
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Class entityClass()
	{
		return ENTITY_CLASS;
	}
	
	
 	protected StringBuilder toStringBuilder(StringBuilder sb)
 	{
 		return sb 
		.append(",id=").append(this.id)
		.append(",name=").append(this.name)
		.append(",addr=").append(this.addr)
		.append(",createDate=").append(this.createDate)
		.append(",modifyDate=").append(this.modifyDate)
		.append(",isDelete=").append(this.isDelete)
		;
 	}
 	
 	
 	@Override
	public String toString()
	{
		return toStringBuilder(new StringBuilder().append("RmwRepertory[")).append(']').toString();
	}
	 
	
	public Map<String,Object> mapValue()
	{
	    Map<String,Object> map=new HashMap<String,Object>();
	    if(this.id!=null) map.put("id",this.id);
		
	    if(this.name!=null) map.put("name",this.name);
		
	    if(this.addr!=null) map.put("addr",this.addr);
		
		  if(getCreateDate() != null) map.put("createDate", this.createDate);
		  if(getModifyDate() != null) map.put("modifyDate", this.modifyDate);
		  if(getIsDelete() != null) map.put("isDelete", this.isDelete);
		return  map;
	}
	
	public void mergeTo(RmwRepertory en)
	{
		  if(getId()!=null) en.setId(getId());
		  if(getName()!=null) en.setName(getName());
		  if(getAddr()!=null) en.setAddr(getAddr());
		  if(getCreateDate() != null) en.setCreateDate(getCreateDate());
		  if(getModifyDate() != null) en.setModifyDate(getModifyDate());
		  if(getIsDelete() != null) en.setIsDelete(getIsDelete());
	}
	
  	@Override
  	public void copyFrom(Object obj)
  	{  
  		 if(!(obj instanceof RmwRepertory)) {
  	 		super.copyFrom(obj);
  	 	}else {
  	  		RmwRepertory bean=( RmwRepertory)obj;
     				this.setId(bean.getId());
			this.setName(bean.getName());
			this.setAddr(bean.getAddr());
     	this.setCreateDate(bean.getCreateDate());
     	this.setModifyDate(bean.getModifyDate());
     	this.setIsDelete(bean.getIsDelete());
     	}
 	 }
}
