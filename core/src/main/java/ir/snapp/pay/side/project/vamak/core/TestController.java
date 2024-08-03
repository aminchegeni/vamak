package ir.snapp.pay.side.project.vamak.core;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("permitAll()")
    @GetMapping(
            path = "/test",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String test() {
        return "test";
    }
}
