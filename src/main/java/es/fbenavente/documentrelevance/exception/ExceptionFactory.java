package es.fbenavente.documentrelevance.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionFactory {
    public static <T extends Exception> T instanceOf(Class<T> exceptionClass, String message) {
        try {
            return (T) exceptionClass.getConstructor(String.class).newInstance(message);
        } catch (Exception e) {
            log.warn("Expected constructor not found. Runtime exception launched.");
            throw new RuntimeException(message, e);
        }
    }
}
