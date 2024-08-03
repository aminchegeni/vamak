package ir.snapp.pay.side.project.vamak.commons.dto.wrapper;

import ir.snapp.pay.side.project.vamak.commons.Identifiable;
import lombok.Getter;

import java.util.Map;

@Getter
public enum Reason implements Identifiable<Integer> {

    UNKNOWN (-1),
    INVALID (0);

    private static final Map<Integer, Reason> CODES = Identifiable.createCache(Reason.class);

    private final int code;

    Reason(int code) {
        this.code = code;
    }

    public static Reason valueOf(int code) {
        return CODES.getOrDefault(code, UNKNOWN);
    }
}