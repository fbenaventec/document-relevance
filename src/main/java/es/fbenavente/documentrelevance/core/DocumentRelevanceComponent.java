package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.domain.Document;
import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import es.fbenavente.documentrelevance.domain.DocumentRelevanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceComponent {
    private final DocumentReader documentReader;
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;
    private final TermFrequencyComponent termFrequencyComponent;
    private final InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    public DocumentRelevanceReport generate() {
        List<Document> documents = documentReader.readAll();
        List<String> terms = documentRelevanceConfiguration.getTerms();
        List<DocumentRelevance> documentsRelevance = new ArrayList<>();
        for (Document document: documents) {
            double ranking = 0d;
            for (String term: terms) {
                double inverseDocumentFrequency = inverseDocumentFrequencyComponent.calculate(documents, term);
                double termFrequency = termFrequencyComponent.calculate(document, term);
                ranking += termFrequency * inverseDocumentFrequency;
            }
            documentsRelevance.add(
                    DocumentRelevance.builder()
                            .name(document.getName())
                            .ranking(ranking)
                            .build()
            );
        }
        orderByRanking(documentsRelevance);
        return buildReport(documentsRelevance);
    }

    private void orderByRanking(List<DocumentRelevance> documentsRelevance) {
        documentsRelevance.sort(comparing(DocumentRelevance::getRanking).reversed());
    }

    private DocumentRelevanceReport buildReport(List<DocumentRelevance> documentsRelevance) {
        return DocumentRelevanceReport.builder().documents(documentsRelevance).build();
    }
}
