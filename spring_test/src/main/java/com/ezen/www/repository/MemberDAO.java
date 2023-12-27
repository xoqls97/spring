package com.ezen.www.repository;

import com.ezen.www.domain.MemberVO;

public interface MemberDAO {

	MemberVO getUser(String id);
    int insert(MemberVO mvo);
	void lastLogin(String id);
	int modify(MemberVO mvo);
	int remove(MemberVO mvo);
}
