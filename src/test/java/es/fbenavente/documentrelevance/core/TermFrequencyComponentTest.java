package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class TermFrequencyComponentTest {
    private static final String TERM1 = "term1";
    private static final String DOCUMENT1 = "document1";

    @InjectMocks
    private TermFrequencyComponent termFrequencyComponent;

    @Test
    public void when_term_does_not_appears_frequency_is_zero() {
        Document document = Document.builder().name(DOCUMENT1).build();

        double result = termFrequencyComponent.calculate(document, TERM1);

        assertThat(result, equalTo(0d));
    }

    @Test
    public void when_term_appears_frequency_is_one() {
        Document document = Document.builder().name(DOCUMENT1).build();

        Double result = termFrequencyComponent.calculate(document, TERM1);

        assertThat(result, equalTo(0d));
    }
}