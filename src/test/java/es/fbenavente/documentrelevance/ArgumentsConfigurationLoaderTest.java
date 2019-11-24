package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import es.fbenavente.documentrelevance.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArgumentsConfigurationLoaderTest {

    @Mock
    private DocumentRelevanceConfiguration documentRelevanceConfiguration;

    @InjectMocks
    private ArgumentsConfigurationLoader argumentsConfigurationLoader;

    @Test
    void directory_should_be_changed_when_receive_d_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-d","directory");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setDirectory("directory");
    }

    @Test
    void directory_should_be_changed_when_receive_D_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-D","directory");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setDirectory("directory");
    }

    @Test
    void terms_should_be_changed_when_receive_tt_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-TT","term1 term2");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setTerms(Arrays.asList("term1", "term2"));
    }

    @Test
    void terms_should_be_changed_when_receive_TT_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-TT","term1 term2");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setTerms(Arrays.asList("term1", "term2"));
    }

    @Test
    void results_to_show_should_be_changed_when_receive_n_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-n","1");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setResultsToShow(1);
    }

    @Test
    void results_to_show_should_be_changed_when_receive_N_param() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-N","1");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setResultsToShow(1);
    }

    @Test
    void interval_should_be_changed_when_receive_P_param_accepting_seconds() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-P","1s");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setInterval(Duration.ofSeconds(1));
    }

    @Test
    void interval_should_be_changed_when_receive_P_param_accepting_minutes() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-P","1m");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setInterval(Duration.ofMinutes(1));
    }

    @Test
    void interval_should_be_changed_when_receive_P_param_accepting_hours() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-P","1h");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setInterval(Duration.ofHours(1));
    }

    @Test
    void interval_should_be_changed_when_receive_P_param_accepting_days() throws InvalidArgumentException {
        ApplicationArguments args = new DefaultApplicationArguments("-P","1d");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setInterval(Duration.ofDays(1));
    }

    @Test
    void invalid_argument_should_be_throw_when_receive_P_param_accepting_days() {
        ApplicationArguments args = new DefaultApplicationArguments("-P","1");

        assertThrows(InvalidArgumentException.class, () -> argumentsConfigurationLoader.loadFromArgs(args));
    }
}