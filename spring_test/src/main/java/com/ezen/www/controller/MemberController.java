package com.ezen.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">>> mvo>> {}",mvo);
		int isOk = msv.signUp(mvo);
		log.info(">>>> signUp ?"+(isOk>0? "ok" : "fail"));
		return "index";
		
	}
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpServletRequest request,Model m) {
		log.info(">>>mvo>>{}",mvo);
		
		//mvo 객체가 db와 일치하는지 체크
		MemberVO loginMvo = msv.isUser(mvo);
		if(loginMvo != null) {
			//로그인 성공시
			HttpSession ses= request.getSession();
			ses.setAttribute("ses", loginMvo); // session에 로그인 객체 저장
			ses.setMaxInactiveInterval(60*10); // 로그인 유지 시간
		}else {
			//로그인 실패시
			m.addAttribute("msg_login",1);
		}
		return "index";
		
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		//세션 객체 삭제
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLogin(mvo.getId());
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_logout",1);
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpServletRequest request, Model m) {
	    log.info(">>> mvo >> {}", mvo);
	    int isOk = msv.modify(mvo);

	    if (isOk > 0) {
	    	request.getSession().removeAttribute("ses");
	    	request.getSession().invalidate();
	        m.addAttribute("msg_modify", 1);
	    } else {
	        m.addAttribute("msg_modify", 0);
	    }

	    return "index";
	}
	
	@GetMapping("/remove")
	public String remove(MemberVO mvo, HttpServletRequest request, Model m) {
		log.info(">>>mvo>>{}",mvo);
		int isOk= msv.remove(mvo);
		if(isOk>0) {
			request.getSession().removeAttribute("ses");
	    	request.getSession().invalidate();
	    	m.addAttribute("msg_remove",1);
		} else {
			m.addAttribute("msg_remove",0);
		}
		
		return "index";
	}
	 

}
