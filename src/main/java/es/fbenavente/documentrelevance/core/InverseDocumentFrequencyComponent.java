package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InverseDocumentFrequencyComponent {
    public Map<String, Double> calculate(List<Document> documents, List<String> terms) {
        return new HashMap<>();
    }
}
