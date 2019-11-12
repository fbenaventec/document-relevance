package es.fbenavente.documentrelevance.core;

import es.fbenavente.documentrelevance.domain.DocumentRelevance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InverseDocumentFrequencyComponent {

    public List<DocumentRelevance> ranking() {
        return new ArrayList<>();
    }
}
