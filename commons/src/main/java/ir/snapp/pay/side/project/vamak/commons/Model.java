package ir.snapp.pay.side.project.vamak.commons;

import java.util.Map;

public enum Model implements Identifiable<Character> {

    UNKNOWN     ('U'),
    GOLD_BASE   ('G'),
    DOLLAR_BASE ('D'),
    BUTTERFLY   ('B'),
    NORMAL      ('N');

    private static final Map<Character, Model> SYMBOLS = Identifiable.createCache(Model.class);

    private final char symbol;

    Model(char symbol) {
        this.symbol = symbol;
    }

    public static Model valueOf(char symbol) {
        return SYMBOLS.getOrDefault(symbol, UNKNOWN);
    }

    @Override
    public Character getCode() {
        return symbol;
    }
}
