package com.task_tracker.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.task_tracker.JWTConfig.AuthEntryPointJWT;
import com.task_tracker.JWTConfig.AuthTokenFilter;

import jakarta.servlet.FilterChain;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class ConfigClass {
	@Autowired
	private CustomUserDetailService userDetailsService;

	@Autowired
	private AuthEntryPointJWT authEntryPoint;
	@Autowired
	private AuthTokenFilter authTokenFilter;
	@Bean 
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	

	@Bean
	SecurityFilterChain httpRequestHandling(HttpSecurity http) throws Exception {
		
		http.cors().disable().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests().requestMatchers("/signup/**", "/login/**").permitAll()
		.anyRequest().authenticated();
	
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception {
		return authconfig.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
