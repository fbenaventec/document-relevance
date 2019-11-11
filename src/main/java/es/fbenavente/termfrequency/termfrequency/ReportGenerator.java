package es.fbenavente.termfrequency.termfrequency;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ReportGenerator {
    private final TermFrequencyConfiguration termFrequencyConfiguration;

    @Scheduled(fixedRateString = "#{@termFrequencyConfiguration.getInterval().toMillis()}")
    public void generate() {
        log.info("Print report");
    }
}

