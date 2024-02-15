package com.example.springsecurityauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/admin").hasRole("ADMIN");
			request.requestMatchers("/h2-console/**").permitAll();
			request.requestMatchers("/user").hasRole("USER");
			request.anyRequest().authenticated();

		}).formLogin(Customizer.withDefaults()).logout((logout) -> logout.permitAll());
				
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));// desactive le header securité cors pour le path vers la bdd H2
		return http.build();
	}

	/*
	 * @Bean//user en memoire public UserDetailsService users() { UserDetails user =
	 * User.builder().username("user").password(passwordEncoder().encode("user")).
	 * roles("USER") .build(); UserDetails admin =
	 * User.builder().username("admin").password(passwordEncoder().encode("admin"))
	 * .roles("USER", "ADMIN").build(); return new InMemoryUserDetailsManager(user,
	 * admin); }
	 */

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
