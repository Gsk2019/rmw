package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="rmw_info")
public class Info extends BaseEntity {

	/**
	 * 现货/求购信息表
	 */
	
	private Integer userId;		//用户id
	private Integer type;			//1卖货信息  2求购信息  3我要供货信息
	private Integer categoryTopId;//一级分类id
	private String categoryTopName;//一级分类名称
	private Integer categoryId;	//二级分类id
	private String categoryName;	//二级分类名称
	private Integer productTopAreaId;//顶级产地id
	private String productTopArea;//顶级产地
	private Integer productAreaId;//产地id
	private String productArea;	//产地
	private String brand;			//品牌
	private String repertory;		//交割仓库地
	private Integer repertoryId;    //交割仓库地id
	private Date productDate;		//生产日期
	private Integer price;				//价格

	private Integer endPrice;		 //结束价格

	private String unit;			//计量单位
	private Integer count;			//可供数量，求购数量
	private Date expiryDate; 		//信息截止时间
	private String contactName;    //联系人
	private String contactTel;    //联系方式
	private Integer isSysTrade;	//是否平台指定 0非平台指定  1平台指定
	private Integer traderId;		//交易员id
	private String traderPhone;	//交易员电话
	private String traderName;	//交易员名字
	private Integer sort;			//排序值
	private Integer status;       //  1正常已审核通过 2.待审核  3审核失败  ,当type=3时1代表待处理 2代表已处理
	private String reason;			//审核失败原因
	private String note;			//备注

	private Integer infoId;//供货id

//	private Date showDate;    //发布日期

	@Column(name="info_id", columnDefinition = "int(11) comment '供货id'")
	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	@Column(name="ptop_area_id", columnDefinition = "int(11) comment '顶级产地id'")
	public Integer getProductTopAreaId() {
		return productTopAreaId;
	}

	public void setProductTopAreaId(Integer productTopAreaId) {
		this.productTopAreaId = productTopAreaId;
	}

	@Column(name="ptop_area", columnDefinition = "varchar(50) comment '顶级产地'")
	public String getProductTopArea() {
		return productTopArea;
	}

	public void setProductTopArea(String productTopArea) {
		this.productTopArea = productTopArea;
	}

	@Column(name="p_area_id", columnDefinition = "int(11) comment '产地 id'")
	public Integer getProductAreaId() {
		return productAreaId;
	}

	public void setProductAreaId(Integer productAreaId) {
		this.productAreaId = productAreaId;
	}

	@Column(name="repertory_id", columnDefinition = "int(11) comment '交割仓库地 id'")
	public Integer getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(Integer repertoryId) {
		this.repertoryId = repertoryId;
	}

	@Column(name="type", columnDefinition = "int(2) comment '1卖货信息2求购信息'")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="category_top_id", columnDefinition = "int(11) comment '一级分类id'")
	public Integer getCategoryTopId() {
		return categoryTopId;
	}

	public void setCategoryTopId(Integer categoryTopId) {
		this.categoryTopId = categoryTopId;
	}
	@Column(name="category_id", columnDefinition = "int(11) comment '二级分类id'")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name="product_area", columnDefinition = "varchar(100) comment '产地'")
	public String getProductArea() {
		return productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}

	@Column(name="brand", columnDefinition = "varchar(125) comment '品牌'")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Column(name="repertory", columnDefinition = "varchar(125) comment '交割仓库地'")
	public String getRepertory() {
		return repertory;
	}

	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "product_date", columnDefinition = "comment '生产日期'")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	@Column(name="count", columnDefinition = "int(11) comment '可供数量，求购数量'")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	@Column(name="unit", columnDefinition = "varchar(50) comment '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="price", columnDefinition="int(11)")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name="end_price", columnDefinition="int(11)")
	public Integer getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Integer endPrice) {
		this.endPrice = endPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_date", columnDefinition = "comment '信息有效期'")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "show_date", columnDefinition = "comment '发布日期'")
//	public Date getShowDate() {
//		return showDate;
//	}
//
//	public void setShowDate(Date showDate) {
//		this.showDate = showDate;
//	}

	@Column(name="contact_tel", columnDefinition = "varchar(18) comment '联系电话'")
	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	@Column(name="sort", columnDefinition = "int(11) comment '排序'")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name="trader_id", columnDefinition = "int(11) comment '交易员id'")
	public Integer getTraderId() {
		return traderId;
	}

	public void setTraderId(Integer traderId) {
		this.traderId = traderId;
	}

	@Column(name="trader_name")
	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	@Column(name="trader_phone", columnDefinition = "varchar(20) comment '交易员电话'")
	public String getTraderPhone() {
		return traderPhone;
	}
	public void setTraderPhone(String traderPhone) {
		this.traderPhone = traderPhone;
	}

	@Column(name="contact_name", columnDefinition = "varchar(20) comment '交易员名字'")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name="status", columnDefinition = "int(2) comment ''")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	@Column(name="user_id",length=64)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name="category_top_name")
	public String getCategoryTopName() {
		return categoryTopName;
	}

	public void setCategoryTopName(String categoryTopName) {
		this.categoryTopName = categoryTopName;
	}

	@Column(name="category_name")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name="is_sys_trade")
	public Integer getIsSysTrade() {
		return isSysTrade;
	}

	public void setIsSysTrade(Integer isSysTrade) {
		this.isSysTrade = isSysTrade;
	}
}
