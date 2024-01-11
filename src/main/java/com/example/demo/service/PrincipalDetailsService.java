package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.PrincipalDetails;
import com.example.demo.dao.MemDAO;
import com.example.demo.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemDAO memDao;
	
	@Override
	public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException{
		List<Member> members = memDao.findByLoginid(loginid);
		Member member = members.stream().findFirst()
				.orElseThrow(() -> {
						return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		return new PrincipalDetails(member);
	}
}
