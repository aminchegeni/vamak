package ir.snapp.pay.side.project.vamak.commons;

import java.util.Map;

public enum PayType implements Identifiable<Character> {

    UNKNOWN     ('U'),
    TO_WINNER   ('W'),
    TO_OWNER    ('O');

    private static final Map<Character, PayType> CODES = Identifiable.createCache(PayType.class);

    private final char code;

    PayType(char code) {
        this.code = code;
    }

    public static PayType valueOf(char code) {
        return CODES.getOrDefault(code, UNKNOWN);
    }

    @Override
    public Character getCode() {
        return code;
    }
}
