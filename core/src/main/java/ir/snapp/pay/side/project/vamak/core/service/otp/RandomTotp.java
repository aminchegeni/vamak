package ir.snapp.pay.side.project.vamak.core.service.otp;

import ir.snapp.pay.side.project.vamak.commons.security.OtpMaker;
import ir.snapp.pay.side.project.vamak.commons.security.Totp;
import ir.snapp.pay.side.project.vamak.core.cfg.OtpConfig;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;

@Service
public class RandomTotp implements OtpMaker<Totp> {

    private final OtpConfig config;

    public RandomTotp(OtpConfig config) {
        this.config = config;
    }

    @Override
    public Totp generate() {
        int val = nextVal();
        return Totp.of(valueOf(val), now().plusSeconds(config.getTtl()));
    }

    private static int nextVal() {
        return ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
    }
}
