package es.fbenavente.termfrequency.termfrequency.core;

import es.fbenavente.termfrequency.termfrequency.viewer.ReportViewer;
import es.fbenavente.termfrequency.termfrequency.domain.TermFrequencyReport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ReportSchedule {
    private final ReportBuilder reportBuilder;
    private final ReportViewer reportViewer;

    @Scheduled(fixedRateString = "#{@termFrequencyConfiguration.getInterval().toMillis()}")
    public void generate() {
        TermFrequencyReport report = reportBuilder.build();
        reportViewer.view(report);
    }
}

