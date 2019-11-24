package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ArgumentsConfigurationLoader {
    private static final String DIRECTORY_PARAM = "-d";
    private static final String TERMS_PARAM = "-tt";
    private static final String RESULTS_TO_SHOW_PARAM = "-n";
    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public void loadFromArgs(ApplicationArguments args) {
        List<String> rawArgs = args.getNonOptionArgs();
        log.info(String.join(",", rawArgs));
        for (int i = 0; i + 1 < rawArgs.size(); i++) {
            String arg   = rawArgs.get(i);
            String value = rawArgs.get(i + 1);
            if (arg.equalsIgnoreCase(DIRECTORY_PARAM)) {
                documentRelevanceConfiguration.setDirectory(value);
                i++;
            } else if (arg.equalsIgnoreCase(TERMS_PARAM)) {
                String[] terms = value.split(" ");
                documentRelevanceConfiguration.setTerms(Arrays.asList(terms));
                i++;
            } else if (arg.equalsIgnoreCase(RESULTS_TO_SHOW_PARAM)) {
                Integer resultsToShow = Integer.valueOf(value);
                documentRelevanceConfiguration.setResultsToShow(resultsToShow);
                i++;
            }
        }
        // -p|-P for interval
    }
}
