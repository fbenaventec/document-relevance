package es.fbenavente.documentrelevance.parser;

import es.fbenavente.documentrelevance.exception.InvalidArgumentException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static es.fbenavente.documentrelevance.ensure.Ensure.ensureThat;
import static es.fbenavente.documentrelevance.exception.ErrorCatalog.INVALID_DURATION;

public class DurationParser {
    private static final String SECOND_UNIT = "s";
    private static final String MINUTE_UNIT = "m";
    private static final String HOUR_UNIT = "h";
    private static final String DAY_UNIT = "d";
    private static final Pattern DURATION_PATTER = Pattern.compile(
            "(?<amount>\\d)+(?<unit>[" + DAY_UNIT + "|" + HOUR_UNIT + "|" + MINUTE_UNIT + "|" +SECOND_UNIT + "])",
            Pattern.CASE_INSENSITIVE);

    public static Duration parseDuration(String value) throws InvalidArgumentException {
        Matcher matcher = DURATION_PATTER.matcher(value);
        ensureThat(matcher.matches(), InvalidArgumentException.class, INVALID_DURATION);
        long amount = Long.parseLong(matcher.group("amount"));
        TemporalUnit temporalUnit = parseTemporalUnit(matcher.group("unit"));
        return Duration.of(amount, temporalUnit);
    }

    private static TemporalUnit parseTemporalUnit(String unit) {
        switch (unit.toLowerCase()) {
            case DAY_UNIT: return ChronoUnit.DAYS;
            case HOUR_UNIT: return ChronoUnit.HOURS;
            case MINUTE_UNIT: return ChronoUnit.MINUTES;
            default: return ChronoUnit.SECONDS;
        }
    }
}
