package org.nexters.inhousekitchen.service;

import java.util.List;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.ReviewDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;

public interface SearchService {
	List<DiningDTO> getHomeMenues(int memberId) throws ServerErrorException;
	List<DiningDTO> getDetailInfo(int hostId);
	List<ReviewDTO> getReview(int diningId);
}
