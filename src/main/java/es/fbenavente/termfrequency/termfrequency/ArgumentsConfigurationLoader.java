package es.fbenavente.termfrequency.termfrequency;

import es.fbenavente.termfrequency.termfrequency.configuration.TermFrequencyConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArgumentsConfigurationLoader {
    private final TermFrequencyConfiguration termFrequencyConfiguration;

    public void loadFromArgs(String ... args) {

    }
}
