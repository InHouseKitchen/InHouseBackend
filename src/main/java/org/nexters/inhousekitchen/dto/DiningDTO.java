package org.nexters.inhousekitchen.dto;

public class DiningDTO {
	private String id;
	private String hostId;
	private String startTime;
	private String endTime;
	private double longitude;
	private double latitude;
	private int tprice;
	private int guests;
	private String dIntro;
	private String mIntro;
	public DiningDTO(String id, String hostId, String startTime, String endTime, double longitude, double latitude,
			int tprice, int guests, String dIntro, String mIntro) {
		super();
		this.id = id;
		this.hostId = hostId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.tprice = tprice;
		this.guests = guests;
		this.dIntro = dIntro;
		this.mIntro = mIntro;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
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
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getTprice() {
		return tprice;
	}
	public void setTprice(int tprice) {
		this.tprice = tprice;
	}
	public int getGuests() {
		return guests;
	}
	public void setGuests(int guests) {
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
	@Override
	public String toString() {
		return "DiningDTO [id=" + id + ", hostId=" + hostId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", tprice=" + tprice + ", guests=" + guests
				+ ", dIntro=" + dIntro + ", mIntro=" + mIntro + "]";
	}
	
	
	
}
