package edu.pnu.controller;

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
    private final UserDetailsImpl userService;

    public AuthController(BusanUserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserDetailsImpl userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/api/login")
    public String login(@RequestBody BusanUser user) {
        BusanUser savedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자가 존재하지 않으면 예외 발생
        if (passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
    
    @PostMapping("/api/signup")
    public String signup(@RequestBody BusanUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Signup successful";
    }
}
