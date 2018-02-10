package org.nexters.inhousekitchen.dto;

public class ReviewDTO {
	private int id;
	private int userId;
	private int diningId;
	private int score;
	private String content;
	private String createDate;
	public ReviewDTO() {
		super();
	}
	public ReviewDTO(int id, int userId, int diningId, int score, String content, String createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.diningId = diningId;
		this.score = score;
		this.content = content;
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDiningId() {
		return diningId;
	}
	public void setDiningId(int diningId) {
		this.diningId = diningId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", userId=" + userId + ", diningId=" + diningId + ", score=" + score
				+ ", content=" + content + ", createDate=" + createDate + "]";
	}
	
	
}
