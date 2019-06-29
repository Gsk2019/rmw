package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 物流信息表
 * @ClassName: MgmProduct
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Table(name = "rmw_logistics")
@Entity
public class Logistics extends BaseEntity {

	private Integer userId;

	private String startPoint;//起点
	private String endPoint;//终点
	private String phone;//联系方式
	private String linkMan;//联系人
	private Integer price;//价格

	private Integer tonnage;//运输吨位
	private String consignment;//委托货物
	private Date expiryDate; 		//信息截止时间
	private Integer type;//1物流报价 2 物流委托

	private Integer status;//1待审核 2审核通过 3驳回

	@Column(name = "user_id", columnDefinition = "int(11)")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_date")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "type", columnDefinition = "int(2)")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTonnage() {
		return tonnage;
	}

	public void setTonnage(Integer tonnage) {
		this.tonnage = tonnage;
	}

	public String getConsignment() {
		return consignment;
	}

	public void setConsignment(String consignment) {
		this.consignment = consignment;
	}

	@Column(name = "start_point")
	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	@Column(name = "end_point")
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "link_man")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
