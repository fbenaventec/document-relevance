package es.fbenavente.documentrelevance.core;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class InverseDocumentFrequencyComponent {
    public double getInverseDocumentFrequency(File file) {
        return 1.0d;
    }
}
