package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * author: Dhiraj Srivastava (dhiraj.s@hcl.com)
 */
@Configuration
public class SecurityConfig {
	@Autowired
	private JWTAuthenticationEntryPoint point;
	@Autowired
	private JWTAuthenticationFilter filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	/*	http.csrf(csrf -> csrf.disable())
				.cors(cors->cors.disable())
				.authorizeHttpRequests(auth ->
						auth.requestMatchers("/home/**").authenticated()
								.requestMatchers("/login").permitAll()
								.requestMatchers("/authorization-code/callback").permitAll())
//								.anyRequest().authenticated())
				//.formLogin(form-> form.loginPage("/login"))
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));*/

		http.
				authorizeHttpRequests(auth -> auth.requestMatchers("/home/**").authenticated()
						.requestMatchers("/authorization-code/callback").permitAll())
						.formLogin(form -> form.loginPage("/home/login").permitAll())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(ex->ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}