package vn.edu.stu.WebBlogNauAn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.stu.WebBlogNauAn.dto.RegisterDto;
import vn.edu.stu.WebBlogNauAn.mapper.AccountMapper;
import vn.edu.stu.WebBlogNauAn.model.Account;
import vn.edu.stu.WebBlogNauAn.repository.AccountRepo;
import vn.edu.stu.WebBlogNauAn.response.RegisterResponse;
import vn.edu.stu.WebBlogNauAn.utils.JWTUtils;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepo accountRepo;
    private final JWTUtils jwtUtils;
    private final AccountMapper accountMapper;
    @Autowired
    public AccountService(AccountRepo accountRepo, JWTUtils jwtUtils, AccountMapper accountMapper) {
        this.accountRepo = accountRepo;
        this.jwtUtils = jwtUtils;
        this.accountMapper = accountMapper;
    }
    @Transactional
    public boolean saveAccount(RegisterDto registerDto) {
        Account account = accountMapper.toAccount(registerDto);
        account.setCreated_at(Timestamp.from(Instant.now()));
        try{
            accountRepo.save(account);
            return true;
        }
        catch(Exception e){
            logger.error("Lỗi khi lưu account vào DB: " + e.getMessage(), e);
            throw new RuntimeException("Có lỗi xảy ra khi lưu tài khoản.");
        }
    }
    public ResponseEntity<RegisterResponse> register(RegisterDto registerDto) {
        boolean result = saveAccount(registerDto);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(registerDto.getUsername());
        registerResponse.setEmail(registerDto.getEmail());
        registerResponse.setRegister_at(Timestamp.from(Instant.now()));
        return result?ResponseEntity.ok(registerResponse):ResponseEntity.status(400).build();
    }
}
