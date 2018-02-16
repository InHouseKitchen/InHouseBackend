package org.nexters.inhousekitchen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(value={ "email", "status", "prefer", "id" }, allowGetters=false)
@ApiModel(value="MemberLogin", parent=MemberDTO.class)
public class MemberLoginDTO extends MemberDTO{
	
	@ApiModelProperty(hidden=true)
	@Override
	public String getEmail() {
		return super.email;
	}
	
	@ApiModelProperty(hidden=true)
	@Override
	public PreferDTO getPrefer() {
		return super.prefer;
	}
}
