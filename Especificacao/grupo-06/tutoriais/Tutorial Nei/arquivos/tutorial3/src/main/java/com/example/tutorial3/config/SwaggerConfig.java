package com.example.tutorial3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

        @Bean
        public Docket greetingApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Tutorial 3")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.tutorial3"))
                .build()
                .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                .title("Tutorial 3")
                .description("Spring Boot RESTFul APIs ")
                .version("1.0.0")
                .license("PROPRIETARY")
                .build();
        }

        @Override
        protected void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
}
