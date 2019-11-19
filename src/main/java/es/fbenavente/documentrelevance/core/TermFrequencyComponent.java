package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;

@Component
public class TermFrequencyComponent {
    public double calculate(Document document, String term) {
        return document.getTermFrequency() != null && document.getTermFrequency().containsKey(term) ? 1d : 0d;
    }
}
