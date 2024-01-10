package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemDAO;
import com.example.demo.domain.Member;
import com.example.demo.vo.LoginVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemService {
	private final MemDAO memDao;

	
	public Member saveMem(Member member) {
		validateDubplicateMember(member);
		return memDao.saveAndFlush(member);
	}
	
	private void validateDubplicateMember(Member member) {
		Optional<Member> findMember = memDao.findById(member.getM_id());
		if(findMember.isPresent()) {
			throw new IllegalStateException("이미 가입하셨슈");
		}
	}
	
	public boolean ceackLoginIdDuplicate(String loginid) {
		return memDao.existsByLoginid(loginid);
	}
	
	public Member login(LoginVO logVo) {
		Optional<Member> optionalMem = memDao.findByLoginid(logVo.getLoginid());
		
		if(optionalMem.isEmpty()) {
			return null;
		}
		
		Member member = optionalMem.get();
		
		if(!member.getPw().equals(logVo.getPw())) {
			return null;
		}
		
		return member;
	}
	
	public Member getLoginUserByLoginId(String loginid) {
		if(loginid == null) return null;
		
		Optional<Member> optionalMem = memDao.findByLoginid(loginid);
		if(optionalMem.isEmpty()) return null;
		
		return optionalMem.get();
	}
}
