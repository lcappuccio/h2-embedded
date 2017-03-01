package org.systemexception.h2embedded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.systemexception.h2embedded.constants.Endpoints;

/**
 * @author leo
 * @date 27/02/16 11:31
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String ADMIN_USER = "admin", ADMIN_PASSWORD = "admin_pwd", ADMIN_ROLE = "ADMIN";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(ADMIN_USER).password(ADMIN_PASSWORD).roles(ADMIN_ROLE);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/**").permitAll();
		httpSecurity.authorizeRequests().antMatchers(Endpoints.H2_CONSOLE).hasRole(ADMIN_ROLE).and().httpBasic();
		httpSecurity.csrf().disable().headers().frameOptions().sameOrigin();
	}
}
