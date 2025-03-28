package vn.edu.stu.WebBlogNauAn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World");
    }
}
