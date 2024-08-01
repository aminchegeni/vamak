package ir.snapp.pay.side.project.vamak.commons;

import java.util.Map;

public enum ReceiveType implements Identifiable<Character> {

    UNKNOWN     ('U'),
    TO_WINNER   ('W'),
    TO_OWNER    ('O');

    private static final Map<Character, ReceiveType> CODES = Identifiable.createCache(ReceiveType.class);

    private final char code;

    ReceiveType(char code) {
        this.code = code;
    }

    public static ReceiveType valueOf(char code) {
        return CODES.getOrDefault(code, UNKNOWN);
    }

    @Override
    public Character getCode() {
        return code;
    }
}
