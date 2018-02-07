package org.nexters.inhousekitchen.dto;

import java.lang.Integer;
import java.lang.String;

import com.fasterxml.jackson.annotation.JsonInclude;

/*null인 데이터필드는 응답 값에서 제외되도록 하는 어노테이션*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiningImageDTO {

	private Integer id;
	private Integer diningId;
	private String imageUrl;
	
	public DiningImageDTO() {
		
	}
	
	public DiningImageDTO(Integer id, Integer diningId, String imageUrl) {
		super();
		this.id = id;
		this.diningId = diningId;
		this.imageUrl = imageUrl;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDiningId() {
		return diningId;
	}
	public void setDiningId(Integer diningId) {
		this.diningId = diningId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "DiningImageDTO [id=" + id + ", diningId=" + diningId + ", imageUrl=" + imageUrl + "]";
	}

	
	
}
