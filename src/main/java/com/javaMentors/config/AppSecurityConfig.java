package com.javaMentors.config;

import com.javaMentors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public AppSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/login.jsp")
				.loginProcessingUrl("/login")
				.usernameParameter("app_username")
				.passwordParameter("app_password")
				.successHandler(authenticationSuccessHandler)
				.permitAll()
				.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login.jsp")
				.and().exceptionHandling()
				.accessDeniedPage("/user/error");
	}
}
