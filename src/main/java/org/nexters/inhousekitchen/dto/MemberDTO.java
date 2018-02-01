package org.nexters.inhousekitchen.dto;

public class MemberDTO {
	private String id;
	private String userName;
	private String pwd;
	private String email;
	private String status;
	
	public MemberDTO(String id, String userName, String pwd, String email, String status) {
		super();
		this.id = id;
		this.userName = userName;
		this.pwd = pwd;
		this.email = email;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", pwd=" + pwd + ", email=" + email + ", status=" + status
				+ "]";
	}
}
