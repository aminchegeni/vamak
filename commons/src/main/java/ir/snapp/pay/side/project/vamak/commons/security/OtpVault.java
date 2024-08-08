package ir.snapp.pay.side.project.vamak.commons.security;

public interface OtpVault<T extends Otp> {

    T getOtp(String username);

    void invalidateOtp(String username);
}
