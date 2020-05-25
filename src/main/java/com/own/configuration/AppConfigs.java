package com.own.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@Configuration
@EnableSwagger2
public class AppConfigs {

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	@Bean
    public Docket eDesignApi(SwaggerConfigProperties swaggerConfigProperties) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(swaggerConfigProperties)).
        		enable(swaggerConfigProperties.isEnabled()).
        		select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class).
                useDefaultResponseMessages(swaggerConfigProperties.isUseDefaultResponseMessages())
                .enableUrlTemplating(swaggerConfigProperties.isEnableUrlTemplating());
    }
	@Bean
	UiConfiguration uiConfig(SwaggerConfigProperties swaggerConfigProperties) {
		return UiConfigurationBuilder.builder().deepLinking(swaggerConfigProperties.isDeepLinking())
				.displayOperationId(swaggerConfigProperties.isDisplayOperationId())
				.defaultModelsExpandDepth(swaggerConfigProperties.getDefaultModelsExpandDepth())
				.defaultModelExpandDepth(swaggerConfigProperties.getDefaultModelExpandDepth())
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(swaggerConfigProperties.isDisplayRequestDuration())
				.docExpansion(DocExpansion.NONE).filter(Boolean.valueOf(swaggerConfigProperties.isFilter()))
				.maxDisplayedTags(swaggerConfigProperties.getMaxDisplayedTags())
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(swaggerConfigProperties.isShowExtensions())
				.tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}
	private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {

        return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle()).description(swaggerConfigProperties.getDescription())
                                   .version(swaggerConfigProperties.getApiVersion()).build();
	}
	
	
	  @Bean public ObjectMapper mapper() { return new ObjectMapper(); }
	 
	
}
