package com.ezen.www.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	HttpServletRequest request;
	
	
	

	@Override
	public int signUp(MemberVO mvo) {
		//아이디가 중복되면 회원가입 실패
		// => 아이디만 주고 DB에서 일치하는 mvo 객체를 리턴
		//일치하는 유저가 있으면 가입 실패 없다면 성공
		MemberVO tempMvo = mdao.getUser(mvo.getId());
        //아이디가 중복되지 않는다면 회원가입 진행
		//password가 null이거나 값이 없다면 가입 불가
		if(tempMvo !=null) {
			return 0;
		}
		if(mvo.getId()==null || mvo.getId().length() ==0) {
			return 0;
		}
		if(mvo.getPw()==null || mvo.getPw().length()==0) {
			return 0;
		}
		String pw=mvo.getPw();
		String encodePw= passwordEncoder.encode(pw);
		mvo.setPw(encodePw);
		
		int isOk = mdao.insert(mvo);
		return isOk;
		
	         
		//회원가입 진행
		// password는 암호화 하여 가입
		
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//로그인 유저 확인
		// 아이디를 주고 해당 아이디의 객체를 리턴
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo == null) {
			return null;
		}
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())){
			return tempMvo;
		}
		return null;
	}

	@Override
	public void lastLogin(String id) {
		mdao.lastLogin(id);
	}

	@Override
	public int modify(MemberVO mvo) {
		  log.info("modify service impl check");
		  String pw = mvo.getPw();
		  if(mvo.getPw() ==null || mvo.getPw().length() ==0) {
			  MemberVO sesmvo = (MemberVO)request.getSession().getAttribute("ses");
			  mvo.setPw(sesmvo.getPw());
		  }else {
			  String encodepw = passwordEncoder.encode(pw);
			  mvo.setPw(encodepw);
		  }
		  return mdao.modify(mvo);
	}

	@Override
	public int remove(MemberVO mvo) {
		log.info("remove service impl check");
		
		return mdao.remove(mvo);
	}


}
