package es.fbenavente.termfrequency.termfrequency;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class TermFrequencyApplication implements CommandLineRunner {
	private final TermFrequencyConfiguration termFrequencyConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(TermFrequencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Init term-frequency application|configuration={}", termFrequencyConfiguration);
		while (true) {
			log.info("Print report");
			Thread.sleep(termFrequencyConfiguration.getInterval().toMillis());
		}
	}
}
