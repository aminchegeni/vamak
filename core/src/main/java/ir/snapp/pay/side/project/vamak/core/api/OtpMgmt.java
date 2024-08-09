package ir.snapp.pay.side.project.vamak.core.api;

import ir.snapp.pay.side.project.vamak.core.cfg.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static ir.snapp.pay.side.project.vamak.core.cfg.Api.Version;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(Version.V1)
@PreAuthorize("permitAll()")
@HttpExchange(
        url = "/otp",
        contentType = "",
        accept = APPLICATION_JSON_VALUE
)
public interface OtpMgmt {

    @ResponseStatus(CREATED)
    @PostExchange
    void send(@RequestParam(value = "username") String username);
}
