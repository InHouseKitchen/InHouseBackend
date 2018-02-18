package org.nexters.inhousekitchen.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.nexters.inhousekitchen.dto.BookingDTO;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDAOimpl implements BookingDAO {

	@Resource
	SqlSessionTemplate template;
	
	/*예약하기*/
	@Override
	public void booking(BookingDTO bookingDto) {
		template.insert("booking.booking", bookingDto);
	}

	/*예약 상태 변경 기능*/
	@Override
	public void bookingStatus(int userId, int status) {
		template.update("booking.end");
		if(status == 1){
			template.update("booking.accept", userId);
		}else if(status == 2){
			template.update("booking.deny", userId);
		}
	}
}
