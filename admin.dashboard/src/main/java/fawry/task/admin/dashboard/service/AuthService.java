package fawry.task.admin.dashboard.service;

import fawry.task.admin.dashboard.dtos.AuthResponseDTO;
import fawry.task.admin.dashboard.dtos.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fawry.task.admin.dashboard.repository.UserRepository;
import fawry.task.admin.dashboard.security.JwtTokenProvider;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public AuthResponseDTO login(LoginRequestDTO request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = jwtTokenProvider.generateToken(auth.getName());
        return new AuthResponseDTO(token);
    }
}
