package com.gestaopessoas.OnSafety.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.gestaopessoas.OnSafety"))              
	          .paths(PathSelectors.regex("/usuarios"))                         
	          .build()
	          .useDefaultResponseMessages(false)
	          .apiInfo(metaData());                                           
	    }


		  private ApiInfo metaData() {
		        return new ApiInfoBuilder()
		                .title("Gestão Pessoas")
		                .description("\"Documentação para gestão de pessoas onSafety \"")
		                .version("1.0.0")
		                .license("Apache 2.0")
		                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
		                .contact(new Contact("William Rolim", "williamrolimads@gmail.com", "Licença API"))
		                .build();
		    }
	}

