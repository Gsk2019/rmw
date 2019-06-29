package org.cs.core.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @ClassName: BaseEntity 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月20日 下午11:09:30
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -6690941339269328820L;

	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"属性名称
	public static final String ON_SAVE_METHOD_NAME = "onSave";// "保存"方法名称
	public static final String ON_UPDATE_METHOD_NAME = "onUpdate";// "更新"方法名称
	public static final String IS_DELETE = "isDelete";
	
	protected Integer id;// ID
	protected Boolean isDelete;
	protected Date createDate;// 创建日期
	protected Date modifyDate;// 修改日期


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="is_delete", columnDefinition="TINYINT(1) default 0")
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void copyFrom(Object obj)
	{
		try
		{
			BeanUtils.copyProperties(this, obj);
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
