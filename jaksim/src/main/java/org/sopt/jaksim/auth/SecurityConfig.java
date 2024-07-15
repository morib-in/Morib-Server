package org.sopt.jaksim.auth;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.auth.filter.CustomAccessDeniedHandler;
import org.sopt.jaksim.auth.filter.CustomJwtAuthenticationEntryPoint;
import org.sopt.jaksim.auth.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;

import java.util.Arrays;
import java.util.Collections;

import static org.sopt.jaksim.global.common.Constants.ACTIVATE_PROFILE_URL;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //web Security를 사용할 수 있게
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] AUTH_WHITE_LIST = {
            ACTIVATE_PROFILE_URL,
             "/api/v1/users/signin/**", "/api/v1/auth/**",
            "/api/v1/users/reissue/**", "/login/**",
            "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .requestCache(RequestCacheConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception ->
                {
                    exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                });

        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers(AUTH_WHITE_LIST).permitAll()
            .anyRequest().authenticated()
        )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
