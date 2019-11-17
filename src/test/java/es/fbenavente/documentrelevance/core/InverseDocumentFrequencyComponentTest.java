package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@ExtendWith(MockitoExtension.class)
class InverseDocumentFrequencyComponentTest {

    private static final Random random = new Random();
    private static final String DOCUMENT_NAME1 = "name1";
    private static final String DOCUMENT_NAME2 = "name2";
    private static final String TERM1 = "term1";
    private static final String TERM2 = "term2";
    @InjectMocks
    private InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;

    @Test
    void when_term_appears_in_all_documents_result_is_zero() {
        List<Document> documents = Arrays.asList(
                Document.builder().name(DOCUMENT_NAME1).words(10).termFrequency(buildTermFrequencyFor(TERM1, TERM2)).build(),
                Document.builder().name(DOCUMENT_NAME2).words(5).termFrequency(buildTermFrequencyFor(TERM1, TERM2)).build()
        );
        List<String> terms = Arrays.asList(TERM1, TERM2);

        Map<String, Double> result = inverseDocumentFrequencyComponent.calculateForAllTerms(documents, terms);

        assertThat(result.keySet(), hasItems(TERM1, TERM2));
        assertThat(result.values(), contains(0d, 0d));
    }

    private Map<String, Integer> buildTermFrequencyFor(String ... terms) {
        if (terms == null) {
            return new HashMap<>();
        }
        Map<String, Integer> termFrequency = new HashMap<>();
        for (String term: terms) {
            termFrequency.put(term, random.nextInt(5) + 1);
        }
        return termFrequency;
    }
}