package com.microservice.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AcctSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Below method is to create admin user and its password.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("adminpassword").roles("ADMIN");
	}
	/**
	 * Below method is have rules on our REST api's.
	 * If url is /user/** then only ADMIN role should access them by providing above username and password.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
			.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN")
		.and()
		//CSRF - Cross Site Request Forgery
		.csrf().disable();
	}
}
