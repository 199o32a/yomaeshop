package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Member;


public interface MemDAO extends JpaRepository<Member, Long> {
	boolean existsByLoginid(String loginid);
	
	Optional<Member> findById(Long m_id);
	List<Member> findByLoginid(String loginid);
	
}
