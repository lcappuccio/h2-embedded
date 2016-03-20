package org.systemexception.h2embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author leo
 * @date 11/10/15 15:43
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("restful-api").select().build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"SpringBoot CRUD/H2",
				"An example REST API with SpringBoot and H2(embedded)",
				null,
				null,
				"leo@systemexception.org",
				"GPL v3",
				"https://github.com/lcappuccio/crud-application/blob/master/LICENSE"
		);
	}
}
