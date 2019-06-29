package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 新闻分类表
 * @ClassName: RmwNews
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Table(name = "rmw_news_type")
@Entity
public class NewsType extends BaseEntity {

	private String name;	 //分类名称
	private String state;  //0停用 1启用
	private Integer sort=1; //排序值

	@Column(name = "state", columnDefinition="varchar(2)" )
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
