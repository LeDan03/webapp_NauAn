    package vn.edu.stu.WebBlogNauAn.utils;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import java.util.Date;
    import org.slf4j.Logger;

    @Component
    public class JWTUtils {
        private static final Logger logger = (Logger) LoggerFactory.getLogger(JWTUtils.class);

        @Value("${security.jwt.secret-key}")
        private String secretKey;

        @Value("${security.jwt.expiration-time}")
        private long expirationTime;

        public JWTUtils() {
            logger.info("JWTUtils initialized with secretKey: {} and expirationTime: {}");
        }
        //Access token
        public String generateAccessToken(String email){
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        }

        //Refresh token
        public String generateRefreshToken(String email){
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 8))//2 thang
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        }
        //Xac thuc, lay thong tin tu token
        public Claims extractClaims(String token){
            return Jwts.parser() // Sử dụng parserBuilder thay vì parser
                    .setSigningKey(secretKey) // Thiết lập khóa ký
                    .build() // Tạo parse
                    .parseClaimsJws(token) // Phân tích token
                    .getBody();
        }
        //Lay email tu access token
        public String extractEmail(String token){
            return extractClaims(token).getSubject();
        }
        //Kiem tra token con song khong
        public boolean isTokenExpired(String token){
            return extractClaims(token).getExpiration().before(new Date());
        }
         //Xac thuc token
        public boolean validateToken(String token, String email){
            return (email.equals(extractEmail(token))) && !isTokenExpired(token); //So sanh email va email trong token, token chua het han
        }
    }
