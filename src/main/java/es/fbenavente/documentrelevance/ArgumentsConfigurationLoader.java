package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ArgumentsConfigurationLoader {
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public void loadFromArgs(ApplicationArguments args) {
        // TODO implement to read params following specs:
        // -d|-D for directory to scan documents
        // -tt|-TT for terms
        // -p|-P for interval
        // -n|-N for elements to show
    }
}
