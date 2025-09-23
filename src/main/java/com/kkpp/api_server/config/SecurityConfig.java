package com.kkpp.api_server.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	var repo = CookieCsrfTokenRepository.withHttpOnlyFalse();
        // 헤더명 명시적으로 고정 (혼동 방지)
        repo.setHeaderName("X-XSRF-TOKEN");
    	
    	http
            // 세션 사용
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

            // 엔드포인트별 접근 제어
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/error", "/error/**", "/", "/auth/*", "swagger-ui/*", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("swagger-ui/*", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/css/**", "/js/**", "/login").permitAll()
                .anyRequest().authenticated()
            )

            // H2 console
            .headers(h -> h.frameOptions(f -> f.sameOrigin()))

            // CSRF (SPA에서 쿠키에 토큰 뿌려주기)
            .csrf(csrf -> csrf
                .csrfTokenRepository(repo)
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"),
                		new AntPathRequestMatcher("/v3/api-docs/**"),
                        new AntPathRequestMatcher("/swagger-ui/**"),
                        new AntPathRequestMatcher("/swagger-ui.html"))
            )

            // CORS (Next.js 개발 도메인 허용)
            .cors(Customizer.withDefaults())

            // 폼로그인/기본인증 제거
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of("http://localhost:3000", "https://your-nextjs-domain.com"));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        cfg.setAllowedHeaders(List.of("Content-Type", "X-XSRF-TOKEN", "Authorization"));
        cfg.setAllowCredentials(true); // 세션 쿠키 전달
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
    
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }



}
