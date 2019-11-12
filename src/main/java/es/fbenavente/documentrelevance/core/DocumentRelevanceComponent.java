package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceComponent {
    private final TermFrequencyComponent termFrequencyComponent;
    private final InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    public DocumentRelevance getDocumentRelevance(File file) {
        double termFrequency = termFrequencyComponent.getTermFrequency(file);
        double inverseDocumentFrequency = inverseDocumentFrequencyComponent.getInverseDocumentFrequency(file);
        double ranking = termFrequency * inverseDocumentFrequency;
        return DocumentRelevance.builder()
                .name(file.getName())
                .termFrequency(BigDecimal.valueOf(termFrequency))
                .inverseDocumentFrequency(BigDecimal.valueOf(inverseDocumentFrequency))
                .ranking(BigDecimal.valueOf(ranking))
                .build();
    }
}
