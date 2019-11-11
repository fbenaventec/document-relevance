package es.fbenavente.termfrequency.termfrequency.core;

import es.fbenavente.termfrequency.termfrequency.domain.TermFrequencyReport;
import org.springframework.stereotype.Component;

@Component
public class ReportGenerator {
    public TermFrequencyReport generate() {
        return new TermFrequencyReport();
    }
}
