package vn.edu.stu.WebBlogNauAn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
}
