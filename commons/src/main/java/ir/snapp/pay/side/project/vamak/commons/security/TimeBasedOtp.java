package ir.snapp.pay.side.project.vamak.commons.security;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data(staticConstructor = "otp")
public class TimeBasedOtp implements Otp, Serializable {

    @Serial
    private static final long serialVersionUID = 7243782279940802709L;

    private final String val;
    private final LocalDateTime expirationTime;

    public TimeBasedOtp(String val, LocalDateTime expirationTime) {
        this.val = val;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return expirationTime.isBefore(now());
    }
}
