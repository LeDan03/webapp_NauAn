package vn.edu.stu.WebBlogNauAn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.WebBlogNauAn.dto.LoginDto;
import vn.edu.stu.WebBlogNauAn.dto.RegisterDto;
import vn.edu.stu.WebBlogNauAn.response.LoginResponse;
import vn.edu.stu.WebBlogNauAn.service.AccountService;
import vn.edu.stu.WebBlogNauAn.utils.JWTUtils;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final JWTUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountService accountService;
    @Autowired
    public AuthenticationController(JWTUtils jwtUtils, BCryptPasswordEncoder bCryptPasswordEncoder, AccountService accountService) {
        this.jwtUtils = jwtUtils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accountService = accountService;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        if("email".equals(email)&&"password".equals(password)) {
            String accessToken = jwtUtils.generateAccessToken(email);
            String refreshToken = jwtUtils.generateRefreshToken(email);
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerRequest) {
        registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        return accountService.register(registerRequest);
    }
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        String email = jwtUtils.extractEmail(refreshToken);
        if(email!=null && !jwtUtils.isTokenExpired(refreshToken)) {
            String newAccessToken = jwtUtils.generateAccessToken(email);
            return ResponseEntity.ok(new LoginResponse(newAccessToken, refreshToken));
        }
        return ResponseEntity.status(401).body("Invalid refresh token");
    }

}
