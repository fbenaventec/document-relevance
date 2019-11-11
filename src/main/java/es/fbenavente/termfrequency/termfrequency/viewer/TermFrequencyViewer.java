package es.fbenavente.termfrequency.termfrequency.viewer;

import es.fbenavente.termfrequency.termfrequency.domain.TermFrequencyReport;
import org.springframework.stereotype.Component;

@Component
public class TermFrequencyViewer {
    public void view(TermFrequencyReport report) {
        System.out.println("Print");
    }
}
