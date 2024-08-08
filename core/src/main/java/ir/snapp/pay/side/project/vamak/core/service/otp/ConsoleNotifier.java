package ir.snapp.pay.side.project.vamak.core.service.otp;

import ir.snapp.pay.side.project.vamak.commons.security.Otp;
import ir.snapp.pay.side.project.vamak.commons.security.OtpNotifier;
import org.springframework.stereotype.Service;

import static java.lang.System.Logger.Level.DEBUG;

@Service
public class ConsoleNotifier implements OtpNotifier<Otp> {

    private static final System.Logger LOGGER = System.getLogger(ConsoleNotifier.class.getName());

    @Override
    public void notify(Otp otp, String to) {
        LOGGER.log(DEBUG, () -> "the %s was sent for %s".formatted(otp, to));
    }
}
