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
    
    public AuthController(BusanUserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserDetailsImpl userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/login")
    public String login(@RequestBody BusanUser user) {
        BusanUser savedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
            return savedUser.getUsername();
        } else {
            return "Login failed";
        }
    }
    
    @PostMapping("/api/signup")
    public String signup(@RequestBody BusanUser user) {
        String id = user.getId();
        String username = user.getUsername();

        // ID 글자수 제한 검사
        if (id.length() < 5 || id.length() > 15) {
            return "ID length must be between 5 and 15 characters";
        }
        if (!id.matches("^[a-zA-Z0-9]*$")) {
            return "ID can only contain letters and numbers";
        }

        // Username 글자수 및 패턴 검사
        if (username.length() < 5 || username.length() >= 15) {
            return "Username length must be between 5 and 15 characters";
        }
        if (!username.matches("^[a-zA-Z가-힣]*$")) {
            return "Username can only contain Korean and English letters";
        }

        if (userRepository.existsById(id)) {
            return "ID already exists";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Signup successful";
    }

}
