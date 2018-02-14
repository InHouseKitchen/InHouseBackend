package org.nexters.inhousekitchen.dto;

import java.lang.Integer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="Member", subTypes={PreferDTO.class})
public class MemberDTO {
	private  Integer id;
	protected String userName;
	protected String email;
	protected String pwd;
	private String status;
	protected PreferDTO prefer;
	
	


	public MemberDTO(){ }

	public MemberDTO(Integer id, String userName, String pwd, String email, String status) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.pwd = pwd;
		this.status = status;
	}
	
	@ApiModelProperty(value = "사용자 식별자", hidden=true)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty(value = "아이디")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ApiModelProperty(value = "비밀번호")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	@ApiModelProperty(value = "상태(게스트모드/호스트모드)", hidden=true)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@ApiModelProperty(value="이메일주소")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ApiModelProperty(value="사용자 음식취향")
	public PreferDTO getPrefer() {
		return prefer;
	}

	public void setPrefer(PreferDTO prefer) {
		this.prefer = prefer;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", userName=" + userName + ", email=" + email + ", pwd=" + pwd + ", status="
				+ status + ", prefer=" + prefer + "]";
	}
	

}
