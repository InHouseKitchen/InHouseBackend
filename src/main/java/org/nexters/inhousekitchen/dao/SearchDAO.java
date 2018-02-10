package org.nexters.inhousekitchen.dao;

import java.util.List;

import org.nexters.inhousekitchen.dto.DiningDTO;
import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;

public interface SearchDAO {
	List<DiningDTO> selectByRandom() throws ServerErrorException;
	List<DiningDTO> selectByPrefer(PreferDTO prefer) throws ServerErrorException;
}
