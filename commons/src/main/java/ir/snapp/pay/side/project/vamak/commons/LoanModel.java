package ir.snapp.pay.side.project.vamak.commons;

import lombok.Getter;

import java.util.Map;

@Getter
public enum LoanModel implements Identifiable<Character> {

    UNKNOWN     ('U'),
    GOLD_BASE   ('G'),
    DOLLAR_BASE ('D'),
    BUTTERFLY   ('B'),
    NORMAL      ('N');

    private static final Map<Character, LoanModel> SYMBOLS = Identifiable.createCache(LoanModel.class);

    private final char symbol;

    LoanModel(char symbol) {
        this.symbol = symbol;
    }

    public static LoanModel valueOf(char symbol) {
        return SYMBOLS.getOrDefault(symbol, UNKNOWN);
    }

    @Override
    public Character getCode() {
        return getSymbol();
    }
}
