package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_app_sms")
public class SMS extends BaseEntity {

	/**
	 * 短信验证码存储
	 */
	private static final long serialVersionUID = -8505788510018561676L;

	private String phone;
	private int type;//1,注册获取验证码,2,忘记密码获取验证码
	private int validTime;
	private String code;
	
	@Column(length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(columnDefinition="TINYINT(2)")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name = "valid_time",length=6)
	public int getValidTime() {
		return validTime;
	}
	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}
	@Column(length=6)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
