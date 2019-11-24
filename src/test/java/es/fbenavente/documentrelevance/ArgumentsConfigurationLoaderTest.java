package es.fbenavente.documentrelevance;

import es.fbenavente.documentrelevance.configuration.DocumentRelevanceConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArgumentsConfigurationLoaderTest {

    @Mock
    private DocumentRelevanceConfiguration documentRelevanceConfiguration;

    @InjectMocks
    private ArgumentsConfigurationLoader argumentsConfigurationLoader;

    @Test
    void directory_should_be_changed_when_receive_d_param() {
        ApplicationArguments args = new DefaultApplicationArguments("-d","directory");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setDirectory("directory");
    }

    @Test
    void directory_should_be_changed_when_receive_D_param() {
        ApplicationArguments args = new DefaultApplicationArguments("-D","directory");

        argumentsConfigurationLoader.loadFromArgs(args);

        verify(documentRelevanceConfiguration).setDirectory("directory");
    }
}