package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentRelevanceComponentTest {
    @Mock
    private TermFrequencyComponent termFrequencyComponent;
    @Mock
    private InverseDocumentFrequencyComponent inverseDocumentFrequencyComponent;
    @InjectMocks
    private DocumentRelevanceComponent component;

    @Test
    void given_a_file_his_relevance_is_the_product_of_tf_and_idf(@Mock File file) {
        when(termFrequencyComponent.getTermFrequency(file)).thenReturn(0.21);
        when(inverseDocumentFrequencyComponent.getInverseDocumentFrequency(file)).thenReturn(0.33);

        DocumentRelevance documentRelevance = component.getDocumentRelevance(file);

        assertThat(documentRelevance, notNullValue());
        assertThat(documentRelevance.getRanking(), equalTo(BigDecimal.valueOf(0.0693d)));
    }
}