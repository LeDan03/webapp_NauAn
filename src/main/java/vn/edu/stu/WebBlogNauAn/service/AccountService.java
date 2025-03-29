package vn.edu.stu.WebBlogNauAn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.stu.WebBlogNauAn.dto.RegisterDto;
import vn.edu.stu.WebBlogNauAn.mapper.AccountMapper;
import vn.edu.stu.WebBlogNauAn.model.Account;
import vn.edu.stu.WebBlogNauAn.repository.AccountRepo;
import vn.edu.stu.WebBlogNauAn.response.LoginResponse;
import vn.edu.stu.WebBlogNauAn.response.RegisterResponse;
import vn.edu.stu.WebBlogNauAn.utils.JWTUtils;

import jakarta.servlet.http.HttpServletRequest;  // Sửa từ javax sang jakarta
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepo accountRepo;
    private final JWTUtils jwtUtils;
    private final AccountMapper accountMapper;
    private final RedisService redisService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CookieService cookieService;

    @Autowired
    public AccountService(AccountRepo accountRepo, JWTUtils jwtUtils, AccountMapper accountMapper, RedisService redisService, BCryptPasswordEncoder bCryptPasswordEncoder, CookieService cookieService) {
        this.accountRepo = accountRepo;
        this.jwtUtils = jwtUtils;
        this.accountMapper = accountMapper;
        this.redisService = redisService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cookieService = cookieService;
    }

    public void saveAccount(RegisterDto registerDto) {
        Account account = accountMapper.toAccount(registerDto);
        account.setCreated_at(Timestamp.from(Instant.now()));
        accountRepo.save(account);

    }

    public ResponseEntity<RegisterResponse> register(RegisterDto registerDto) {
        saveAccount(registerDto);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(registerDto.getUsername());
        registerResponse.setEmail(registerDto.getEmail());
        registerResponse.setRegister_at(Timestamp.from(Instant.now()));
        return ResponseEntity.ok(registerResponse);
    }

    public ResponseEntity<?> emailLogin(String email, String password, HttpServletRequest request, HttpServletResponse response) {
        if(!redisService.isEmailRegistered(email)) {
            return ResponseEntity.status(400).build();
        }
        Optional<Account> account = accountRepo.findByEmail(email);
        if(account.isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        if(!bCryptPasswordEncoder.matches(password, account.get().getPassword())) {
            return ResponseEntity.status(400).build();
        }
        String refreshToken = cookieService.getItemFromCookies("refreshToken", request);
        if(refreshToken == null) {
            refreshToken = jwtUtils.generateRefreshToken(email);
            cookieService.addRefreshTokenToCookie(response, refreshToken);
        }else
        {
            if(jwtUtils.isTokenExpired(refreshToken)) {
                String newRefreshToken = jwtUtils.generateRefreshToken(email);
                cookieService.updateRefreshTokenToCookie(response, newRefreshToken);
            }
        }
        String accessToken = jwtUtils.generateAccessToken(email);
        LoginResponse loginResponse = new LoginResponse(accessToken);
        return ResponseEntity.ok(loginResponse);
    }
}
