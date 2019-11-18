package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class TermFrequencyComponentTest {
    private static final String TERM1 = "term1";
    private static final String DOCUMENT1 = "document1";
    private static final String DOCUMENT2 = "document2";

    @InjectMocks
    private TermFrequencyComponent termFrequencyComponent;

    @Test
    public void when_term_does_not_appears_frequency_is_zero() {
        List<Document> documents = Arrays.asList(
                Document.builder().name(DOCUMENT1).build(),
                Document.builder().name(DOCUMENT2).build()
        );
        List<String> terms = Arrays.asList(TERM1);

        Map<String, Map<String, Double>> result = termFrequencyComponent.calculateForAllDocuments(documents, terms);

        assertThat(result.keySet(), containsInAnyOrder(DOCUMENT1, DOCUMENT2));
        assertThat(result.get(DOCUMENT1).keySet(), hasItem(TERM1));
        assertThat(result.get(DOCUMENT1).values(), hasItem(0d));
    }

    @Test
    public void when_term_appears_frequency_is_one() {
        List<Document> documents = Arrays.asList(
                Document.builder().name(DOCUMENT1).build(),
                Document.builder().name(DOCUMENT2).build()
        );
        List<String> terms = Arrays.asList(TERM1);

        Map<String, Map<String, Double>> result = termFrequencyComponent.calculateForAllDocuments(documents, terms);

        assertThat(result.keySet(), containsInAnyOrder(DOCUMENT1, DOCUMENT2));
        assertThat(result.get(DOCUMENT1).keySet(), hasItem(TERM1));
        assertThat(result.get(DOCUMENT1).values(), hasItem(0d));
    }
}