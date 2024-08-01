package ir.snapp.pay.side.project.vamak.commons.model;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public enum Model {

    UNKNOWN     ('U'),
    GOLD_BASE   ('G'),
    DOLLAR_BASE ('D'),
    BUTTERFLY   ('B'),
    NORMAL      ('N');

    private static final Map<Character, Model> SYMBOLS = Stream.of(values()).collect(
            collectingAndThen(
                    toMap(
                            Model::getSymbol,
                            Function.identity()
                    ),
                    Collections::unmodifiableMap));

    private final char symbol;

    Model(char symbol) {
        this.symbol = symbol;
    }

    public static Model valueOf(char symbol) {
        return SYMBOLS.getOrDefault(symbol, UNKNOWN);
    }

    public char getSymbol() {
        return symbol;
    }
}
