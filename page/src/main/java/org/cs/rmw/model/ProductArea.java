package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品产地
 */
@Entity
@Table(name="rmw_product_area")
public class ProductArea implements Serializable {

	private Integer id;
	private String areaName;//产地名称
	private Integer topId; //上级id

	private Integer status;//展示状态 1：展示 0：不展示
	protected Boolean isDelete;
	protected Date createDate;// 创建日期
	protected Date modifyDate;// 修改日期

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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="area_name", columnDefinition = "varchar(125) comment '产地名称'")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Column(name="top_id", columnDefinition = "int(11) comment '上级id'")
	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	@Column(name="status", columnDefinition = "int(2) comment '展示状态 1：展示 0：不展示'")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
