package vn.edu.stu.WebBlogNauAn;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@RequiredArgsConstructor
public class WebBlogNauAnApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBlogNauAnApplication.class, args);
	}

}
