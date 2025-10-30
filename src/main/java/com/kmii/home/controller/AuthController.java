package com.kmii.home.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kmii.home.entity.User;
import com.kmii.home.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	//회원 가입
		@PostMapping("/signup")
		public String signup(@RequestParam("username") String username, @RequestParam("password") String password) {
			
			User user = userService.signupUser(username, password);
			return "회원가입 완료: " + user.getUsername();
			
		}
	
	
	//로그인 후에 인증 받은 user만 접근할 수 있는 request → 로그인 체크용 요청
	@GetMapping("/apicheck")
	public String apicheck() {
		System.out.println("로그인 확인");
		return "로그인 성공 확인!";
	}
	
	
	//현재 로그인한 사용자 정보 확인
	@GetMapping("/me")
	public ResponseEntity<?> me(Authentication auth){
		
		if(auth == null) {
			return ResponseEntity.status(401).body(Map.of("error","로그인이 필요합니다."));
		}
		return ResponseEntity.ok(Map.of("username",auth.getName()));
		
	}
	
	
	

}
