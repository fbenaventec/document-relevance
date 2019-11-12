package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class DocumentRelevanceComponent {
    private final TermFrequencyComponent termFrequencyComponent;
    private final InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    public DocumentRelevance getDocumentRelevance(File file) {
        return new DocumentRelevance();
    }
}
