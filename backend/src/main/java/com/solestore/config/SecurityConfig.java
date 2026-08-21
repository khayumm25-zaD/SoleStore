package com.solestore.config;

import com.solestore.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtFilter;
	public SecurityConfig(JwtAuthenticationFilter jwtFilter) { this.jwtFilter = jwtFilter; }
	@Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> {}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).exceptionHandling(errors -> errors
			.authenticationEntryPoint((request, response, exception) -> response.sendError(401, "Unauthorized"))
			.accessDeniedHandler((request, response, exception) -> response.sendError(403, "Forbidden"))).authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/auth/**", "/error").permitAll()
				.requestMatchers(org.springframework.http.HttpMethod.GET, "/api/products/**", "/api/categories/**").permitAll()
				.requestMatchers("/api/admin/**", "/api/roles/**").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.POST, "/api/products/**", "/api/categories/**").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/products/**", "/api/categories/**", "/api/variants/**").hasRole("ADMIN")
				.requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/products/**", "/api/categories/**", "/api/variants/**").hasRole("ADMIN")
				.requestMatchers("/api/profile").authenticated()
				.requestMatchers("/api/users/**").hasRole("ADMIN")
				.anyRequest().authenticated()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	@Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
	@Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception { return configuration.getAuthenticationManager(); }
}
