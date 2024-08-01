package ir.snapp.pay.side.project.vamak.commons;

import java.util.Map;

public enum PayType implements Identifiable<Character> {

    UNK ('U'),
    C2C ('C'),
    A2A ('A');

    private static final Map<Character, PayType> CODES = Identifiable.createCache(PayType.class);

    private final char code;

    PayType(char code) {
        this.code = code;
    }

    public static PayType valueOf(char code) {
        return CODES.get(code);
    }

    @Override
    public Character getCode() {
        return code;
    }
}
