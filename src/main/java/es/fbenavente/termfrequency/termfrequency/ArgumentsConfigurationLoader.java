package es.fbenavente.termfrequency.termfrequency;

import es.fbenavente.termfrequency.termfrequency.configuration.TermFrequencyConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ArgumentsConfigurationLoader {
    private final TermFrequencyConfiguration termFrequencyConfiguration;

    public void loadFromArgs(ApplicationArguments args) {
        // TODO implement to read params following specs:
        // -d|-D for directory to scan documents
        // -tt|-TT for terms
        // -p|-P for interval
        // -n|-N for elements to show
    }
}
