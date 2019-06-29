package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="rmw_logistics")
public class RmwLogistics extends BaseEntity {

	/**
	 * 物流信息表
	 */
	
	private String startPoint;
	private String endPoint;
	private String phone;
	private BigDecimal price;

	@Column(name="price", columnDefinition="decimal(11,2) comment '运价'")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name="start_point", columnDefinition = "varchar(100) comment '起点'")
	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	@Column(name="end_point", columnDefinition = "varchar(100) comment '终点'")
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	@Column(name="phone", columnDefinition = "varchar(20) comment '联系方式'")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
