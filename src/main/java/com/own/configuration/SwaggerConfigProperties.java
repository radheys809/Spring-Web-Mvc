package com.own.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data

@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfigProperties {
    private boolean enabled;

    private boolean useDefaultResponseMessages;

    private boolean enableUrlTemplating;

    private boolean deepLinking;
    private boolean displayOperationId;
    private Integer defaultModelsExpandDepth;
    private Integer defaultModelExpandDepth;
    private boolean displayRequestDuration;
    private boolean filter;
    private Integer maxDisplayedTags;
    private boolean showExtensions;
    private String title;
    private String description;
    private String apiVersion;
}
