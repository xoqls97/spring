package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.CommentVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int delete(int cno);

	int update(CommentVO cvo);




}
