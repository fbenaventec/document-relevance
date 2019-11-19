package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceReportComponent {
    private final DocumentRelevanceComponent documentRelevanceComponent;

    public DocumentRelevanceReport generate(List<Document> documents, List<String> terms, Integer top) {
        List<DocumentRelevance> documentsRelevance = documentRelevanceComponent.calculateRelevance(documents, terms);
        orderByRanking(documentsRelevance);
        if (top != null) {
            documentsRelevance = documentsRelevance.subList(0, min(documents.size(), top));
        }
        return buildReport(documentsRelevance);
    }

    private void orderByRanking(List<DocumentRelevance> documentsRelevance) {
        documentsRelevance.sort(comparing(DocumentRelevance::getRanking).reversed());
    }

    private DocumentRelevanceReport buildReport(List<DocumentRelevance> documentsRelevance) {
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }
}
