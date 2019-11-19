package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

@ExtendWith(MockitoExtension.class)
class InverseDocumentFrequencyComponentTest {

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
                        .name(DOCUMENT_NAME2).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 3);
                                           put(TERM2, 4);
                                       }}
                        ).build()
        );

        double result = inverseDocumentFrequencyComponent.calculate(documents, TERM1);

        assertThat(result, equalTo(0d));
    }

    @Test
    void when_term_does_not_appears_in_the_corpus_the_result_is_one() {
        List<Document> documents = Arrays.asList(
                Document.builder()
                        .name(DOCUMENT_NAME1).words(10)
                        .termFrequency(new HashMap<>()).build(),
                Document.builder()
                        .name(DOCUMENT_NAME2).words(10)
                        .termFrequency(new HashMap<>()).build()
        );

        double result = inverseDocumentFrequencyComponent.calculate(documents, TERM1);

        assertThat(result, equalTo(1d));
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
                        .name(DOCUMENT_NAME2).words(10)
                        .termFrequency(new HashMap<String, Integer>() {{
                                           put(TERM1, 1);
                                           put(TERM2, 0);
                                       }}
                        ).build()
        );

        double result = inverseDocumentFrequencyComponent.calculate(documents, TERM1);

        assertThat(result, closeTo(0.301029996d, 0.0001d));
    }
}