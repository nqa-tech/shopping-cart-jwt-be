package com.poly.backend.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
/**
 * Lớp SecurityConfig cung cấp cấu hình bảo mật cho ứng dụng.
 * Nó cấu hình các quy tắc bảo mật, các URL được bảo vệ và xác thực JWT token.
 */
public class SecurityConfig {

  final  UserAuthenticationEntryPoint userAuthenticationEntryPoint;
  final  CustomJwtFilter jwtAuthFilter;

    final  String[] PHONE_PROTECTED_URLS = {"/api/phones/**"};
    final String[] ORDER_PROTECTED_URLS = {"/api/orders/**"};

    /**
     * Phương thức cấu hình chuỗi lọc bảo mật
     *
     * @param http Đối tượng HttpSecurity
     * @return Đối tượng SecurityFilterChain
     * @throws Exception Ngoại lệ có thể xảy ra
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> {
                    /**
                     - requestMatchers...hasRole("ADMIN"): tức là các phương thức HTTP
                     này cần có quyền ADMIN để truy cập
                     -  PHONE_PROTECTED_URLS: Danh sách các endpoint được bảo vệ yêu cầu quyền ADMIN để truy cập
                     */
                    auth.requestMatchers(HttpMethod.DELETE, PHONE_PROTECTED_URLS).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, PHONE_PROTECTED_URLS).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, PHONE_PROTECTED_URLS).hasRole("ADMIN")
                            /**
                             - requestMatchers("/api/orders/**").permitAll(): các phương thức với endpoint này đều có thể truy cập mà không cần xác thực
                             */
                            .requestMatchers(HttpMethod.POST, ORDER_PROTECTED_URLS).permitAll()
                            .requestMatchers(HttpMethod.GET, ORDER_PROTECTED_URLS).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, ORDER_PROTECTED_URLS).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, ORDER_PROTECTED_URLS).hasRole("ADMIN")
                            /**
                             - requestMatchers("/**").permitAll(): các phương thức với endpoint khác
                             được bắt đầu bằng dạng "/**" đều được truy cập
                             */
                            .requestMatchers("/**").permitAll()
                            /**
                             * - anyRequest().authenticated(): tất cả các request khác cần phải được xác thực
                             */
                            .anyRequest().authenticated();
                })
        ;
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
