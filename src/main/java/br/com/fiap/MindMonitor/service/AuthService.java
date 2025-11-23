package br.com.fiap.MindMonitor.service;

import br.com.fiap.MindMonitor.config.JwtUtil;
import br.com.fiap.MindMonitor.entity.User;
import br.com.fiap.MindMonitor.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository userRepository, PasswordEncoder
            passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) throw
                new RuntimeException("Invalid credentials");
        return jwtUtil.generateToken(user.getUsername(),
                user.getRole().name());
    }
}
