package es.fbenavente.termfrequency.termfrequency;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
@EnableScheduling
public class TermFrequencyApplication {
	private final TermFrequencyConfiguration termFrequencyConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(TermFrequencyApplication.class, args);
	}
}
