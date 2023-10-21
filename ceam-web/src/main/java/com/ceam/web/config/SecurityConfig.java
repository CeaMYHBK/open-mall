package com.ceam.web.config;

import com.ceam.web.components.JwtAccessDeniedHandler;
import com.ceam.web.components.JwtAuthenticationEntryPoint;
import com.ceam.web.components.jwt.TokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author CeaM
 * 2023/01/20 10:03
 **/
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final TokenFilter tokenFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * 白名单
     */
    private static final String[] whitePath = {"/auth/attention","/api/commercial",
            "/api/goods/categoryId",
            "/menu/build", "/api/home", "/api/auth/login", "/api/auth/code", "/api/category/channel",
            "/client/**", "/auth/logout", "/webjars/**", "/oauth/token", "/oauth/authorize"};

    private static final String[] staticResources = {"/favicon.ico", "/static/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 版本不同可能顺序不同Can't configure antMatchers after anyRequest
        return http.authorizeRequests()
                .antMatchers(whitePath).permitAll()
                .antMatchers(staticResources).permitAll()

                // 不拦截option请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 关闭服务器端渲染页面
                .and().csrf().disable()

                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 避免前后端Session不一致
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 关闭Spring Security 的Session
                .sessionFixation().changeSessionId().disable()

                .formLogin().loginPage("/login").permitAll()

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .build();
    }
}
