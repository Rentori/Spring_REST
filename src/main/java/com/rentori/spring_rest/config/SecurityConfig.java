package com.rentori.spring_rest.config;

import com.rentori.spring_rest.security.jwt.JwtConfigurer;
import com.rentori.spring_rest.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String MODERATOR_ENDPOINT = "/api/v1/users/**";
    private static final String USER_ENDPOINT = "/api/v1/users/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST, USER_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, MODERATOR_ENDPOINT).hasAuthority("MODERATOR")
                .antMatchers(HttpMethod.POST, MODERATOR_ENDPOINT).hasAuthority("MODERATOR")
                .antMatchers(HttpMethod.PUT, MODERATOR_ENDPOINT).hasAuthority("MODERATOR")
                .antMatchers(HttpMethod.DELETE, MODERATOR_ENDPOINT).hasAuthority("MODERATOR")
                .antMatchers(HttpMethod.GET, USER_ENDPOINT).hasAuthority("USER")
                .antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
