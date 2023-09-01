package com.zeki.springboot2template.security;



import com.zeki.springboot2template.security.jwt.CustomAuthenticationEntryPoint;
import com.zeki.springboot2template.security.jwt.JwtAuthenticationFilter;
import com.zeki.springboot2template.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
     * {@link PasswordEncoder#encode(CharSequence)} 문자열을 인코딩 시킴.
     * {@link PasswordEncoder#matches(CharSequence, String)} 문자열과 인코딩된 데이터를 비교함.
     * Decoder는 기능상, 보안상 사용하지 않습니다.
     *
     * @return PasswordEncoderFactories 에서 PasswrodEncoder 생성
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Cors설정
     * AllowedOrigin에 와일드카드(*)를 사용하면 에러가 나는 경우가 있음.
     * 기본적으로 프론트의 도메인만 등록하는게 정상.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //TODO : localhost 제거
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:43000"));   // TODO : CORS 허용할 도메인 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 아래 형식의 URL로 들어오는 요청에 대해 인증을 요구
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                .csrf().disable() // csrf 보안 토큰 disable처리.
                .cors().configurationSource(corsConfigurationSource()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/login/**").permitAll()
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/api-docs/**").permitAll()

                .antMatchers("/cache/**").permitAll()
                .antMatchers("/token/**").permitAll()

                .antMatchers("/").permitAll()   // TODO : 적절히 조절

                .antMatchers("/company/**").authenticated()
                .antMatchers("/ship/**").authenticated()
                .antMatchers("/prdtempl/**").authenticated()
                .antMatchers("/product/**").authenticated()
                .antMatchers("/calendar/**").authenticated()
                .antMatchers("/rev/**").authenticated()

                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}