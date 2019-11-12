package es.fbenavente.termfrequency.termfrequency;

import es.fbenavente.termfrequency.termfrequency.configuration.TermFrequencyConfiguration;
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
public class TermFrequencyApplication implements ApplicationRunner {
	private final TermFrequencyConfiguration termFrequencyConfiguration;
	private final ArgumentsConfigurationLoader argumentsConfigurationLoader;

	public static void main(String[] args) {
		SpringApplication.run(TermFrequencyApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		argumentsConfigurationLoader.loadFromArgs(args);
	}
}
