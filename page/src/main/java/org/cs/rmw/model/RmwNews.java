package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 新闻表
 * @ClassName: RmwNews
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Table(name = "rmw_news")
@Entity
public class RmwNews extends BaseEntity {

	private String title;		//新闻标题

	private String summary;	//摘要

	private String publisher;	//发布者

	private String keywords;	//关键字

	private String remark;		//备注

	private String type;       //新闻分类id																			1行业动态 2 价格行情 3独家视角

	private Integer LinkType;   //新闻链接类型 1正常图文新闻  2链接新闻（字段content保存第三方链接地址）

	private String mainImage;  //新闻主图

	private String content;	//内容

	private String state;  //1显示中 2不显示

	private Integer viewCount;//阅读量

	private Integer sort=1;

	@Column(name = "title", columnDefinition="VARCHAR(300) comment '新闻标题'" )
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "summary", columnDefinition="VARCHAR(300) comment '摘要'" )
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "remark", columnDefinition="VARCHAR(300) comment '备注'" )
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "publisher", columnDefinition="VARCHAR(50) comment '发布者'" )
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "type", columnDefinition="VARCHAR(2)" )
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "view_count", columnDefinition="INTEGER(11)" )
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name = "main_image", columnDefinition="VARCHAR(200) comment '新闻主图'" )
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Column(name = "content", columnDefinition="text" )
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

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

	@Column(name = "keywords")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "link_type")
	public Integer getLinkType() {
		return LinkType;
	}

	public void setLinkType(Integer linkType) {
		LinkType = linkType;
	}
}
