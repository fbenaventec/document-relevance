package es.fbenavente.termfrequency.termfrequency.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties("term-frequency")
@Data
public class TermFrequencyConfiguration {
    private String directory;
    private Integer resultsToShow;
    private String [] terms;
    private Duration interval;
}
