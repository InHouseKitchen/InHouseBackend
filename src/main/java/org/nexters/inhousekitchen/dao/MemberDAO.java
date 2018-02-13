package org.nexters.inhousekitchen.dao;

import org.nexters.inhousekitchen.dto.MemberDTO;
import org.nexters.inhousekitchen.exception.ServerErrorException;

public interface MemberDAO {
	MemberDTO selectByUsername(String username) throws ServerErrorException;
	MemberDTO selectByEmail(String email) throws ServerErrorException;
	int insertUser(MemberDTO member) throws ServerErrorException;
}
