package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
        Long documentsWhereTermAppears = documents.stream().filter(isTermPresent(term)).count();
        if (documentsWhereTermAppears == 0) {
            return 1d;
        }
        return Math.log10(documents.size() / documentsWhereTermAppears.doubleValue());
    }

    private Predicate<Document> isTermPresent(String term) {
        return document -> document.getTermFrequency() != null
                && document.getTermFrequency().getOrDefault(term, 0) > 0;
    }
}
