package ir.snapp.pay.side.project.vamak.core.service;

import ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Error;
import ir.snapp.pay.side.project.vamak.commons.exception.DoseNotExistException;
import ir.snapp.pay.side.project.vamak.commons.security.Otp;
import ir.snapp.pay.side.project.vamak.commons.security.OtpNotifier;
import ir.snapp.pay.side.project.vamak.commons.security.OtpVault;
import ir.snapp.pay.side.project.vamak.commons.security.Totp;
import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ir.snapp.pay.side.project.vamak.commons.dto.wrapper.Reason.NOT_EXISTENT;

@Service
public class OtpService {

    private final MemberRepository repo;

    private final OtpNotifier<Otp> notifier;

    private final OtpVault<Totp> vault;

    public OtpService(MemberRepository repo, OtpNotifier<Otp> notifier, OtpVault<Totp> vault) {
        this.repo = repo;
        this.notifier = notifier;
        this.vault = vault;
    }

    public void sendTo(String username) {
        Optional<String> mobile = repo.findMobileByUsername(username);
        mobile.ifPresentOrElse(
                m -> {
                    if (!m.isEmpty()) {
                        Otp otp = vault.getOtp(username);
                        notifier.notify(otp, m);
                    }
                },
                () -> {
                    throw new DoseNotExistException(
                            Error.builder()
                                    .reason(NOT_EXISTENT)
                                    .param("username")
                                    .description("user dose not exist")
                                    .values(new String[]{username})
                                    .build());
                }
        );
    }

    public boolean isActive(Otp received, String username) {
        try {
            Otp forwarded = vault.getOtp(username);
            return forwarded.getVal().equals(received.getVal());
        } finally {
            vault.invalidateOtp(username);
        }
    }
}
