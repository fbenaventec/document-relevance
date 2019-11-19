package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class InverseDocumentFrequencyComponent {
    public double calculate(List<Document> documents, String term) {
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
