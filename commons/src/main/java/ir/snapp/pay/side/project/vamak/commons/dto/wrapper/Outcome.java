package ir.snapp.pay.side.project.vamak.commons.dto.wrapper;

import ir.snapp.pay.side.project.vamak.commons.Identifiable;
import lombok.Getter;

import java.util.Map;

@Getter
public enum Outcome implements Identifiable<Integer> {

    UNKNOWN (-1),
    SUCCESS (0),
    FAILED  (1);

    private static final Map<Integer, Outcome> CODES = Identifiable.createCache(Outcome.class);

    private final int code;

    Outcome(int code) {
        this.code = code;
    }

    public static Outcome valueOf(int code) {
        return CODES.getOrDefault(code, UNKNOWN);
    }
}
