package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InverseDocumentFrequencyComponent {
    public Map<String, Double> calculateForAllTerms(List<Document> documents, List<String> terms) {
        if (CollectionUtils.isEmpty(terms) || CollectionUtils.isEmpty(documents)) {
            return new HashMap<>();
        }
        Map<String, Double> termFrequency = new HashMap<>();
        for (String term: terms) {
            termFrequency.put(term, calculate(documents, term));
        }
        return termFrequency;
    }

    private Double calculate(List<Document> documents, String term) {
        return 0d;
    }
}
