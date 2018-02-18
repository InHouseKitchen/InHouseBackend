package org.nexters.inhousekitchen.dao;

import org.nexters.inhousekitchen.dto.BookingDTO;

public interface BookingDAO {

	void booking(BookingDTO bookingDto);
	void bookingStatus(int userId, int status);
}
