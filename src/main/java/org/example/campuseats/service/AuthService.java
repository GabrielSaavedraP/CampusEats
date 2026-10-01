package org.example.campuseats.service;

import lombok.RequiredArgsConstructor;
import org.example.campuseats.exception.InvalidCredentialsException;
import org.example.campuseats.exception.UserAlreadyExistsException;
import org.example.campuseats.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository;
    private final PasswordEncoder;
    private final AuthenticationManager;
    private final JwtService jwtService;
    public RegisterResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new UserAlreadyExistsException("El username ya está registrado");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("El email ya está en uso");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User saved=userRepository.save(user);
        return RegisterResponse.builder()
                .id(saved.getId())
                .username(saved.getUsername())
                .email(saved.getEmail())
                .build()
    }
    public LoginResponse login(LoginRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getusername(), request.getPassword())

            );
        } catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Usuario o contraseña incorrectos");
        }
        String token = jwtService.generateToken(request.getUsername());
        return LoginResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpirationMs()/1000)
                .build();
    }
}
