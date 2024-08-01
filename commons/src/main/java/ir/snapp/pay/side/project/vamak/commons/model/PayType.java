package ir.snapp.pay.side.project.vamak.commons.model;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public enum PayType {

    UNKNOWN     ('U'),
    TO_WINNER   ('W'),
    TO_OWNER    ('O');

    private static final Map<Character, PayType> CODES = Stream.of(values()).collect(
            collectingAndThen(
                    toMap(
                            PayType::getCode,
                            Function.identity()
                    ),
                    Collections::unmodifiableMap));

    private final char code;

    PayType(char code) {
        this.code = code;
    }

    public static PayType valueOf(char code) {
        return CODES.getOrDefault(code, UNKNOWN);
    }

    public char getCode() {
        return code;
    }
}
