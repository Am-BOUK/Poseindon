package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration class used to define the application authentication. It use the
 * BCryptPasswordEncoder class to encrypt the password. <br>
 * It extends the WebSecurityConfigurerAdapter abstract class.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public UserDetailsService userDetailsService;

	/**
	 * The 'configure' method with the 'AuthenticationManagerBuilder' argument
	 * handles authentication
	 * 
	 * Authentication goes through the Bean 'userDetailsService'
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * The 'configure' method with the 'HttpSecurity' argument handles permissions
	 * for incoming requests. This method allows either authorization by Token
	 * (GitHub, Google) or by Session (Spring Security)
	 * 
	 * All pages with URL '/User/**', '/secure/article-details' and '/admin/home'
	 * are only allowed to user with authority 'ADMIN'
	 * 
	 * All other requests must be authenticated
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/trade/**", "/ruleName/**")
					.authenticated().
				antMatchers("/user/**", "/secure/article-details", "/admin/home")
					.hasAuthority("ADMIN")
				.anyRequest()
					.authenticated()
				.and()
				.formLogin()
					.defaultSuccessUrl("/bidList/list", true)
				.and()
				.oauth2Login()
					.defaultSuccessUrl("/bidList/list", true)
				.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout")).logoutSuccessUrl("/login?logout")
				.and()
				.exceptionHandling()
					.accessDeniedPage("/403");

	}

	/**
	 * Define a passwordEncoder.
	 * 
	 * @return PasswordEncoder that uses the BCrypt
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
