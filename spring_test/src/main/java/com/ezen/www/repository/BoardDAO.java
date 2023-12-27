package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {


	List<BoardVO> selectList(PagingVO pgvo);

	BoardVO detail(int bno);

	int up(int bno);

	void update(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	int insert(BoardVO bvo);

	int selectBno();

}
