package com.resser.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity

public class AppConfig{


	@Bean
	SecurityFilterChain sfc(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// no state of sessions or not session created
				.authorizeHttpRequests(Authorize ->
				// this used to configure request authorization rules
				Authorize.requestMatchers("api/**")
						// This specifies that requests matching the pattern "api/**" should be
						// considered for authorization.
						.authenticated()
						// This specifies that authenticated users are allowed to access the URLs
						// matched by the request matcher.
						.anyRequest().permitAll()
				// This allows any request (not just those matched by "api/**") to be permitted
				// without authentication.
				).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
				// This adds a custom filter (JwtTokenValidator) before a specific filter class
				// (BasicAuthenticationFilter) in the filter chain.
				// The JwtTokenValidator filter is responsible for validating JWT tokens.
				// JwtTokenValidator: This is a custom filter class that validates JWT tokens
				// extracted from requests.
				.csrf().disable()
				// This disables Cross-Site Request Forgery (CSRF) protection. CSRF protection
				// prevents
				// unauthorized commands from being transmitted from a user that the web
				// application trusts.

				.cors().configurationSource(CorsConfigurationSource())
				.and().httpBasic().and().formLogin().disable();

		return httpSecurity.build();
	}

	private CorsConfigurationSource CorsConfigurationSource() {
		// TODO Auto-generated method stub
		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
