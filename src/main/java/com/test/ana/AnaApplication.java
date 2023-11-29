package com.test.ana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class AnaApplication implements WebMvcConfigurer {
	public static void main(String[] args)  {
		SpringApplication.run(AnaApplication.class,args);
	}
	@Bean
	public WebMvcConfigurer configurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/erp/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET","POST","DELETE","PUT")
						.allowedHeaders("*");
			}
		};
	}
}
