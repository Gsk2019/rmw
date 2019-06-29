package org.cs.rmw.model;

import org.cs.core.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 新闻评论表
 * @ClassName: NewsComment
 * @Description:  
 * @author: sunny_shi
 * @date: 2018-06-09 13:29:09
 */
@Table(name = "rmw_news_comment")
@Entity
public class NewsComment extends BaseEntity {

	private String content;	//内容

	private String state;  //1审核通过 2待审核 3审核失败

	private Integer userId;//阅读量

	private Integer newsId;//阅读量

	@Column(name = "content" )
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

	@Column(name = "user_id" )
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "news_id" )
	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
}
