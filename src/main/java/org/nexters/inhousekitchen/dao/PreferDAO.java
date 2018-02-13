package org.nexters.inhousekitchen.dao;

import org.nexters.inhousekitchen.dto.PreferDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;

public interface PreferDAO {
	 PreferDTO selectByMemberId(int memberId) throws ServerErrorException;
	 void insertNew(PreferDTO prefer) throws ServerErrorException;
}
