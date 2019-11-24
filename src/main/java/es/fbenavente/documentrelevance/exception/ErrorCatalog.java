package es.fbenavente.documentrelevance.exception;

public class ErrorCatalog {
    public static final String INVALID_DURATION =
            "Duration format is invalid should be [VALUE][UNIT] on VALUE is a natural number and " +
            "UNIT one of: s (for seconds), m (for minutes), h (for hours) and d (for days)";

    private ErrorCatalog(){

    }
}
