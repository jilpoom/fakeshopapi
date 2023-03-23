package org.palad.fakeshop.security.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public final Long ACCESS_TOKEN_EXPIRE_COUNT = 60 * 1000L; // ms 단위, 1분
    public final Long REFRESH_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30분

    public String createToken(Map<String, Object> valueMap, int minutes) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //PayLoad
        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);

        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_COUNT * minutes);

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Map<String, Object> validateToken(String token) throws JwtException {

        Map<String, Object> claim = null;

        claim = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        claim.get("username");

        log.info(claim);

        return claim;
    }

}
