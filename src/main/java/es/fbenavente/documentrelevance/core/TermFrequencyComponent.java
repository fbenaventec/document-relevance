package es.fbenavente.documentrelevance.core;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TermFrequencyComponent {

    public double getTermFrequency(File file) {
        return Math.random();
    }
}
