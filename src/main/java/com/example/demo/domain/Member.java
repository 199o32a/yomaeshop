package com.example.demo.domain;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.constant.Role;
import com.example.demo.vo.MemVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@DynamicInsert
@Table(name="member", schema="public")
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	@Column(name="m_id")
	private Long m_id ;
	
	private String loginid;
	private String pw;
	private String m_name;
	private int m_birth;
	private String m_address;
	private String m_phone;
	private String m_del_yn;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member CreateMem(MemVO memVo, BCryptPasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setM_id((long) 1234);
		member.setLoginid(memVo.getLoginid());
		member.setPw(passwordEncoder.encode(memVo.getPw()));
		member.setM_name(memVo.getM_name());
		member.setM_birth(memVo.getM_birth());
		member.setM_address(memVo.getM_address());
		member.setM_phone(memVo.getM_phone());
		member.setM_del_yn("N");
		member.setRole(Role.USER);
		return member;
		
	}
	
	
}

