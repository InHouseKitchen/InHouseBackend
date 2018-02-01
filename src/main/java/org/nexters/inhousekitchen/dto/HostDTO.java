package org.nexters.inhousekitchen.dto;

public class HostDTO extends MemberDTO{
	
	private String phone;
	private String foodType;
	private String cookType;
	private String address;
	private String profile;
	
	public HostDTO(String id, String userName, String pwd, String email, String status) {
		super(id, userName, pwd, email, status);
	}
	
	public HostDTO(String id, String userName, String pwd, String email, String status, String phone, String foodType,
			String cookType, String address, String profile) {
		super(id, userName, pwd, email, status);
		this.phone = phone;
		this.foodType = foodType;
		this.cookType = cookType;
		this.address = address;
		this.profile = profile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getCookType() {
		return cookType;
	}
	public void setCookType(String cookType) {
		this.cookType = cookType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "HostDTO [phone=" + phone + ", foodType=" + foodType + ", cookType=" + cookType + ", address=" + address
				+ ", profile=" + profile + "]";
	}
	
	
	
	
}
