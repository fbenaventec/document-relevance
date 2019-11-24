package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.core.DocumentRelevanceReportGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
@EnableScheduling
public class DocumentRelevanceApplication implements ApplicationRunner {
	private final ArgumentsConfigurationLoader argumentsConfigurationLoader;
	private final TaskScheduler taskScheduler;
	private final DocumentRelevanceReportGenerator documentRelevanceReportGenerator;
	private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(DocumentRelevanceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		argumentsConfigurationLoader.loadFromArgs(args);
		taskScheduler.scheduleAtFixedRate(
				documentRelevanceReportGenerator::generate,
				documentRelevanceConfiguration.getInterval().toMillis()
		);
	}
}
