package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static es.fbenavente.documentrelevance.parser.DurationParser.parseDuration;

@Component
@AllArgsConstructor
@Slf4j
public class ArgumentsConfigurationLoader {
    private static final String DIRECTORY_PARAM = "-d";
    private static final String TERMS_PARAM = "-tt";
    private static final String RESULTS_TO_SHOW_PARAM = "-n";
    private static final String PERIOD_PARAM = "-p";

    private final DocumentRelevanceConfiguration documentRelevanceConfiguration;

    public void loadFromArgs(ApplicationArguments args) throws InvalidArgumentException {
        List<String> rawArgs = args.getNonOptionArgs();
        log.info(String.join(",", rawArgs));
        for (int i = 0; i + 1 < rawArgs.size(); i+= 2) {
            String arg   = rawArgs.get(i);
            String value = rawArgs.get(i + 1);
            if (arg.equalsIgnoreCase(DIRECTORY_PARAM)) {
                documentRelevanceConfiguration.setDirectory(value);
            } else if (arg.equalsIgnoreCase(TERMS_PARAM)) {
                String[] terms = value.split(" ");
                documentRelevanceConfiguration.setTerms(Arrays.asList(terms));
            } else if (arg.equalsIgnoreCase(RESULTS_TO_SHOW_PARAM)) {
                Integer resultsToShow = Integer.valueOf(value);
                documentRelevanceConfiguration.setResultsToShow(resultsToShow);
            } else if (arg.equalsIgnoreCase(PERIOD_PARAM)) {
                Duration interval = parseDuration(value);
                documentRelevanceConfiguration.setInterval(interval);
            } else {
                i--;
            }
        }
    }

}
