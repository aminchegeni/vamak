package ir.snapp.pay.side.project.vamak.commons.security;

@FunctionalInterface
public interface OtpNotifier<T extends Otp> {

    void notify(T otp, String to);
}
