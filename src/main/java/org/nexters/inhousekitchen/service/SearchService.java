package org.nexters.inhousekitchen.service;

import java.util.List;
import java.lang.Integer;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;

public interface SearchService {
	List<DiningDTO> getHomeMenues(int memberId) throws ServerErrorException;
}
