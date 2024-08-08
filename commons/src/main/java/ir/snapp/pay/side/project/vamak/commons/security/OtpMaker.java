package ir.snapp.pay.side.project.vamak.commons.security;

@FunctionalInterface
public interface OtpMaker<T extends Otp> {

    T generate();
}
