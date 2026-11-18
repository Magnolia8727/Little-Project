package com.example.MyLittleProject.controller;

import com.example.MyLittleProject.dto.request.LoginRequest;
import com.example.MyLittleProject.dto.request.RegisterRequest;
import com.example.MyLittleProject.dto.response.AuthResponse;
import com.example.MyLittleProject.exception.DuplicateResourceException;
import com.example.MyLittleProject.exception.ResourceNotFoundException;
import com.example.MyLittleProject.models.AppUser;
import com.example.MyLittleProject.models.Student;
import com.example.MyLittleProject.repository.AppUserRepository;
import com.example.MyLittleProject.repository.StudentRepository;
import com.example.MyLittleProject.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already taken");
        }
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        if(request.getStudentNumber() != null)
        {
            Student student = studentRepository.findById(request.getStudentNumber()).orElseThrow(() -> new ResourceNotFoundException("Student number not found"));
            user.setStudent(student);
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token,user.getUsername(),user.getRole().name());
    }
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        AppUser user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Username not found"));
        String token = jwtService.generateToken(user);
        return new AuthResponse(token,user.getUsername(),user.getRole().name());
    }
}
