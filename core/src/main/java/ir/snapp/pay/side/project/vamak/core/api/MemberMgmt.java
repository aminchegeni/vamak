package ir.snapp.pay.side.project.vamak.core.api;

import ir.snapp.pay.side.project.vamak.commons.dto.UserInfo;
import ir.snapp.pay.side.project.vamak.core.cfg.Api;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static ir.snapp.pay.side.project.vamak.core.cfg.Api.Version;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(Version.V1)
@PreAuthorize("permitAll()")
@HttpExchange(
        url = "/members",
        contentType = "",
        accept = APPLICATION_JSON_VALUE
)
@Validated
public interface MemberMgmt {

    @ResponseStatus(NO_CONTENT)
    @PostExchange(
            contentType = APPLICATION_JSON_VALUE
    )
    void add(@Valid @RequestBody UserInfo userInfo);
}
