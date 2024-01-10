package com.example.demo.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemVO {
	private Long m_id;
	
	@NotBlank(message = "아이디를 입력하세요")
	private String m_m_id;
	
	@NotBlank(message = "패스워드를 입력하세요")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String m_pw;
	private String m_name;
	private int m_birth;
	private String m_address;
	private String m_phone;
	private String m_del_yn;
	

}
