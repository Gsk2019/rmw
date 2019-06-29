package org.cs.rmw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cs.core.model.BaseEntity;

@Entity
@Table(name="app_feedback")
public class FeedBack extends BaseEntity {

	/**
	 * 意见反馈
	 */
	private static final long serialVersionUID = 6132411775437223903L;
	
	private Integer userId;
	private String userName;
	private String phone;
	private String content;
	private String imgs;
	private String result;//处理结果

	private Integer type;// 1现货帮我找 2供应帮我找

	private String status;//反馈状态 1：待处理 2：已处理


	@Column(name="type", columnDefinition="int(2) ")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="result",length=450)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="status",length=2)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="user_name",length=255)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="content", columnDefinition = "text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(length=450)
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
}
