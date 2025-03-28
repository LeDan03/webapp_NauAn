    package vn.edu.stu.WebBlogNauAn.filter;

    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;
    import vn.edu.stu.WebBlogNauAn.utils.JWTUtils;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.io.IOException;

    @Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

        @Autowired
        private JWTUtils jwtUtils;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            String requestURI = request.getRequestURI();
            String token = request.getHeader("Authorization");
            if (requestURI.equals("/api/auth/login") || requestURI.equals("/api/auth/register")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);

                String email = jwtUtils.extractEmail(token);
                if (email != null && jwtUtils.validateToken(token, email)) {
                    filterChain.doFilter(request, response);
                }
                else{
                    response.setStatus(403);
                }
            }else{
                response.setStatus(403);
            }
        }
    }
