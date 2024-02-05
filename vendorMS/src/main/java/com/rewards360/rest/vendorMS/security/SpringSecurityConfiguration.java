package com.rewards360.rest.vendorMS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 1. All request should be authenticated
		http.authorizeHttpRequests((httpRequests) -> httpRequests.anyRequest().authenticated());

		// 2. If a request is not shown, a web page is shown
		http.httpBasic(withDefaults());

		// 3. disable CSRF -> POST, PUT
		http.csrf((csrf) -> csrf.disable());

		return http.build();
	}
}
