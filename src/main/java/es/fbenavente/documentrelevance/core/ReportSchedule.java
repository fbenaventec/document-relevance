package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import es.fbenavente.documentrelevance.viewer.ReportViewer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ReportSchedule {
    private final ReportGenerator reportGenerator;
    private final ReportViewer reportViewer;

    @Scheduled(fixedRateString = "#{@documentRelevanceConfiguration.getInterval().toMillis()}")
    public void generate() {
        DocumentRelevanceReport report = reportGenerator.generate();
        reportViewer.view(report);
    }
}

