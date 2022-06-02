package it.engineering.controller;

import it.engineering.dto.JwtAuthResponse;
import it.engineering.dto.LoginDto;
import it.engineering.security.JwtTokenProvider;
import it.engineering.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userLogged = (UserPrincipal) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token, userLogged.getEmail(), userLogged.getAuthorities()));
    }
}
