package org.nexters.inhousekitchen.dto;
import java.sql.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.Integer;
import java.lang.String;
import java.lang.Double;

/*null인 데이터필드는 응답 값에서 제외되도록 하는 어노테이션*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiningDTO {
	private Integer id;
	private Integer hostId;
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private Double longitude;
	private Double latitude;
	private Integer price;
	private Integer guests;
	private String dIntro;
	private String mIntro;
	private List<DiningImageDTO> diningImages;
	
	public DiningDTO() {
		
	}
	public DiningDTO(Integer id, Integer hostId, Date startDate, Date endDate, 
			          String startTime, String endTime, Double longitude, Double latitude,
			          Integer price, Integer guests, String dIntro, String mIntro, List<DiningImageDTO> diningImages) {
		super();
		this.id = id;
		this.hostId = hostId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.price = price;
		this.guests = guests;
		this.dIntro = dIntro;
		this.mIntro = mIntro;
		this.diningImages = diningImages;

	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getHostId() {
		return hostId;
	}


	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Integer getGuests() {
		return guests;
	}


	public void setGuests(Integer guests) {
		this.guests = guests;
	}


	public String getdIntro() {
		return dIntro;
	}


	public void setdIntro(String dIntro) {
		this.dIntro = dIntro;
	}


	public String getmIntro() {
		return mIntro;
	}


	public void setmIntro(String mIntro) {
		this.mIntro = mIntro;
	}

	
	public Integer getPrice() {
		return price;
	}
	
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	public List<DiningImageDTO> getDiningImages() {
		return diningImages;
	}
	
	
	public void setDiningImages(List<DiningImageDTO> diningImages) {
		this.diningImages = diningImages;
	}
	
	
	@Override
	public String toString() {
		return "DiningDTO [id=" + id + ", hostId=" + hostId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", longitude=" + longitude + ", latitude="
				+ latitude + ", price=" + price + ", guests=" + guests + ", dIntro=" + dIntro + ", mIntro=" + mIntro
				+ ", diningImages=" + diningImages + "]";
	}
	
	
}
