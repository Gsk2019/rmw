package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="other")
public class Other extends BaseEntity {

	/**
	 * 统计值
	 * code：
	 *  count1：累计发布量
	 *  count2：累计成交量
	 *  count3：累计成交额
	 */

	private String contents; //内容
	private String code; //代码
	private String type;//1统计2周边内容 3轮播图
	private Integer num;//统计值

	public Other(){

	}

	public Other(String code, String type){
		this.code = code;
		this.type = type;
	}

	@Column(name = "type", columnDefinition="varchar(2)" )
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "num", columnDefinition="int(11)" )
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "code", columnDefinition="varchar(10)" )
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "contents", columnDefinition="text" )
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
