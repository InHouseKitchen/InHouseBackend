package org.nexters.inhousekitchen.dto;

import java.util.Date;

public class BookingDTO {
	private int id;
	private int userId;
	private int diningId;
	private int guestNumber;
	private String status;
	private Date reservationDate;
	public BookingDTO() {
		super();
	}
	public BookingDTO(int id, int userId, int diningId, int guestNumber, String status, Date reservationDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.diningId = diningId;
		this.guestNumber = guestNumber;
		this.status = status;
		this.reservationDate = reservationDate;
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
	public int getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	@Override
	public String toString() {
		return "BookingDTO [id=" + id + ", userId=" + userId + ", diningId=" + diningId + ", guestNumber=" + guestNumber
				+ ", status=" + status + "]";
	}
	
	
}
