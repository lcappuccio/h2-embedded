package org.systemexception.h2embedded;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.systemexception.h2embedded.interceptors.TestInterceptor;

/**
 * @author leo
 * @date 24/02/16 19:25
 */
@Configuration
@ComponentScan("org.systemexception.h2embedded.*")
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/api/data");
	}
}
