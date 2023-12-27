package com.ezen.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Inject
	private BoardService bsv;
	
	@Inject
	private FileHandler fhd;
	
	//경로와 리턴의 값이 같을 경우 생략가능
	
	// /board/register => /board/register
	
	@GetMapping("/register")
	public void register() { }
	
	//@RequestParam("name")String name : 파라미터 받을 때
	
	// public String register(@RequestParam("name")String name, BoardVO bvo): 파라미터받을 때 사용
	// required : 필수여부 (값이 false일 경우 파라미터가 없어도 NullPointerException이 발생하지 않음.
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files",required=false) MultipartFile[] files) {
		log.info("bvo>>>{}", bvo);
		log.info(">>> files>>>{}", files.toString());
		//파일 핸들러 처리
		List<FileVO> flist = null;
		//파일이 있을 경우만 fhd를 호추
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
			log.info(">>> flist>>{}", flist);
		}else {
			log.info("file null");
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
		
		int isOk = bsv.register(bdto);
		// int isOK = bsv.register(bvo);
		//목적지 경로
		return "redirect:/board/list";
		
		
	}
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		log.info(">>> pgvo >>{}"+ pgvo);
		//리턴타입은 목적지 경로에 대한 타입 (destPage가 리턴임)
		//Model 객체 =>setAttribute 역할을 하는 객체
		m.addAttribute("list",bsv.getList(pgvo));
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		log.info("ph"+ph);
		m.addAttribute("ph",ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})  
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info(">>>>> bno > "+bno);
		m.addAttribute("boardDTO",bsv.getDetail(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, Model m) {
		
		log.info(">>>bvo>>>{}", bvo);
		//update
		
		bsv.update(bvo);
		m.addAttribute("bno",bvo.getBno());
		return "redirect:/board/detail"; //bno
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info(">>> bno >> {}",bno);
		int isOk= bsv.remove(bno);
		//페이지가 새로고침 될 때 남아있을 필요가 없는데이터
		//리다이렉트 될 때 데이터를 보내는 객체(RedirectAttribute)
		//한번만 일회성으로 데이터 보낼때 사용
		re.addFlashAttribute("isDel", isOk);
		
		return "redirect:/board/list";
		
	}

	
	
	
}
