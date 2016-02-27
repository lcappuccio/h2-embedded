package org.systemexception.h2embedded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

	private final String ADMIN_ROLE = "ADMIN", USER_ROLE = "USER";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin_pwd").roles(ADMIN_ROLE);
		auth.inMemoryAuthentication().withUser("user").password("user_pwd").roles(USER_ROLE);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers(Endpoints.H2_CONSOLE).hasRole(ADMIN_ROLE).and().httpBasic();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole(ADMIN_ROLE).and().httpBasic();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasRole(ADMIN_ROLE).and().httpBasic();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasRole(ADMIN_ROLE).and().httpBasic();
		httpSecurity.csrf().disable().headers().frameOptions().sameOrigin();
	}
}
