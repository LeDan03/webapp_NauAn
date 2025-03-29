package vn.edu.stu.WebBlogNauAn.service;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;


import jakarta.servlet.http.HttpServletRequest;  // Sửa từ javax sang jakarta
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {

    public String getItemFromCookies(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(90*24*60*60);//3 thang, 90 ngay
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        String cookieHeader = "refreshToken=" + refreshToken + "; Max-Age=" + (90 * 24 * 60 * 60) +
                "; HttpOnly; Secure; SameSite=Strict;";
        response.addHeader("Set-Cookie", cookieHeader);
    }
    public HttpServletResponse updateRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
        //Xoa refresh token cu
        Cookie oldCookie = new Cookie("refreshToken", null);
        oldCookie.setMaxAge(0);
        oldCookie.setPath("/");
        response.addCookie(oldCookie);

        //Them refresh token moi
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(90*24*60*60);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return response;
    }
}
