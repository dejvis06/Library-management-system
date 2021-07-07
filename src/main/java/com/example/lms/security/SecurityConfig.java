package com.example.lms.security;

import static com.example.lms.util.SecurityConstant.PUBLIC_URLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.lms.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().frameOptions().disable().and().csrf().disable().cors().and().authorizeRequests()
				.antMatchers("/user/find").access("hasAuthority('user:read')").antMatchers(PUBLIC_URLS).permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.getbCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
