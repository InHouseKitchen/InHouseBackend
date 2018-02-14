package org.nexters.inhousekitchen.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(value={ "userName", "pwd", "status", "prefer", "id" }, allowGetters=false)
@ApiModel(value="MemberEmail", parent=MemberDTO.class)
public class MemberEmailDTO extends MemberDTO{	
	
	@ApiModelProperty(hidden=true)
	@Override
	public String getPwd() {
		return super.pwd;
	}
	
	@ApiModelProperty(hidden=true)
	@Override
	public String getUserName() {
		return super.userName;
	}
	
	@ApiModelProperty(hidden=true)
	@Override
	public PreferDTO getPrefer() {
		return super.prefer;
	}
}
