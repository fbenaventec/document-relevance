package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ArgumentsConfigurationLoader {
    public static final String DIRECTORY_PARAM = "-d";
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public void loadFromArgs(ApplicationArguments args) {
        List<String> rawArgs = args.getNonOptionArgs();
        log.info(String.join(",", rawArgs));
        for (int i = 0; i + 1 < rawArgs.size(); i++) {
            String arg   = rawArgs.get(i);
            String value = rawArgs.get(i + 1);
            if (arg.equalsIgnoreCase(DIRECTORY_PARAM)) {
                documentRelevanceConfiguration.setDirectory(value);
            }
        }
        // TODO implement to read params following specs:
        // -d|-D for directory to scan documents
        // -tt|-TT for terms
        // -p|-P for interval
        // -n|-N for elements to show
    }
}
