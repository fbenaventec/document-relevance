package es.fbenavente.documentrelevance.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionFactory {
    private ExceptionFactory() {

    }

    @SuppressWarnings("squid:S00112")
    public static <T extends Exception> T instanceOf(Class<T> exceptionClass, String message) {
        // This implementation could be replaced by a switch based on catalog of custom exceptions, this change
        // has some advantages: it doesn't use reflection; it's not necessary launch a RuntimeException
        try {
            return exceptionClass.getConstructor(String.class).newInstance(message);
        } catch (Exception e) {
            log.warn("Expected constructor not found. Runtime exception launched.");
            throw new RuntimeException(message, e);
        }
    }
}
