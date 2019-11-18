package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.hamcrest.number.IsCloseTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.number.IsCloseTo.closeTo;

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
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 3);
                                           put(TERM2, 1);
                                       }}
                        ).build(),
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 3);
                                           put(TERM2, 4);
                                       }}
                        ).build()
        );
        List<String> terms = Arrays.asList(TERM1, TERM2);

        Map<String, Double> result = inverseDocumentFrequencyComponent.calculateForAllTerms(documents, terms);

        assertThat(result.keySet(), hasItems(TERM1, TERM2));
        assertThat(result.values(), containsInAnyOrder(0d, 0d));
    }

    @Test
    void when_term_does_not_appears_in_the_corpus_the_result_is_one() {
        List<Document> documents = Arrays.asList(
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<>()).build(),
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<>()).build()
        );
        List<String> terms = Arrays.asList(TERM1, TERM2);

        Map<String, Double> result = inverseDocumentFrequencyComponent.calculateForAllTerms(documents, terms);

        assertThat(result.keySet(), hasItems(TERM1, TERM2));
        assertThat(result.values(), containsInAnyOrder(1d, 1d));
    }

    @Test
    void when_term_appears_in_part_of_the_corpus_the_result_is_the_logarithm_of_divide_number_of_documents_by_documents_with_term() {
        List<Document> documents = Arrays.asList(
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 0);
                                           put(TERM2, 1);
                                       }}
                        ).build(),
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 1);
                                           put(TERM2, 0);
                                       }}
                        ).build()
        );
        List<String> terms = Arrays.asList(TERM1, TERM2);

        Map<String, Double> result = inverseDocumentFrequencyComponent.calculateForAllTerms(documents, terms);

        assertThat(result.keySet(), hasItems(TERM1, TERM2));
        assertThat(result.values(), containsInAnyOrder(
                Arrays.asList(
                        closeTo(0.301029996d, 0.0001d),
                        closeTo(0.301029996d, 0.0001d)
                )
        ));
    }
}