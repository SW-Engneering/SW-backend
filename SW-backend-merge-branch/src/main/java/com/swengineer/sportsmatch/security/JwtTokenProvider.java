package com.swengineer.sportsmatch.security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "RMXJdjTr8t5LGeaBFFDMy1sP18DzqLU5"; // 환경 변수로 관리할 것
    private final long expirationTime = 3600000; // 1시간

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = loadUserByUsername(username); // UserDetailsService를 사용해 구현
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 실제로는 UserDetailsService를 주입해 UserDetails를 로드하도록 구현
    private UserDetails loadUserByUsername(String username) {
        return null; // 임시로 null 반환. 실제로는 UserDetailsService 구현체를 사용
    }
}

