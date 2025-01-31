package org.koreait.planitkorea.config;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.filter.JwtAuthenticationFilter;
import org.koreait.planitkorea.handler.OAuth2SuccessHandler;
import org.koreait.planitkorea.service.implement.OAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Lazy
    @Autowired

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2UserServiceImpl oAuth2Service;

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/v1/auth/**"),
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
                                new AntPathRequestMatcher("/api/v1/users/find-id"),
                                new AntPathRequestMatcher("/api/v1/users/mails/**"),
                                new AntPathRequestMatcher("/api/v1/boards/**"),
                                new AntPathRequestMatcher("/file/**"),
                                new AntPathRequestMatcher("/api/v1/products/**"),
                                new AntPathRequestMatcher("/api/v1/reviews/{productId}"),
                                new AntPathRequestMatcher("/oauth2/callback/*"),
                                new AntPathRequestMatcher("/api/v1/reviews/auth/**")
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                                new AntPathRequestMatcher("/api/v1/boards/**")
=======
                                new AntPathRequestMatcher("/image/**")
>>>>>>> f08a91c (pyj)
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
>>>>>>> 65e6b84 (fix: reservation 로직 수정):PlanItKorea/src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
=======
=======
                                new AntPathRequestMatcher("/api/v1/boards/**"),
                                new AntPathRequestMatcher("/image/**"),
                                new AntPathRequestMatcher("/api/v1/products/**")
>>>>>>> 3c1f589 (20250131 shy)
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
>>>>>>> abf6421 (refactor: 대소문자변경):PlanItKorea/src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
=======
=======
                                new AntPathRequestMatcher("/api/v1/boards/**"),
                                new AntPathRequestMatcher("/image/**"),
                                new AntPathRequestMatcher("/oauth2/callback/*")
<<<<<<< HEAD
>>>>>>> bfdd46a (20250126 OAuth2)
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
>>>>>>> 6ee97db (feat: OAuth2):PlanItKorea/src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
=======
=======

>>>>>>> 1d9962e (20250131 pyj password)
>>>>>>> be57e07 (feat: 비밀번호 찾기, 이메일 전송 로직 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/config/WebSecurityConfig.java
                        )
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                        .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service))
                        .successHandler(oAuth2SuccessHandler)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptpasswordEncoder) throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setPasswordEncoder(bCryptpasswordEncoder);

        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
