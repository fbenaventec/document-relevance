package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceComponent {
    private final TermFrequencyComponent termFrequencyComponent;
    private final InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    public List<DocumentRelevance> calculateRelevance(List<Document> documents, List<String> terms) {
        List<DocumentRelevance> documentsRelevance = new ArrayList<>();
        for (Document document: documents) {
            double ranking = 0d;
            for (String term: terms) {
                double inverseDocumentFrequency = inverseDocumentFrequencyComponent.calculate(documents, term);
                double termFrequency = termFrequencyComponent.calculate(document, term);
                ranking += termFrequency * inverseDocumentFrequency;
            }
            DocumentRelevance relevance = DocumentRelevance.builder()
                    .name(document.getName())
                    .ranking(ranking)
                    .build();
            documentsRelevance.add(relevance);
        }
        return documentsRelevance;
    }
}
