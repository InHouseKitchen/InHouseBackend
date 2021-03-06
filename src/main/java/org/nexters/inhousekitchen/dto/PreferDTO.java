package org.nexters.inhousekitchen.dto;
import java.lang.Integer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="Prefer(사용자 음식취향)")
public class PreferDTO {
	private Integer memberId;
	private String favor1;
	private String favor2;
	private String favor3;
	private String favor4;
	
	public PreferDTO() { }
	
	public PreferDTO(Integer memberId, String favor1, String favor2, String favor3, String favor4) {
		this.memberId = memberId;
		this.favor1 = favor1;
		this.favor2 = favor2;
		this.favor3 = favor3;
		this.favor4 = favor4;
	}
	
	@ApiModelProperty(value = "사용자 식별자", hidden=true)
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@ApiModelProperty(value = "선호 음식종류(한,중,일)")
	public String getFavor1() {
		return favor1;
	}
	public void setFavor1(String favor1) {
		this.favor1 = favor1;
	}
	
	@ApiModelProperty(value = "선호 음식종류(탕,튀김,조림,찜,조링)")
	public String getFavor2() {
		return favor2;
	}
	public void setFavor2(String favor2) {
		this.favor2 = favor2;
	}
	
	@ApiModelProperty(value = "선호음식(직접입력)")
	public String getFavor3() {
		return favor3;
	}
	public void setFavor3(String favor3) {
		this.favor3 = favor3;
	}
	
	@ApiModelProperty(value = "선호음식(직접입력)")
	public String getFavor4() {
		return favor4;
	}
	public void setFavor4(String favor4) {
		this.favor4 = favor4;
	}

	@Override
	public String toString() {
		return "PreferenceDTO [memberId=" + memberId + ", favor1=" + favor1 + ", favor2=" + favor2 + ", favor3="
				+ favor3 + ", favor4=" + favor4 + "]";
	}
	
	
}
