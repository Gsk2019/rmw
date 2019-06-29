package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="rmw_user")
public class RmwUser extends BaseEntity {

	/**
	 * 用户表
	 */
	private static final long serialVersionUID = 6132411775437223903L;
	
	private String userName;
	private String phone;
	private String pwd;
	private String salt;
	private String img;

	private String identity;
	private String identityFront;
	private String identityReverse;
	private String company;
	private String companyTel;


	private String companyOwner;
	private String businessLicence;
	private String licenceJy;
	private String licenceSc;

	private String status;
	private String isIdentity;//1待提交认证 2审核中 3认证成功 4认证未通过
	private String code;

	private String openid;

	@Column(name="openid", columnDefinition = "varchar(255) default '' comment 'wx openid'")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name="is_iddentity", columnDefinition = "varchar(2) comment '1待提交认证 2审核中 3认证成功'")
	public String getIsIdentity() {
		return isIdentity;
	}

	public void setIsIdentity(String isIdentity) {
		this.isIdentity = isIdentity;
	}

	@Column(name="img", columnDefinition = "varchar(200) comment '头像'")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name="user_name", columnDefinition = "varchar(50) not null comment '用户姓名'")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(columnDefinition = "varchar(11) not null comment '手机号'")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="pwd", columnDefinition = "varchar(255) not null comment '用户密码'")
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name="salt", columnDefinition = "varchar(8) not null comment '盐值'")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name="company", columnDefinition = "varchar(50) comment '公司名称'")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="company_tel", columnDefinition = "varchar(20) comment '公司电话'")
	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	@Column(name="company_owner", columnDefinition = "varchar(100) comment '公司法人姓名'")
	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}
	@Column(name="business_licence", columnDefinition = "varchar(200) comment '营业执照'")
	public String getBusinessLicence() {
		return businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}
	@Column(name="licence_jy", columnDefinition = "varchar(200) comment '食品经营许可证'")
	public String getLicenceJy() {
		return licenceJy;
	}

	public void setLicenceJy(String licenceJy) {
		this.licenceJy = licenceJy;
	}
	@Column(name="licence_sc", columnDefinition = "varchar(200) comment '食品生产许可证'")
	public String getLicenceSc() {
		return licenceSc;
	}

	public void setLicenceSc(String licenceSc) {
		this.licenceSc = licenceSc;
	}
	@Column(name="status", columnDefinition = "varchar(2) comment '1正常 2关闭'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="identity", columnDefinition = "varchar(18) comment '身份证号'")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name="identity_front", columnDefinition = "varchar(200) comment '身份证正面'")
	public String getIdentityFront() {
		return identityFront;
	}

	public void setIdentityFront(String identityFront) {
		this.identityFront = identityFront;
	}
	@Column(name="identity_reverse", columnDefinition = "varchar(200) comment '身份证反面'")
	public String getIdentityReverse() {
		return identityReverse;
	}

	public void setIdentityReverse(String identityReverse) {
		this.identityReverse = identityReverse;
	}
	@Column(name="code", columnDefinition = "varchar(8) comment '用户code，唯一标识'")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
