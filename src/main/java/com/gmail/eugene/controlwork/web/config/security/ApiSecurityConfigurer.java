package com.gmail.eugene.controlwork.web.config.security;

import com.gmail.eugene.controlwork.repository.model.enums.RoleEnum;
import com.gmail.eugene.controlwork.web.config.security.handler.ApiAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@Order(1)
public class ApiSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public ApiSecurityConfigurer(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AccessDeniedHandler apiAccessDeniedHandler() {
        return new ApiAccessDeniedHandler();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/api/v1/cards",
                        "/api/v1/cards/*"
                )
                .hasAuthority(RoleEnum.ADMINISTRATOR.name())
                .antMatchers("/api/v1/users/cards")
                .hasAuthority(RoleEnum.CUSTOMER.name())
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(apiAccessDeniedHandler())
                .and()
                .csrf()
                .disable();
    }
}