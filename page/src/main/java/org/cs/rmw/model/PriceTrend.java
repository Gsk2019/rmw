package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="rmw_price_trend")
public class PriceTrend extends BaseEntity {

	/**
	 * 行情走势表
	 */
	
	private Date priceDate;
	private BigDecimal avePrice;
	private BigDecimal minPrice;
	private Integer categoryId;
	private String categoryName;
	private Integer status; //1显示 0不显示


	@Column(name="ave_price", columnDefinition="decimal(11,2) comment '平均价格'")
	public BigDecimal getAvePrice() {
		return avePrice;
	}

	public void setAvePrice(BigDecimal avePrice) {
		this.avePrice = avePrice;
	}

	@Column(name="min_price", columnDefinition="decimal(11,2) comment '平均价格'")
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name="price_date")
	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	@Column(name="category_id", columnDefinition="int(11) comment '一级品类id'")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="category_name", columnDefinition="varchar(50) comment '一级品类名称'")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
