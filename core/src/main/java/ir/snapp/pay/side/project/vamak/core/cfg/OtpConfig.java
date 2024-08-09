package ir.snapp.pay.side.project.vamak.core.cfg;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "vamak.otp")
public class OtpConfig {

    @Positive
    @Min(30L)
    private long ttl = 30L; // second
}
