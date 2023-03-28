package org.palad.fakeshop.security.handler;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.security.utils.JwtTokenProvider;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private JwtTokenProvider jwtTokenProvider;

    public CustomAuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("Login Success Handler--------------------");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info(authentication);
        log.info(authentication.getName()); // username

        Map<String, Object> claim = new HashMap<>();

        String name = authentication.getName();

        log.info("Authentication name : " + name);
        claim.put("username", name);

        //Access Token 유효 기간 30분
        String accessToken = jwtTokenProvider.createToken(claim, 30);

        //Refresh Token 유효 기간 30분
        String refreshToken = jwtTokenProvider.createToken(claim, 30);

        Gson gson = new Gson();

        Map<String, String> keyMap = Map.of(
                "accessToken", accessToken, "refreshToken", refreshToken
        );

        String jsonStr = gson.toJson(keyMap);

        response.getWriter().println(jsonStr);


    }
}
