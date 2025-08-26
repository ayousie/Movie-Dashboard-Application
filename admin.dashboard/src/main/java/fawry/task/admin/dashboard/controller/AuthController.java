package fawry.task.admin.dashboard.controller;

import fawry.task.admin.dashboard.dtos.AuthResponseDTO;
import fawry.task.admin.dashboard.dtos.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fawry.task.admin.dashboard.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Validated @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}