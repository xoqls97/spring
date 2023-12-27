package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;


	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list service impl check");
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardDTO getDetail( int bno) {
		log.info("detail service impl check");
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBvo(bdao.detail(bno));
		boardDTO.setFlist(fdao.getFileList(bno));
		return boardDTO;
	}

	@Override
	public void update(BoardVO bvo) {
		log.info("modify service impl check");
		bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove service impl check");
		return bdao.remove(bno);
	}


	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("paging service impl check");
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int register(BoardDTO bdto) {
		log.info("register service impl check");
		int isOk = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist()==null) {
			isOk *=1;
		}else {
			if(isOk>0 && bdto.getFlist().size()>0) {
				int bno = bdao.selectBno();
				log.info("bno>>>{}",bno);
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk*=fdao.insertFile(fvo);
				}
			}
		}
		
		return isOk;
	}
	

}
