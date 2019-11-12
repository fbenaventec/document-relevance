package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TermFrequencyComponent {

    public List<DocumentRelevance> evaluate() {
        return new ArrayList<>();
    }
}
