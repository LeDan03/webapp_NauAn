package vn.edu.stu.WebBlogNauAn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.WebBlogNauAn.dto.LoginDto;
import vn.edu.stu.WebBlogNauAn.dto.RegisterDto;
import vn.edu.stu.WebBlogNauAn.response.LoginResponse;
import vn.edu.stu.WebBlogNauAn.service.AccountService;
import vn.edu.stu.WebBlogNauAn.service.RedisService;
import vn.edu.stu.WebBlogNauAn.utils.JWTUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final JWTUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountService accountService;
    private final RedisService redisService;

    @Autowired
    public AuthenticationController(JWTUtils jwtUtils, BCryptPasswordEncoder bCryptPasswordEncoder, AccountService accountService, RedisService redisService) {
        this.jwtUtils = jwtUtils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accountService = accountService;
        this.redisService = redisService;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto authRequest, HttpServletRequest request, HttpServletResponse response) {
        redisService.registerEmail(authRequest.getEmail());
        return accountService.emailLogin(authRequest.getEmail(), authRequest.getPassword(), request, response);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerRequest) {
        registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        return accountService.register(registerRequest);
    }
    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        String email = jwtUtils.extractEmail(refreshToken);
        if(email!=null && !jwtUtils.isTokenExpired(refreshToken)) {
            String newAccessToken = jwtUtils.generateAccessToken(email);
            return ResponseEntity.ok(new LoginResponse(newAccessToken));
        }
        return ResponseEntity.status(401).body("Invalid refresh token");
    }

}
