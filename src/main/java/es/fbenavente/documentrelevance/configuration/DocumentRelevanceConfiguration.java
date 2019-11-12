package es.fbenavente.documentrelevance.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties("document-relevance")
@Data
public class DocumentRelevanceConfiguration {
    private String directory;
    private Integer resultsToShow;
    private String [] terms;
    private Duration interval;
}
