package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
@EnableScheduling
public class DocumentRelevanceApplication implements ApplicationRunner {
	private final DocumentRelevanceConfiguration documentRelevanceConfiguration;
	private final ArgumentsConfigurationLoader argumentsConfigurationLoader;

	public static void main(String[] args) {
		SpringApplication.run(DocumentRelevanceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		argumentsConfigurationLoader.loadFromArgs(args);
	}
}
