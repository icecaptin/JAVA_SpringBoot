package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.entity.BusanUser;
import edu.pnu.repository.BusanUserRepository;
import edu.pnu.service.UserDetailsImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

	private final BusanUserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthController(BusanUserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			UserDetailsImpl userService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody BusanUser user) {
		BusanUser savedUser = userRepository.findById(user.getId()).orElse(null);
		if (savedUser == null) {
			return ResponseEntity.status(401).body("ID가 존재하지 않습니다.");
		}
		if (passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
			return ResponseEntity.ok(savedUser.getUsername());
		} else {
			return ResponseEntity.status(401).body("Password가 일치하지 않습니다.");
		}
	}

	@PostMapping("/api/signup")
	public ResponseEntity<String> signup(@RequestBody BusanUser user) {
		String id = user.getId();
		String username = user.getUsername();

		// ID 글자수 제한 검사
		if (id.length() < 5 || id.length() > 15) {
			return ResponseEntity.status(400).body("ID length must be between 5 and 15 characters");
		}
		if (!id.matches("^[a-zA-Z0-9]*$")) {
			return ResponseEntity.status(400).body("ID can only contain letters and numbers");
		}

		// Username 글자수 및 패턴 검사
		if (username.length() < 3 || username.length() > 10) {
			return ResponseEntity.status(400).body("Username length must be between 3 and 10 characters");
		}
		if (!username.matches("^[a-zA-Z가-힣]*$")) {
			return ResponseEntity.status(400).body("Username can only contain Korean and English letters");
		}

		if (userRepository.existsById(id)) {
			return ResponseEntity.status(409).body("ID already exists");
		}

		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		// 기본 역할 설정
		user.setUserrole("MEMBER");

		userRepository.save(user);
		return ResponseEntity.status(201).body("Signup successful");
	}

}
