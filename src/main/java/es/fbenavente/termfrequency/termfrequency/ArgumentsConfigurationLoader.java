package es.fbenavente.termfrequency.termfrequency;

import es.fbenavente.termfrequency.termfrequency.configuration.TermFrequencyConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArgumentsConfigurationLoader {
    private final TermFrequencyConfiguration termFrequencyConfiguration;

    public void loadFromArgs(String ... args) {
        // TODO implement to read params following specs:
        // -d|-D for directory to scan documents
        // -tt|-TT for terms
        // -p|-P for interval
        // -n|-N for elements to show
    }
}
