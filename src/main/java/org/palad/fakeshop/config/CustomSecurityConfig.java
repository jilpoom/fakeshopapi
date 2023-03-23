package org.palad.fakeshop.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.security.handler.CustomAuthenticationSuccessHandler;
import org.palad.fakeshop.security.filter.APILoginFilter;
import org.palad.fakeshop.security.filter.TokenCheckFilter;
import org.palad.fakeshop.security.CustomUserDetailsService;
import org.palad.fakeshop.security.utils.JwtTokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        log.info("webSecurityCustomizer --------------------------------");

        // 정적 파일(css, js 파일 등의 접근에 대한 시큐리티 적용 해지)
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        log.info("--------------Security Config-----------------");


//        http.authorizeRequests(authorize -> authorize
//                .antMatchers("/auth/login" ).permitAll()
//                .anyRequest().authenticated());

        // AuthenticationManger 설정
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());

        // Get Authentication Manager
        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.build();

        // 반드시 필요
        http.authenticationManager(authenticationManager);

        // API Login Filter
        APILoginFilter apiLoginFilter = new APILoginFilter("/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager);

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler =
                new CustomAuthenticationSuccessHandler(jwtTokenProvider);

        apiLoginFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);

        // APILoginFilter 위치 조정
        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenCheckFilter(jwtTokenProvider, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable(); // csrf 토큰 비활성화
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 사용 안함

        return http.build();

    }

    private TokenCheckFilter tokenCheckFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        return new TokenCheckFilter(jwtTokenProvider, customUserDetailsService);
    }


}
