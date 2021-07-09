package com.example.lms.security;

import static com.example.lms.util.SecurityConstant.PUBLIC_URLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.lms.service.UserInterface;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserInterface userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().frameOptions().disable().and().csrf().disable().cors().and().authorizeRequests()
				.antMatchers("/user/find").access("hasAuthority('user:read')").antMatchers("/user/save")
				.access("hasAuthority('user:update')").antMatchers("/user/delete").access("hasAuthority('user:delete')")
				.antMatchers("/book/save").access("hasAuthority('book:create')").antMatchers("/book/find")
				.access("hasAuthority('book:read')").antMatchers("/book/delete").access("hasAuthority('book:delete')")
				.antMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler(jwtAccessDeniedHandler).and()
				.addFilterBefore(jwtAuthenticationEntryPoint, UsernamePasswordAuthenticationFilter.class);
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
