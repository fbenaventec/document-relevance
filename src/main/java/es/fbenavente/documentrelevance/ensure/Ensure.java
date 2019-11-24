package es.fbenavente.documentrelevance.ensure;

import es.fbenavente.documentrelevance.exception.ExceptionFactory;

public class Ensure {

    public static <T extends Exception> void ensureThat(
            boolean value,
            Class<T> exceptionClass,
            String message) throws T {
        if (!value) {
            throw ExceptionFactory.instanceOf(exceptionClass, message);
        }
    }
}
