package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TermFrequencyComponent {

    public Map<String, Map<String, Double>> calculateForAllDocuments(List<Document> documents, List<String> terms) {
        if (CollectionUtils.isEmpty(documents) || CollectionUtils.isEmpty(terms)) {
            return new HashMap<>();
        }
        Map<String, Map<String, Double>> termsFrequencyByDocument = new HashMap<>();
        for(Document document: documents) {
            termsFrequencyByDocument.put(document.getName(), calculateForDocument(document, terms));
        }
        return termsFrequencyByDocument;
    }

    private Map<String, Double> calculateForDocument(Document document, List<String> terms) {
        Map<String, Double> termFrequency = new HashMap<>();
        for (String term: terms) {
            termFrequency.put(term, calculate(document, term));
        }
        return termFrequency;
    }

    private double calculate(Document document, String term) {
        return document.getTermFrequency() != null && document.getTermFrequency().containsKey(term) ? 1d : 0d;
    }
}
