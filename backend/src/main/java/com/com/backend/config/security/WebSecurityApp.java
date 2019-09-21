package com.com.backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityApp extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    public WebSecurityApp(@Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter(){
        return new JwtAuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.cors()
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                    .hasRole("ADMIN")
                .antMatchers("/api/auth/**", "/actuator/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
