package ir.snapp.pay.side.project.vamak.core.service.otp;

import ir.snapp.pay.side.project.vamak.commons.security.Otp;
import ir.snapp.pay.side.project.vamak.commons.security.OtpMaker;
import ir.snapp.pay.side.project.vamak.commons.security.OtpVault;
import ir.snapp.pay.side.project.vamak.commons.security.Totp;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.lang.System.Logger.Level.DEBUG;
import static java.util.Objects.requireNonNull;

@Service
@CacheConfig(cacheNames = "otp")
public class CachedVault implements OtpVault<Totp> {

    private static final System.Logger LOGGER = System.getLogger(CachedVault.class.getName());

    private final Cache cache;

    private final OtpMaker<Totp> maker;

    public CachedVault(CacheManager manager, OtpMaker<Totp> maker) {
        this.cache = manager.getCache("otp");
        this.maker = maker;
    }

    @Cacheable/*(key = "#username", condition = "@memberRepository.existsByUsername(#username)")*/
    @Override
    public Totp getOtp(String username) {
        return maker.generate();
    }

    @CacheEvict
    @Override
    public void invalidateOtp(String username) {
        LOGGER.log(DEBUG, () -> "%s used the otp".formatted(username));
    }

    @Scheduled(fixedDelayString = "@otpConfig.getTtl()")
    @SuppressWarnings("unchecked")
    public void evictExpiredOtps() {
        Map<?, Otp> store = ((Map<?, Otp>) requireNonNull(cache).getNativeCache());
        store.entrySet()
                .stream()
                .filter(entry -> ((Totp) entry.getValue()).isExpired())
                .peek(entry -> LOGGER.log(DEBUG,
                        () -> "%s for %s is evicted with scheduler".formatted(entry.getValue(), entry.getKey())))
                .map(Map.Entry::getKey)
                .forEach(cache::evict);
    }
}
