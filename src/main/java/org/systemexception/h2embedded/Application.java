package org.systemexception.h2embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leo
 * @date 11/10/15 15:43
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	/*
	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("restful-api").select().build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"SpringBoot H2 Embedded",
				"An example REST API with SpringBoot and H2(embedded)",
				null,
				null,
				new Contact("Leonardo Cappuccio", "https://github.com/lcappuccio/h2-embedded", null),
				"GPL v3",
				"https://raw.githubusercontent.com/lcappuccio/h2-embedded/master/LICENSE",
				Collections.emptyList());
	}
	 */
}
