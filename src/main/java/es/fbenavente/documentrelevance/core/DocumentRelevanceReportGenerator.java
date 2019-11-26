package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import es.fbenavente.documentrelevance.viewer.ReportViewer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class DocumentRelevanceReportGenerator {
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;
    private final DocumentRelevanceReportComponent documentRelevanceReportComponent;
    private final DocumentReader documentReader;
    private final ReportViewer reportViewer;

    public void generate() {
        List<Document> documents = documentReader.readAll();
        List<String> terms = documentRelevanceConfiguration.getTerms();
        DocumentRelevanceReport report = documentRelevanceReportComponent.generate(documents, terms, documentRelevanceConfiguration.getResultsToShow());
        reportViewer.view(report);
    }
}

