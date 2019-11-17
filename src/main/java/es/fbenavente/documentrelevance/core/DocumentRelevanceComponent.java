package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceComponent {
    private final DocumentReader documentReader;
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;
    private final TermFrequencyComponent termFrequencyComponent;
    private final InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    public DocumentRelevanceReport generate() {
        List<Document> documents = documentReader.readAll();
        Map<String, Double> inverseDocumentFrequencyByTerm
                = inverseDocumentFrequencyComponent.calculate(documents, documentRelevanceConfiguration.getTerms());
        Map<String, Map<String, Double>> termFrequenciesByDocument
                = termFrequencyComponent.calculate(documents, documentRelevanceConfiguration.getTerms());
        List<DocumentRelevance> documentsRelevance = new ArrayList<>();
        for (Document document: documents) {
            double ranking = 0d;
            double inverseDocumentFrequency = inverseDocumentFrequencyByTerm.getOrDefault(document.getName(), 0d);
            Map<String, Double> termFrequenciesByTerm
                    = termFrequenciesByDocument.getOrDefault(document.getName(), new HashMap<>());
            for (String term: documentRelevanceConfiguration.getTerms()) {
                double termFrequency = termFrequenciesByTerm.getOrDefault(term, 0d);
                ranking += termFrequency + inverseDocumentFrequency;
            }
            documentsRelevance.add(
                    DocumentRelevance.builder()
                            .name(document.getName())
                            .ranking(BigDecimal.valueOf(ranking))
                            .build()
            );
        }
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }
}
