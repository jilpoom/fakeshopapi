package org.palad.fakeshop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Log4j2
public class JWTGenerateTests {

    private String accessSecret = "1234567812345678123456781234567812345678124143";

    public final Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // ms 단위, 30분

    @Test
    public void createToken() throws Exception {
        String email = "user02@gmail.com";
        List<String> roles = List.of("ROLE_USER");
        Long id = 1L;

        Claims claims = Jwts.claims().setSubject(email); // jwt Claims 생성 (JWT 3 구조 중 paload에 들어가는 내용)
        // setter에 해당하는 내용은 이미 지정되어 있다. 변경하고 싶을 때 정의한다.

        // clasim -- sub -- email

        claims.put("roles", roles);
        claims.put("userId", id);

        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.ACCESS_TOKEN_EXPIRE_COUNT)) // 현재 시간으로 부터 만기 시간 설정
                .signWith(Keys.hmacShaKeyFor(accessSecret)) // 결과에 서명까지 포함시킨 JwtBuilder 리턴
                .compact();

        log.info(jwtToken);


    }



    @Test
    public void JwtParser() {
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMDJAZ21haWwuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJJZCI6MSwiaWF0IjoxNjc5NDk4Nzc4LCJleHAiOjE2Nzk1MDA1Nzh9.-RLqD0Bf3VMmCf4F3BXYiColKfbfBUzKsp1ny-3wskY";

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        log.info(claims.getSubject());
        log.info(claims.get("roles"));
        log.info(claims.get("userId"));
        log.info(claims.getIssuedAt());
        log.info(claims.getExpiration());

    }

    @Test
    public void validateToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRQakhlUzlYM2ZaV01wbkNPMXMubWplWXgvMUFoTUNOYk5sZFVPT3NkNnkzL1J2REpzRFV0UyIsInVzZXJuYW1lIjoidXNlcjEiLCJpYXQiOjE2Nzk1NzYyNDQsImV4cCI6MTY3OTU3Njg0NH0.LbzZsmdSSnA_wa-lBj66OF5dT500UyXvWCYqzqKAk2w";

//        byte[] secretKey = Base64.getUrlEncoder().encode(this.accessSecret.getBytes(StandardCharsets.UTF_8));



        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.accessSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        log.info(claims);
    }

}
